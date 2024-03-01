package sockets.exemplo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 4001);
			//Socket socket = new Socket("10.105.68.171", 4001);

			/* Enviando requisição */
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
//			writer.print("getDataHora\n");
			
//			writer.print("getHost\n");
			
			writer.print("getAuthor\n");
			writer.flush();
			
			
			/* Recebendo resposta */
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
