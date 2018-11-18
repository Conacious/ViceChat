import java.io.IOException;
public class HebritaClienteChat extends Thread{

	private ClienteChat clienteHebra;
	public HebritaClienteChat(ClienteChat cliente){
		clienteHebra = cliente;
	}

	public void run(){
		String mensajeProtocolo = "";
		try{
			do{
				mensajeProtocolo = clienteHebra.getInputStream().readLine();
				if(mensajeProtocolo != null){
					String[] contenido = mensajeProtocolo.split("#");
					switch(contenido[0]){
						case ViceChatProtocolo.VICE_SHOW_ROOMS:
								System.out.println("------------------------");
								for(int i = 1; i < contenido.length; i++){
									System.out.println(contenido[i]);
								}
								System.out.println("------------------------");
							break;
						case ViceChatProtocolo.VICE_ENTER_ROOM:
							//Runtime.getRuntime().exec("clear");
							if(contenido.length > 1){
								System.out.println("**USUARIOS EN LA SALA**");

								for(int i = 1; i < contenido.length-1; i++){
									System.out.print(contenido[i] + ",");
								}
								System.out.println(contenido[contenido.length-1]);
								System.out.println("************************");
							}else{
								System.out.println("La sala esta vacia. Bienvenido.");
							}
							break;
						case ViceChatProtocolo.VICE_LEAVE_ROOM:
							System.out.println("------------------------");
							for(int i = 1; i < contenido.length; i++){
								System.out.print(contenido[i]);
							}
							System.out.println("------------------------");
							break;
					}
				}
			}while(true);
		}catch(IOException e){
			System.out.println("Error de lectura desde el stream socket de ClienteChat");
		}
		
	}	

}