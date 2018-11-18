import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.net.UnknownHostException;

public class ClienteChat{

	private  String nickname;
	private  Socket conexionCliente;
	private  Scanner scanner = new Scanner(System.in);
	private  PrintWriter outputStream;
	private  BufferedReader inputStream;
	private  HebritaClienteChat escuchaServidor;


	public void envioMensajeServidor(String mensajeAEnviar){
		outputStream.println(mensajeAEnviar);
	}

	public BufferedReader getInputStream(){
		return inputStream;
	}

	public void procesamientoEntradaUsuario(){
		String entrada = "";
		String[] mensaje = {""};
		String codigoMensaje = "";
		String mensajeAEnviar = "";
		boolean salir = true;


		do{
			entrada = scanner.next();
			mensaje = entrada.split("-");
			codigoMensaje = mensaje[0];
			switch(codigoMensaje){
				case ViceChatOpciones.ELEGIR_SALA:
					mensajeAEnviar = ViceChatProtocolo.VICE_ENTER_ROOM + "#" + mensaje[1];
					envioMensajeServidor(mensajeAEnviar);
					break;
				case ViceChatOpciones.SALIR:
					salir = true;
					break;
			}
		}while(!salir);
	}

	public void recepcionMensajeServidor(String mensajeProtocolo){

	}

	public static void main(String args[]){
		
		String mensajeProtocolo = "";
		ClienteChat cliente = new ClienteChat();
		HebritaClienteChat hebraEscuchaServidor = new HebritaClienteChat(cliente);

		if(args.length != 1){
			System.err.println("El programa necesita el nombre de usuario.");
		}else{
			try{
				cliente.conexionCliente = new Socket("localhost", 8888);
				cliente.outputStream = new PrintWriter(cliente.conexionCliente.getOutputStream(),true);
				cliente.inputStream = new BufferedReader( new InputStreamReader(cliente.conexionCliente.getInputStream()));
				cliente.nickname = args[0];
				hebraEscuchaServidor.start();
				cliente.outputStream.println(ViceChatProtocolo.VICE_CONN + "#" + cliente.nickname);
				cliente.procesamientoEntradaUsuario();
			}catch(IOException e){
				System.err.println("Error al conectar.");
			}
		}
	}
}