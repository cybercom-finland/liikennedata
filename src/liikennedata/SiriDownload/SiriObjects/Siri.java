
package liikennedata.SiriDownload.SiriObjects;

import java.util.List;

public class Siri{
   	private ServiceDelivery ServiceDelivery;
   	private String version;

 	public ServiceDelivery getServiceDelivery(){
		return this.ServiceDelivery;
	}
	public void setServiceDelivery(ServiceDelivery serviceDelivery){
		this.ServiceDelivery = serviceDelivery;
	}
 	public String getVersion(){
		return this.version;
	}
	public void setVersion(String version){
		this.version = version;
	}
}
