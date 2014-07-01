
package liikennedata.SiriObjects;

import java.util.List;

public class VehicleActivity{
   	private MonitoredVehicleJourney MonitoredVehicleJourney;
   	private Number RecordedAtTime;
   	private Number ValidUntilTime;

 	public MonitoredVehicleJourney getMonitoredVehicleJourney(){
		return this.MonitoredVehicleJourney;
	}
	public void setMonitoredVehicleJourney(MonitoredVehicleJourney monitoredVehicleJourney){
		this.MonitoredVehicleJourney = monitoredVehicleJourney;
	}
 	public Number getRecordedAtTime(){
		return this.RecordedAtTime;
	}
	public void setRecordedAtTime(Number recordedAtTime){
		this.RecordedAtTime = recordedAtTime;
	}
 	public Number getValidUntilTime(){
		return this.ValidUntilTime;
	}
	public void setValidUntilTime(Number validUntilTime){
		this.ValidUntilTime = validUntilTime;
	}
}
