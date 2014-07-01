
package liikennedata.SiriObjects;

import java.util.List;

public class VehicleLocation{
   	private Number Latitude;
   	private Number Longitude;

 	public Number getLatitude(){
		return this.Latitude;
	}
	public void setLatitude(Number latitude){
		this.Latitude = latitude;
	}
 	public Number getLongitude(){
		return this.Longitude;
	}
	public void setLongitude(Number longitude){
		this.Longitude = longitude;
	}
}
