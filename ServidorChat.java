import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ServidorChat{

	private static final ServidorChat servidorChat = new ServidorChat();
	private ControladorChat controladorChat = new ControladorChat();
	//private final int puerto;

	private ServidorChat(){};
	
	public static ServidorChat getServidorChatInstance(){
		return servidorChat;
	}

	public ControladorChat getControladorChat(){
		return controladorChat;
	}

	public static void main(String[] args){
		ServidorChat servidorChat = ServidorChat.getServidorChatInstance();
		ControladorChat controladorChat = servidorChat.getControladorChat();
		HebraChat hebraChat;
		try {
			ServerSocket socketServicio = new ServerSocket(Integer.parseInt(args[0]));
			do {
				Socket socketCliente = socketServicio.accept();
				hebraChat = new HebraChat(socketCliente, servidorChat.getControladorChat());
				controladorChat.aniadirNuevaConexion(hebraChat);
				hebraChat.start();
			} while(true);
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto.");
		}
	}
}