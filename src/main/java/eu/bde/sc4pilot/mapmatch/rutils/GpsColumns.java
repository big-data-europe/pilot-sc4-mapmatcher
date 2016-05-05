package eu.bde.sc4pilot.mapmatch.rutils;

import java.util.ArrayList;
/**
 * Container of GPS data per colums
 * @author Luigi Selmi
 *
 */
public class GpsColumns {

  String [] names = {"device_id",
      "inserted_timestamp",
      "longitude", 
      "latitude", 
      "altitude",
      "speed", 
      "orientation", 
      "recorded_timestamp", 
      "valid", 
      "zoneid", 
      "transfer", 
      "unix_timestamp"}; 
    
    public String [] getNames() {
      return names;
    }
    
    private int size = 0;
    
    public GpsColumns(int size) {
      this.size = size;
    }
    
    private int [] deviceId = new int[size];
    private String [] insertedTimestamp = new String[size];
    private double [] longitude = new double[size];
    private double [] latitude = new double[size]; 
    private double [] altitude = new double[size];
    private int [] speed = new int[size];
    private double [] orientation = new double[size];
    private String [] recordedTimestamp = new String[size];
    private int [] valid = new int[size];
    private int [] zoneid = new int[size];
    private int [] transfer = new int[size];
    private String [] unixTimestamp = new String[size];

    public int getSize() {
      return size;
    }
    public void setSize(int size) {
      this.size = size;
    }
    public int[] getDeviceId() {
      return deviceId;
    }
    public void setDeviceId(int[] deviceId) {
      this.deviceId = deviceId;
    }
    public String[] getInsertedTimestamp() {
      return insertedTimestamp;
    }
    public void setInsertedTimestamp(String[] insertedTimestamp) {
      this.insertedTimestamp = insertedTimestamp;
    }
    public double[] getLongitude() {
      return longitude;
    }
    public void setLongitude(double[] longitude) {
      this.longitude = longitude;
    }
    public double[] getLatitude() {
      return latitude;
    }
    public void setLatitude(double[] latitude) {
      this.latitude = latitude;
    }
    public double[] getAltitude() {
      return altitude;
    }
    public void setAltitude(double[] altitude) {
      this.altitude = altitude;
    }
    public int[] getSpeed() {
      return speed;
    }
    public void setSpeed(int[] speed) {
      this.speed = speed;
    }
    public double[] getOrientation() {
      return orientation;
    }
    public void setOrientation(double[] orientation) {
      this.orientation = orientation;
    }
    public String[] getRecordedTimestamp() {
      return recordedTimestamp;
    }
    public void setRecordedTimestamp(String[] recordedTimestamp) {
      this.recordedTimestamp = recordedTimestamp;
    }
    public int[] getValid() {
      return valid;
    }
    public void setValid(int[] valid) {
      this.valid = valid;
    }
    public int[] getZoneid() {
      return zoneid;
    }
    public void setZoneid(int[] zoneid) {
      this.zoneid = zoneid;
    }
    public int[] getTransfer() {
      return transfer;
    }
    public void setTransfer(int[] transfer) {
      this.transfer = transfer;
    }
    public String[] getUnixTimestamp() {
      return unixTimestamp;
    }
    public void setUnixTimestamp(String[] unixTimestamp) {
      this.unixTimestamp = unixTimestamp;
    }

    
}
