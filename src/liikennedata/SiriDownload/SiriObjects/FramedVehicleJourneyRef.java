
package liikennedata.SiriDownload.SiriObjects;

import java.util.List;

public class FramedVehicleJourneyRef{
   	private DataFrameRef DataFrameRef;
   	private String DatedVehicleJourneyRef;

 	public DataFrameRef getDataFrameRef(){
		return this.DataFrameRef;
	}
	public void setDataFrameRef(DataFrameRef dataFrameRef){
		this.DataFrameRef = dataFrameRef;
	}
 	public String getDatedVehicleJourneyRef(){
		return this.DatedVehicleJourneyRef;
	}
	public void setDatedVehicleJourneyRef(String datedVehicleJourneyRef){
		this.DatedVehicleJourneyRef = datedVehicleJourneyRef;
	}
}
