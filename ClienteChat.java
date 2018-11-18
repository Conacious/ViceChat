import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteChat{

	private static String nickname;
	private static Socket conexionCliente;


	public static void main(String args[]){
		String recibido = "";
		try{
			conexionCliente = new Socket("localhost", 8888);
			PrintWriter outPrinter = new PrintWriter(conexionCliente.getOutputStream(),true);
			BufferedReader inputStream = new BufferedReader( new InputStreamReader(conexionCliente.getInputStream()));

			while((recibido = inputStream.readLine()) != null){
		
			}

			conexionCliente.close();
		}catch(IOException e){
			System.err.println("Error al conectar.");
		}
	}
}