package liikennedata.SiriDownload.MetaData;

/**
 * Metadata which is added by us to complement the Siri data.
 * This data is stored even if retrieving the Siri data fails.
 * @author lapel1
 *
 */
public class MetaData {
	public enum SiriDataSource {
		Tampere
	}
	
	/**
	 * HTTP status code of the GET request.
	 */
	private Number HttpStatus;
	/**
	 * When was the data saved
	 */
	private Number SaveTimeStamp;
	/**
	 * Source of the Siri data.
	 */
	private SiriDataSource DataSource;
	
	public Number getHttpStatus() {
		return HttpStatus;
	}
	public void setHttpStatus(Number httpStatus) {
		HttpStatus = httpStatus;
	}
	public Number getSaveTimeStamp() {
		return SaveTimeStamp;
	}
	public void setSaveTimeStamp(Number saveTimeStamp) {
		SaveTimeStamp = saveTimeStamp;
	}
	public SiriDataSource getDataSource() {
		return DataSource;
	}
	public void setDataSource(SiriDataSource dataSource) {
		this.DataSource = dataSource;
	}
	
}
