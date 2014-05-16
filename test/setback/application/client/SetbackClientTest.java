/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import setback.common.SetbackException;

/**
 * This class is to test the SetbackClient.  It will prove that
 * the SetbackClient can properly open a socket to a host.
 * @author Michael Burns
 * @version Apr 18, 2014
 */
public class SetbackClientTest {

	private ServerSocket serverSocket;
	private final int port = 9999;

	@Before
	public void setup() throws IOException {
		serverSocket = new ServerSocket(port);
	}

	@After
	public void cleanup() throws IOException {
		serverSocket.close();
	}

	@Test
	public void singleClientConnectionTest() throws SetbackException {
		Socket socket = SetbackClient.makeSocket(port, "localhost");
		assertEquals(Socket.class, socket.getClass());
		assertEquals(port, socket.getPort());
	}
	
	@Test(expected=SetbackException.class)
	public void invalidHostNameTest() throws SetbackException {
		SetbackClient.makeSocket(port, "fakeHost");
	}
	
	@Test(expected=SetbackException.class)
	public void invalidPortTest() throws SetbackException {
		SetbackClient.makeSocket(0, "localhost");
	}
}
