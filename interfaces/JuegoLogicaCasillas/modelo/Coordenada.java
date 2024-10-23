package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Coordenada {
	int x;
	int y;
	public  Coordenada(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public static Coordenada aleatoria(int max) {
		return new Coordenada((int)(Math.random()*max),(int)(Math.random()*max)); 
	}
	
	public ArrayList<Coordenada> getAlrededores(){
		ArrayList<Coordenada> alrededores = new ArrayList<Coordenada>();
		alrededores.add(this);
		alrededores.add(new Coordenada(x-1, y));
		alrededores.add(new Coordenada(x, y+1));
		alrededores.add(new Coordenada(x+1, y));
		alrededores.add(new Coordenada(x, y-1));
		return alrededores;
	}
	public static ArrayList<Coordenada> getTablero(int tamaño){
		ArrayList<Coordenada> tablero = new ArrayList<Coordenada>();
		for (int y = 0; y < tamaño; y++) {
			for (int x = 0; x < tamaño; x++) {
				tablero.add(new Coordenada(x, y)); 
			}
		}		
		return tablero;
	}
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenada other = (Coordenada) obj;
		return x == other.x && y == other.y;
	}	
	
}
