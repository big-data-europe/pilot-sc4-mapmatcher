package eu.bde.sc4pilot.mapmatch;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapMatch {
	
	private static final Logger log = LoggerFactory.getLogger(MapMatch.class);

	public static void main(String[] args) { 
	    RConnection c = initRserve();
		try {
			
			REXP x = c.eval("loadPackages();" +
						"road<-readGeoData();" +
						"gdata<-readGpsData();" +
						"matches<-match(road,gdata);" +
						"printMatches(matches)");
			
			//log.info(x.asString());
			
			
		} catch (RserveException e) {		
			e.printStackTrace();
		} /*catch (REXPMismatchException e) {			
			e.printStackTrace();
		} */finally {
			c.close();
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
