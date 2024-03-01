package sockets.exemplo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Date;

public class MyServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(4001);

			while (true) {
				/* Aguardando conexões */
				Socket socket = server.accept();

				/* Recebendo requisição */
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String requisicao = reader.readLine();

				PrintWriter writer = new PrintWriter(socket.getOutputStream());

				switch (requisicao) {
				case Requisicoes.getDataHora:
					Date data = new Date(System.currentTimeMillis());
					System.out.println(data);
					writer.print(data);
					break;
				case Requisicoes.getHost:
					String host = System.getProperty("os.name");
					System.out.println(host);
					writer.print(host);
					break;

				case Requisicoes.getAuthor:
					String author = "Afonso";
					System.out.println(author);
					writer.print(author + "!\n");
					break;

				default:
					writer.print("Bad request 400!");
					break;
				}

				/* Enviando resposta */
				writer.flush();
				socket.close();
			}
//			server.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
