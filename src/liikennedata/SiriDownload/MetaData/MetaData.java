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
	private Number SaveTimestamp;
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
	public Number getSaveTimestamp() {
		return SaveTimestamp;
	}
	public void setSaveTimestamp(Number saveTimestamp) {
		SaveTimestamp = saveTimestamp;
	}
	public SiriDataSource getDataSource() {
		return DataSource;
	}
	public void setDataSource(SiriDataSource dataSource) {
		this.DataSource = dataSource;
	}
	
}
