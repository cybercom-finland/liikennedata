
package liikennedata.SiriDownload.SiriObjects;

import java.util.List;

public class VehicleMonitoringDelivery{
   	private Number ResponseTimestamp;
   	private boolean Status;
   	private List<VehicleActivity> VehicleActivity;
   	private String version;
   	
	public Number getResponseTimestamp() {
		return ResponseTimestamp;
	}
	public void setResponseTimestamp(Number responseTimestamp) {
		ResponseTimestamp = responseTimestamp;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	public List<VehicleActivity> getVehicleActivity() {
		return VehicleActivity;
	}
	public void setVehicleActivity(List<VehicleActivity> vehicleActivity) {
		VehicleActivity = vehicleActivity;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}


}
