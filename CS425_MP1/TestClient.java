import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

// import client.Client;
// import grep.SysGrep;
// import server.Server;

public class TestClient {
	@Test
	public void testClientConstructor() throws UnknownHostException {
		String expectedlocalIP = InetAddress.getLocalHost().getHostAddress();
		Client client = new Client();
    	String localIP = client.getClientIP();
    	assertEquals(expectedlocalIP, localIP); 
	}
	
	@Test
	public void testClientWithoutRunningServer() throws ConnectException {
		Client client = new Client();
    	try {
			client.excute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClientWithRunningServer() {
		Server server;
		try {
			server = new Server();
			server.excute();
			Client client = new Client();
	    	try {
				client.excute();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
