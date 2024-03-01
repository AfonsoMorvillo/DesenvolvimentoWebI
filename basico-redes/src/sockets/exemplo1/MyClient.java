package sockets.exemplo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyClient {
	public static void main(String[] args) {
		try {
			//Socket socket = new Socket("localhost", 4001);
			Socket socket = new Socket("10.105.68.171", 4001);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			/* Recebendo dados de resposta */
			String line = reader.readLine();			
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			
			reader.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
