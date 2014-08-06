
package liikennedata.SiriDownload.SiriObjects;


/**
 * Root for the Siri classes. This class is only used internally for a brief period to get the Siri data parsed correctly. This class is not saved in the final Json.
 * Classes produced by http://jsongen.byingtondesign.com/index.jsp
 * @author lapel1
 *
 */
public class SiriRoot {
   	private Siri Siri;

 	public Siri getSiri(){
		return this.Siri;
	}
	public void setSiri(Siri siri){
		this.Siri = siri;
	}
}
