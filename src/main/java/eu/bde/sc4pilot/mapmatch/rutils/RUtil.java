package eu.bde.sc4pilot.mapmatch.rutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import org.rosuda.REngine.REXPDouble;
import org.rosuda.REngine.REXPInteger;
import org.rosuda.REngine.REXPString;
import org.rosuda.REngine.RList;

/**
 * Provides some methods to transform Java collections into R data frame
 * @author Luigi Selmi
 *
 */
public class RUtil {
  
  /**
   *   
   * @param is
   * @param header
   * @return
   * @throws MalformedURLException
   * @throws IOException
   */
  public GpsColumns readCsv(InputStream is, boolean header) throws MalformedURLException, IOException {
    ArrayList<String> csvLines = new ArrayList<String>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    String recordLine;
    String headerLine;
    if(header) 
      headerLine = reader.readLine();
    while((recordLine = reader.readLine()) != null) {
      csvLines.add(recordLine);
    }
    
    int size = csvLines.size();
    
    GpsColumns gps = new GpsColumns(size);
    
    int [] deviceId = new int[size];
    String [] insertedTimestamp = new String[size];
    double [] longitude = new double[size];
    double [] latitude = new double[size]; 
    double [] altitude = new double[size];
    int [] speed = new int[size];
    double [] orientation = new double[size];
    String [] recordedTimestamp = new String[size];
    int [] valid = new int[size];
    int [] zoneid = new int[size];
    int [] transfer = new int[size];
    String [] unixTimestamp = new String[size];

    
    Iterator<String> icsvLines = csvLines.iterator();
    int recordNumber = 0;
    while (icsvLines.hasNext()) {
      String [] fields = icsvLines.next().split(",");
      deviceId[recordNumber] = Integer.valueOf(fields[0]);
      insertedTimestamp[recordNumber] = fields[1];
      longitude[recordNumber] = Double.valueOf(fields[2]);
      latitude[recordNumber] = Double.valueOf(fields[3]);
      altitude[recordNumber] = Double.valueOf(fields[4]);
      speed[recordNumber] = Integer.valueOf(fields[5]);
      orientation[recordNumber] = Double.valueOf(fields[6]);
      recordedTimestamp[recordNumber] = fields[7];
      valid[recordNumber] = Integer.valueOf(fields[8]);
      zoneid[recordNumber] = Integer.valueOf(fields[9]);
      transfer[recordNumber] = Integer.valueOf(fields[10]);
      unixTimestamp[recordNumber] = fields[11];
      recordNumber++;
    }
    
    gps.setDeviceId(deviceId);
    gps.setInsertedTimestamp(insertedTimestamp);
    gps.setLongitude(longitude);
    gps.setLatitude(latitude);
    gps.setAltitude(altitude);
    gps.setSpeed(speed);
    gps.setRecordedTimestamp(recordedTimestamp);
    gps.setValid(valid);
    gps.setZoneid(zoneid);
    gps.setTransfer(transfer);
    gps.setUnixTimestamp(unixTimestamp);
    
    return gps;
  }
  /**
   * Creates a R data frame from a CSV file  
   * @param is
   * @param header
   * @return
   * @throws MalformedURLException
   * @throws IOException
   */
  public RList createRListFromCsv(InputStream is, boolean header) throws MalformedURLException, IOException  {
    RList rlist = new RList();
    GpsColumns gps = readCsv(is, header);
  
    rlist.put(gps.names[0],new REXPInteger(gps.getDeviceId()));
    rlist.put(gps.names[1],new REXPString(gps.getInsertedTimestamp()));
    rlist.put(gps.names[2],new REXPDouble(gps.getLongitude()));
    rlist.put(gps.names[3],new REXPDouble(gps.getLatitude()));
    rlist.put(gps.names[4],new REXPDouble(gps.getAltitude()));
    rlist.put(gps.names[5],new REXPInteger(gps.getSpeed()));
    rlist.put(gps.names[6],new REXPDouble(gps.getOrientation()));
    rlist.put(gps.names[7],new REXPString(gps.getRecordedTimestamp()));
    rlist.put(gps.names[8],new REXPInteger(gps.getValid()));
    rlist.put(gps.names[9],new REXPInteger(gps.getZoneid()));
    rlist.put(gps.names[10],new REXPInteger(gps.getTransfer()));
    rlist.put(gps.names[11],new REXPString(gps.getUnixTimestamp()));
   
    return rlist;
  }
  
  public void printRList(RList rdata) {
    
  }
  
  
}
