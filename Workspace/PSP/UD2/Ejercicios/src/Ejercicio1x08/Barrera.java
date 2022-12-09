package Ejercicio1x08;

public class Barrera {
/*
 * Monitor (tendra metodos syncronizados)
 * entrada,salida y mostrar
 * 
 * vector para plazas y atributo auxiliar para ver huecos libres(dato-- cuando entre uno nuevo)
 * 
 * entrar=wait
 * sair= notifyall
 */
	private int plazas[];
	private int libres;
	
	Barrera(int nPlazas){
		plazas = new int[nPlazas];
		libres=nPlazas;
	}
	
	synchronized public int entrada(int coche) throws InterruptedException{
		int plaza=0;
		while (libres == 0) {
			System.out.println("Coche "+coche+" esperando");
			wait();
		}
		while(plazas[plaza] != 0) {
			plaza++;
		}
		plazas[plaza]=coche;
		libres--;		
		return plaza;
	}
	
	synchronized public void salida (int plaza) {
		plazas[plaza] = 0; // vuelve el valor de la plaza que queda vacia a 0
		libres++;
		notify();
	}
	
	synchronized public void mostrar() {
		String salida = "Coche ";
		for(int i=0;i<plazas.length;i++) {
			salida+=("["+plazas[i]+"]");
		}
		System.out.println(salida);
	}
	
	/*
	 * Run:
	 * entrar// como son sync si no puede se quedara pillado
	 * mostrar
	 * salir
	 */
}
