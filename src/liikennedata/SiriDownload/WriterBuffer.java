package liikennedata.SiriDownload;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for buffering Siri data
 * @author lapel1
 *
 */
public class WriterBuffer {
	
	/**
	 * How big is the buffer. When it gets full, the data is given to anyone who asks.
	 * Full doesn't mean really full in this context - it can become fuller than full.
	 */
	private static final int BUFFERSIZE = 10;
	
	private ArrayList<String> buffer = new ArrayList<String>();

	/**
	 * Add a string to the buffer
	 * @param text
	 */
	public void add(String text) {
		buffer.add(text);
	}
	
	/**
	 * If the buffer is full ('fullness' is defined by us), return the buffer.
	 * If the buffer is not full, return an empty list.
	 * @return The buffer or an empty list
	 */
	public ArrayList<String> getReadyBufferedContent() {
		if (buffer.size() >= BUFFERSIZE) {
			return buffer;
//			ArrayList<String> ret = (ArrayList<String>) buffer.clone();
//			buffer.clear();
//			return ret;
		}
		return new ArrayList<String>();
	}
	
	/**
	 * Clears the buffer
	 */
	public void emptyBuffer() {
		buffer.clear();
	}
}
