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


	public String getNickname(){
		return nickname;
	}


	public HebraChat(Socket conCliente, ControladorChat controladorChat) throws IOException{
		conexionCliente = conCliente;
		outputStream = new PrintWriter(conexionCliente.getOutputStream(),true);
		inputStream = new BufferedReader( new InputStreamReader(conexionCliente.getInputStream()));
		this.controladorChat = controladorChat;
	}

	public void run(){
		String mensajeProtocolo = "";
		String[] mensajeContenido = {""};
		String protocolo = "";
		try{
			do{
				mensajeProtocolo = inputStream.readLine();
				String[] contenido = mensajeProtocolo.split("#");

				switch(contenido[0]){
					case ViceChatProtocolo.VICE_CONN:
						String nombresSalas = ViceChatProtocolo.VICE_SHOW_ROOMS;
						nickname = contenido[1];
						//System.out.println(nombresSalas);
						nombresSalas += controladorChat.getSalasDisponibles();
						outputStream.println(nombresSalas);
						break;

				}

			}while(true);
		}catch(IOException e){

		}
	}
}




	/*	byte []buferEnvio = "eh".getBytes();
		byte []buferRecepcion=new byte[256];
		int bytesLeidos=0;
		try{
			for(int i=-1; i < 5; i++){
				outputStream.println("eh");
				Thread.sleep(3000);
			}
			conexionCliente.close();
		}catch(Exception e){
			System.err.println("Err: " + e);
		}
	}*/
