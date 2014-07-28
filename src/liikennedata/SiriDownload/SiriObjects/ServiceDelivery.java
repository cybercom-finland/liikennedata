
package liikennedata.SiriDownload.SiriObjects;

import java.util.List;

public class ServiceDelivery{
   	private boolean MoreData;
   	private ProducerRef ProducerRef;
   	private Number ResponseTimestamp;
   	private boolean Status;
   	private List<VehicleMonitoringDelivery> VehicleMonitoringDelivery;
   	
	public boolean isMoreData() {
		return MoreData;
	}
	public void setMoreData(boolean moreData) {
		MoreData = moreData;
	}
	public ProducerRef getProducerRef() {
		return ProducerRef;
	}
	public void setProducerRef(ProducerRef producerRef) {
		ProducerRef = producerRef;
	}
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
	public List<VehicleMonitoringDelivery> getVehicleMonitoringDelivery() {
		return VehicleMonitoringDelivery;
	}
	public void setVehicleMonitoringDelivery(
			List<VehicleMonitoringDelivery> vehicleMonitoringDelivery) {
		VehicleMonitoringDelivery = vehicleMonitoringDelivery;
	}

}
