package eu.bde.sc4pilot.mapmatch;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPDouble;
import org.rosuda.REngine.REXPInteger;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REXPString;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.bde.sc4pilot.mapmatch.rutils.RUtil;
/**
 * A client class to connect to a proxy R server (Rserve) to evaluate R statements.
 * @author luigi
 *
 */
public class MapMatch {
	
	private static final Logger log = LoggerFactory.getLogger(MapMatch.class);
	
	public static void main(String[] args) throws MalformedURLException, IOException { 
	  
	  // Connection to Rserve
	  RConnection c = initRserve();
	  // Rserve root folder. Contains the R script with the commands used by the client 
	  // and the geographical data for the map matching
		final String RSERVE_HOME = "/home/luigi/projects/bigdataeurope/certh-mapmatching"; 
	  try {
		  // Gets the data
	    URL csvUrl = new URL("file:///home/luigi/projects/bigdataeurope/pilot-sc4-mapmatcher/src/test/resources/eu/bde/sc4pilot/mapmatch/rutils/gpsdata_test.csv");
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
	
	private static RConnection initRserve() {
		RConnection c = null;
		try {
			c = new RConnection();
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
