import java.util.ArrayList;
public class ControladorChat{

	private ArrayList<HebraChat> clientesConectados = new ArrayList();

	public void aniadirNuevaConexion(HebraChat nuevaConexion){
		clientesConectados.add(nuevaConexion);
	}

}