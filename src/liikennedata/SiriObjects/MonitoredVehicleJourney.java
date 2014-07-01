
package liikennedata.SiriObjects;

import java.util.List;

public class MonitoredVehicleJourney{
   	private Number Bearing;
   	private String Delay;
   	private DestinationName DestinationName;
   	private DirectionRef DirectionRef;
   	private FramedVehicleJourneyRef FramedVehicleJourneyRef;
   	private LineRef LineRef;
   	private boolean Monitored;
   	private OperatorRef OperatorRef;
   	private OriginName OriginName;
   	private VehicleLocation VehicleLocation;
   	private VehicleRef VehicleRef;
	public Number getBearing() {
		return Bearing;
	}
	public void setBearing(Number bearing) {
		Bearing = bearing;
	}
	public String getDelay() {
		return Delay;
	}
	public void setDelay(String delay) {
		Delay = delay;
	}
	public DestinationName getDestinationName() {
		return DestinationName;
	}
	public void setDestinationName(DestinationName destinationName) {
		DestinationName = destinationName;
	}
	public DirectionRef getDirectionRef() {
		return DirectionRef;
	}
	public void setDirectionRef(DirectionRef directionRef) {
		DirectionRef = directionRef;
	}
	public FramedVehicleJourneyRef getFramedVehicleJourneyRef() {
		return FramedVehicleJourneyRef;
	}
	public void setFramedVehicleJourneyRef(
			FramedVehicleJourneyRef framedVehicleJourneyRef) {
		FramedVehicleJourneyRef = framedVehicleJourneyRef;
	}
	public LineRef getLineRef() {
		return LineRef;
	}
	public void setLineRef(LineRef lineRef) {
		LineRef = lineRef;
	}
	public boolean isMonitored() {
		return Monitored;
	}
	public void setMonitored(boolean monitored) {
		Monitored = monitored;
	}
	public OperatorRef getOperatorRef() {
		return OperatorRef;
	}
	public void setOperatorRef(OperatorRef operatorRef) {
		OperatorRef = operatorRef;
	}
	public OriginName getOriginName() {
		return OriginName;
	}
	public void setOriginName(OriginName originName) {
		OriginName = originName;
	}
	public VehicleLocation getVehicleLocation() {
		return VehicleLocation;
	}
	public void setVehicleLocation(VehicleLocation vehicleLocation) {
		VehicleLocation = vehicleLocation;
	}
	public VehicleRef getVehicleRef() {
		return VehicleRef;
	}
	public void setVehicleRef(VehicleRef vehicleRef) {
		VehicleRef = vehicleRef;
	}

}
