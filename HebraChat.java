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

	public HebraChat(Socket conCliente) throws IOException{
		conexionCliente = conCliente;
		outputStream = new PrintWriter(conexionCliente.getOutputStream(),true);
		inputStream = new BufferedReader( new InputStreamReader(conexionCliente.getInputStream()));
	}

	public void run(){
		byte []buferEnvio = "eh".getBytes();
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
	}
}
