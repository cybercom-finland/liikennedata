package liikennedata.SiriDownload;

import liikennedata.SiriDownload.SiriObjects.*;
import liikennedata.SiriDownload.MetaData.*;

/**
 * Root for the json data.
 * Contains an entry for the original unaltered Siri data and an entry for our own metadata.
 * @author lapel1
 *
 */
public class DataRoot {
	private MetaData MetaData;
	private Siri Siri;
	
	public liikennedata.SiriDownload.MetaData.MetaData getMetaData() {
		return MetaData;
	}
	public void setMetaData(liikennedata.SiriDownload.MetaData.MetaData metaData) {
		MetaData = metaData;
	}
	
	public Siri getSiri() {
		return Siri;
	}
	public void setSiri(Siri siri) {
		Siri = siri;
	}
}
