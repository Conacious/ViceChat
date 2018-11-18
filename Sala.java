import java.util.ArrayList;

public class Sala{

	private String identificadorSala;
	private ArrayList<HebraChat> usuariosSala = new ArrayList();


	public Sala(String nombre){
		identificadorSala = nombre;
	}


	public ArrayList<HebraChat> getUsuariosSala(){
		return usuariosSala;
	}

	public String getIdentificadorSala(){
		return identificadorSala;	
	}

}