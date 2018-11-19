import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class HebraChat extends Thread {

	private ControladorChat controladorChat;
	private Socket conexionCliente;
	private PrintWriter outputStream;
	private BufferedReader inputStream;

	private String nickname;
	private String salaActual;

	public String getNickname() {
		return nickname;
	}

	public PrintWriter getOutputStream() {
		return outputStream;
	}

	public HebraChat(Socket conCliente, ControladorChat controladorChat) throws IOException {
		conexionCliente = conCliente;
		outputStream = new PrintWriter(conexionCliente.getOutputStream(), true);
		inputStream = new BufferedReader(new InputStreamReader(conexionCliente.getInputStream()));
		this.controladorChat = controladorChat;
		salaActual = null;
	}

	public void run() {
		String mensajeProtocolo = "";
		String[] mensajeContenido = { "" };
		String protocolo = "";
		try {
			do {
				mensajeProtocolo = inputStream.readLine();
				if (mensajeProtocolo != null) {
					String[] contenido = mensajeProtocolo.split("#");
					switch (contenido[0]) {
					// Primer contacto cliente-servidor. Mostramos Opciones del menu.
					case ViceChatProtocolo.VICE_CONN:
						String nombresSalas = ViceChatProtocolo.VICE_SHOW_ROOMS;
						nickname = contenido[1];
						nombresSalas += controladorChat.getSalasDisponibles();
						outputStream.println(nombresSalas);
						break;
					case ViceChatProtocolo.VICE_ENTER_ROOM:
						String nombreSala = contenido[1];
						System.out.println(contenido[1]);
						String listaUsuarios = ViceChatProtocolo.VICE_ENTER_ROOM + "#";
						String[] usuariosSala = controladorChat.getUsuariosSala(nombreSala);
						salaActual = nombreSala;
						controladorChat.aniadirASala(this, nombreSala);
						if (usuariosSala != null)
							for (String usuario : usuariosSala) {
								listaUsuarios += usuario + "#";
								System.out.println(usuario);
							}
						outputStream.println(listaUsuarios);
						break;
					case ViceChatProtocolo.VICE_LEAVE_ROOM:

						/*
						 * String refrescarSalas = ViceChatProtocolo.VICE_LEAVE_ROOM + "#";
						 * controladorChat.quitarUsuarioSala(salaActual, this); refrescarSalas +=
						 * controladorChat.getSalasDisponibles(); outputStream.println(refrescarSalas);
						 */

						String salass = ViceChatProtocolo.VICE_SHOW_ROOMS;
						salass += controladorChat.getSalasDisponibles();
						controladorChat.quitarUsuarioSala(salaActual, this);
						outputStream.println(salass);

						break;
					case ViceChatProtocolo.VICE_MSG:
						controladorChat.mandarMensajeSala(salaActual, this, contenido[1]);
						break;
					case ViceChatProtocolo.VICE_SHOW_USER:
						String listaUsers = ViceChatProtocolo.VICE_ENTER_ROOM + "#";
						String[] usuariosSa = controladorChat.getUsuariosSala(salaActual);

						controladorChat.aniadirASala(this, salaActual);
						if (usuariosSa != null)
							for (String usuario : usuariosSa) {
								listaUsers += usuario + "#";
								System.out.println(usuario);
							}
						outputStream.println(listaUsers);
						break;
					}
				}
			} while (true);
		} catch (IOException e) {
			System.out.println("Error de lectura/escritura.");
		}
	}
}
