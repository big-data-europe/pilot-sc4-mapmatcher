package eu.bde.sc4pilot.mapmatch.rutils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RUtilTest {

  RUtil r;
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    r = new RUtil();
  }

  @Test
  public void testReadCsv() throws MalformedURLException, IOException {
    InputStream is = getClass().getResourceAsStream("gpsdata_test.csv");
    GpsColumns frame = r.readCsv(is, true);
    int [] id = frame.getDeviceId();
    
    Assert.assertTrue("The identifier doesn't match.", id[0] == 20034);  
    
  }

}
