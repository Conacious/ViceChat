import java.util.ArrayList;

public class ControladorChat {

	private ArrayList<HebraChat> clientesConectados = new ArrayList();
	private ArrayList<Sala> salasDisponibles = new ArrayList();

	public ControladorChat() {
		Sala salaAuxiliar;

		salaAuxiliar = new Sala("Juegos");
		salasDisponibles.add(salaAuxiliar);
		salaAuxiliar = new Sala("Política");
		salasDisponibles.add(salaAuxiliar);
		salaAuxiliar = new Sala("Etsiit");
		salasDisponibles.add(salaAuxiliar);

	}

	public void aniadirNuevaConexion(HebraChat nuevaConexion) {
		clientesConectados.add(nuevaConexion);
	}

	public String getSalasDisponibles() {
		String nombresSalas = "";
		for (Sala sala : salasDisponibles) {
			nombresSalas += ("#" + sala.getIdentificadorSala() );
		}
		return nombresSalas;
	}

	public void aniadirASala(HebraChat usuario, String salaNombre) {
		for (Sala sala : salasDisponibles) {// REEMPLAZAR CON CONTAINS
			if (sala.getIdentificadorSala().equals(salaNombre)) {
				sala.aniadirUsuarioASala(usuario);
			}
		}
	}

	public void quitarUsuarioSala(String nombreSala, HebraChat usuario) {
		for (Sala sala : salasDisponibles) { // REEMPLAZAR CON CONTAINS
			if (sala.getIdentificadorSala().equals(nombreSala)) {
				sala.borrarUsuarioSala(usuario);
			}
		}
	}

	public void mandarMensajeSala(String nombreSala, HebraChat usuario, String mensaje) {
		for (Sala sala : salasDisponibles) { // REEMPLAZAR CON CONTAINS
			if (sala.getIdentificadorSala().equals(nombreSala)) {
				for (HebraChat usuarioSala : sala.getUsuariosSala()) {
					if (!usuarioSala.getNickname().equals(usuario.getNickname())) {
						usuarioSala.getOutputStream().println(ViceChatProtocolo.VICE_MSG + "#" + mensaje);
					}
				}
			}
		}
	}

	public Sala getSala(String nombreSala) {
		Sala salaEncontrada = null;
		for (Sala sala : salasDisponibles) {
			if (sala.getIdentificadorSala().equals(nombreSala)) {
				salaEncontrada = sala;
				break;
			}
		}
		return salaEncontrada;
	}

	public String[] getUsuariosSala(String nombreSala) {
		String[] nombresUsuarios = null;
		System.out.println(nombreSala);
		Sala sala = null;
		if ((sala = getSala(nombreSala)) != null) {
			System.out.println("Sala existente");
			ArrayList<HebraChat> usuarios = sala.getUsuariosSala();
			nombresUsuarios = new String[usuarios.size()];
			System.out.println("Tamaño de usuarios en sala es de " + Integer.toString(usuarios.size()));
			for (int i = 0; i < nombresUsuarios.length; i++) {
				nombresUsuarios[i] = usuarios.get(i).getNickname();
				System.out.println("ENTRO EN BUCLE " + nombresUsuarios[i]);
			}
		}
		return nombresUsuarios;
	}

}