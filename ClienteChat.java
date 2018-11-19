import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import java.net.UnknownHostException;

public class ClienteChat {

	private String nickname;
	private Socket conexionCliente;
	private Scanner scanner = new Scanner(System.in);
	private PrintWriter outputStream;
	private BufferedReader inputStream;
	private HebritaClienteChat escuchaServidor;

	public void getInfoChat() {

		System.out.println("Bienvenido a ViceChat. A continuación se muestran las diferentes opciones");
		System.out.println("Entrar sala existente: 	E-NombreSala");
		System.out.println("Salir de la sala: 	S-NombreSala");
		System.out.println("Enviar mensaje a la sala: M-MensajeAEnviar");
		System.out.println("Listar usuarios en la sala: L-NombreSala");
		System.out.println("Salir de la aplicación: C");

		/*
		 * String infoChat = ""; infoChat +=
		 * "Bienvenido a ViceChat. A continuación se muestran las diferentes opciones \n"
		 * ; infoChat += "Entrar sala existente: 		E-NombreSala \n"; infoChat +=
		 * "Salir de la sala: 			S-NombreSala \n"; infoChat +=
		 * "Enviar mensaje a la sala: 	M-MensajeAEnviar \n"; infoChat +=
		 * "Listar usuarios en la sala: L-NombreSala \n"; infoChat +=
		 * "Salir de la aplicación:     C \n";
		 * 
		 * return infoChat;
		 */
	}

	public void envioMensajeServidor(String mensajeAEnviar) {
		outputStream.println(mensajeAEnviar);
	}

	public BufferedReader getInputStream() {
		return inputStream;
	}

	public void procesamientoEntradaUsuario() {
		String entrada = "";
		String[] mensaje = { "" };
		String codigoMensaje = "";
		String mensajeAEnviar = "";
		boolean salir = false;

		do {
			scanner = new Scanner(System.in);
			entrada = scanner.nextLine();
			mensaje = entrada.split("-");
			codigoMensaje = mensaje[0];

			//System.out.println(codigoMensaje);
			switch (codigoMensaje) {
			case ViceChatOpciones.ELEGIR_SALA:
				mensajeAEnviar = ViceChatProtocolo.VICE_ENTER_ROOM + "#" + mensaje[1];
				envioMensajeServidor(mensajeAEnviar);
				break;
			case ViceChatOpciones.SALIR:
				salir = true;
				break;
			case ViceChatOpciones.MENSAJE:
				mensajeAEnviar = ViceChatProtocolo.VICE_MSG + "#" + mensaje[1];
				envioMensajeServidor(mensajeAEnviar);
				break;
			case ViceChatOpciones.LISTA_USUARIOS:
				mensajeAEnviar = ViceChatProtocolo.VICE_SHOW_USER + "#";
				if(mensaje.length > 1){
					mensajeAEnviar += mensaje[1];
				}
				envioMensajeServidor(mensajeAEnviar);
				break;
			case ViceChatOpciones.SALIR_SALA:
				mensajeAEnviar = ViceChatProtocolo.VICE_LEAVE_ROOM + "#";
				if(mensaje.length > 1){
					mensajeAEnviar += mensaje[1];
				}
				envioMensajeServidor(mensajeAEnviar);
				break;

			}
		} while (!salir);
		
	}

	public void recepcionMensajeServidor(String mensajeProtocolo) {

	}

	public static void main(String args[]) {

		String mensajeProtocolo = "";
		ClienteChat cliente = new ClienteChat();
		HebritaClienteChat hebraEscuchaServidor = new HebritaClienteChat(cliente);

		if (args.length != 1) {
			System.err.println("El programa necesita el nombre de usuario.");
		} else {
			try {
				cliente.getInfoChat();
				cliente.conexionCliente = new Socket("localhost", 8888);
				cliente.outputStream = new PrintWriter(cliente.conexionCliente.getOutputStream(), true);
				cliente.inputStream = new BufferedReader(
						new InputStreamReader(cliente.conexionCliente.getInputStream()));
				cliente.nickname = args[0];
				hebraEscuchaServidor.start();
				cliente.outputStream.println(ViceChatProtocolo.VICE_CONN + "#" + cliente.nickname);
				cliente.procesamientoEntradaUsuario();
				cliente.conexionCliente.close();
			} catch (IOException e) {
				System.err.println("Error al conectar.");
			}
		}
	}
}