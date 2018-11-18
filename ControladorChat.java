import java.util.ArrayList;
public class ControladorChat{

	private ArrayList<HebraChat> clientesConectados = new ArrayList();
	private ArrayList<Sala> salasDisponibles = new ArrayList();

	public ControladorChat(){
		Sala salaAuxiliar;

		salaAuxiliar = new Sala("Juegos");
		salasDisponibles.add(salaAuxiliar);
		salaAuxiliar = new Sala("Política");
		salasDisponibles.add(salaAuxiliar);
		salaAuxiliar = new Sala("Etsiit");
		salasDisponibles.add(salaAuxiliar);

	}

	public void aniadirNuevaConexion(HebraChat nuevaConexion){
		clientesConectados.add(nuevaConexion);
	}

	public String getSalasDisponibles(){
		String nombresSalas = "";
//		System.out.println(salasDisponibles.size());
		for(Sala sala : salasDisponibles){
			nombresSalas += ("#" + sala.getIdentificadorSala() );
	//		System.out.println(nombresSalas);
		}
		return nombresSalas;
	}

}