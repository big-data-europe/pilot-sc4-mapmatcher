package eu.bde.sc4pilot.mapmatch;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import eu.bde.sc4pilot.mapmatch.rutils.RUtil;

/**
 * A client class to connect to a proxy R server (Rserve) to evaluate R statements.
 * @author Luigi Selmi
 *
 */
public class MapMatch {
	
  private static String DEFAULT_GPS_DATASET = "https://raw.githubusercontent.com/big-data-europe/pilot-sc4-docker-r/master/test/gpsdata_test.csv";
	private static final Logger log = LoggerFactory.getLogger(MapMatch.class);
	
	public static void main(String[] args) throws MalformedURLException, IOException { 
	  String urlDataSet;
	  String rserveHost;
	  int rservePort = 6311;
	  if (args.length < 3) {
	     System.out.println("This application requires three parameters in the following order:\n" +
	                      "1) Url of the data set to be map matched. A default one is provided.\n" + 
	                      "2) IP address of Rserve. The default value is \"localhost\".\n" +
	                      "3) Port number of Rserve. The default value is 6311.\n");
	     urlDataSet = DEFAULT_GPS_DATASET;
	     rserveHost = "localhost";
	  } else {
	    urlDataSet = args[0];
	    rserveHost = args[1];
	    rservePort = Integer.valueOf(args[2]);
	  }
	  
	  // Connection to Rserve
	  RConnection c = initRserve(rserveHost, rservePort);
	  // Rserve root folder. Contains the R script with the functions used by the client (this class)
	  // and the geographical data for the map matching
	  final String RSERVE_HOME = "/home/sc4pilot/rserve";
	  try {
		  // Gets the data
	    URL csvUrl = new URL(urlDataSet);
	    URLConnection connection = csvUrl.openConnection();
      InputStream is = connection.getInputStream();
      RUtil util = new RUtil();
      RList l = util.createRListFromCsv(is, true);
      
      // Evaluates R commands
	    c.eval("setwd('" + RSERVE_HOME + "')");
			c.voidEval("loadPackages()");
			c.voidEval("road<-readGeoData()");
      c.assign("gpsdata", REXP.createDataFrame(l));
      c.voidEval("initgps<-initGpsData(gpsdata)");
      c.voidEval("gdata<-readGpsData(gpsdata)");
      RList matches = c.eval("match(road,initgps,gdata)").asList();
      
      // Field15
      String tfieldName15 = (String) matches.names.get(15);
      log.info(tfieldName15);
      String [] tfield15 = matches.at(15).asStrings();
      for(int j = 0; j < tfield15.length; j++){
        log.info(j + ") " + tfield15[j]);
      }
			
		} catch (RserveException e) {		
			e.printStackTrace();
		} catch (REXPMismatchException e) {			
			e.printStackTrace();
		} finally {
			c.close();
			log.info("Rserve: closed connection.");
		}
	}
	
	private static RConnection initRserve(String rserveHost, int rservePort) {
		RConnection c = null;
		try {
			c = new RConnection(rserveHost, rservePort);
			REXP x = c.eval("R.version.string");
			log.info(x.asString());;
			
		} catch (RserveException e) {			
			e.printStackTrace();
		} catch (REXPMismatchException e) {			
			e.printStackTrace();
		}
		return c;
	}

}
