
public class Compartido {
	private int numero;
	private boolean disponible = false; //cola vac�a
	
	public int get(){
		if(disponible){
			disponible = false;
			return numero;
		}
		return -1;
	}
	
	public void set (int n){
		numero = n;
		disponible = true;
	}
}
