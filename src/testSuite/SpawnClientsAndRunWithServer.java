package testSuite;

import java.util.Random;

import server.Server;

public class SpawnClientsAndRunWithServer {

	/*
	 * Super parameters
	 */
	
	
	public static void main(String[] args) {
		Server server = new MockServer();
		for (int i = 0; i < 1; i++) {
			new MockClient(server);
		}
		
		// Just want to keep all spawned threads alive, so this thread is a no-op from here on
		while (true);
	}
}
