package eu.bde.sc4pilot.mapmatch;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class MapMatch {

	public static void main(String[] args) throws Exception {
		RConnection c = new RConnection();
		REXP x = c.eval("R.version.string");
		System.out.println(x.asString());

	}

}
