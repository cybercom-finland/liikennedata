package liikennedata.SiriDownload;

import liikennedata.SiriDownload.SiriObjects.SiriRoot;

/**
 * Class for comparing different versions of the Siri data and deciding what needs to be saved and what not.
 * Basically we want to save only the data that is changed - we do not want to save all of the data.
 * @author lapel1
 *
 */
public class DataCompare {
	public SiriRoot GetChanges(SiriRoot oldData, SiriRoot newData) {
		if (oldData.getSiri().getServiceDelivery().getResponseTimestamp().doubleValue() == newData.getSiri().getServiceDelivery().getResponseTimestamp().doubleValue()) {
			return null; // no changes
		}
		
		return null;
	}
}
