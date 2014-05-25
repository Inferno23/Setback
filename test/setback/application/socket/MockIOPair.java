/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This implementation of the IOPair interface does not use
 * sockets.  Instead, it just outputs the given string.
 * @author Michael Burns
 * @version May 25, 2014
 */
public class MockIOPair implements IOPair {

	private OutputStream out;
	private InputStream in;

	public MockIOPair(String inputString) {
		out = new ByteArrayOutputStream();
		if (inputString != null) {
			in = new ByteArrayInputStream(inputString.getBytes());
		}
		else {
			String str = "\n";
			in = new ByteArrayInputStream(str.getBytes());
		}
	}

	/* (non-Javadoc)
	 * @see setback.application.socket.IOPair#out()
	 */
	@Override
	public OutputStream out() throws IOException {
		return out;
	}

	/* (non-Javadoc)
	 * @see setback.application.socket.IOPair#in()
	 */
	@Override
	public InputStream in() throws IOException {
		return in;
	}

	/* (non-Javadoc)
	 * @see setback.application.socket.IOPair#close()
	 */
	@Override
	public void close() throws IOException {}

}
