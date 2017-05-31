package tabuSearch;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.swing.text.html.MinimalHTMLWriter;

import TFG.Cliente;

public class Tools {

	/**
	 * Cost or fitness of an alldifferent constraint
	 * @param sol
	 * @return
	 */
	private static int costAllDifferent(int[] sol) {
		
		int n = 0;
		
		for(int i=0;i<Cliente.vuelos.size();i++){
			for(int j=0;j<Cliente.vuelos.size();j++){
				if (i!=j && sol[i]==sol[j]){
					int minutos=Cliente.vuelos.get(i).isTimeOverLap(Cliente.vuelos.get(j));
					if (minutos<=15)n+=5000;
					else n+=minutos;
				}
			}
		}
		return n;
	}
	
	private static int minimizarTrasbordos(int[] sol) {
		int minutos=0;
		for (int a=0;a<Cliente.vuelos.size();a++){
			Hashtable<String, String> trasbordos=Cliente.vuelos.get(a).getTrasbordos();
			if (trasbordos!=null){
			Enumeration<String> values = trasbordos.elements();
			while (values.hasMoreElements()) {
				int num=Integer.parseInt(values.nextElement());
				for (int j=0;j<Cliente.puertas.size();j++){
					if(sol[a]==j){//si la puerta es la de la solucion se calcula la distancia de esa puerta a las demas 
						List<Double> distancias=Cliente.puertas.get(j).getDistanciaPuertas();
						for (int z=0;z<distancias.size();z++){
							minutos+=(int) (distancias.get(z)/28.8)*num;
						}
					}
				}
			}
			}
		}
		
		return minutos;
	}

	// Fitness of a solution for the n-queens problem
	public static int fitness(int[] sol) {
		int n = 0;

		// allDifferent on Q
		n += costAllDifferent(sol);
		
		n += minimizarTrasbordos(sol);
		/*
		// allDifferent on y
		int[] aux = new int[sol.length];
		for (int i=0; i<sol.length; ++i) {
			aux[i] = sol[i] + i;
		}
		n += costAllDifferent(aux);

		// allDifferent on z
		for (int i=0; i<sol.length; ++i) {
			aux[i] = sol[i] - i;
		}
		n += costAllDifferent(aux);
	*/
		return n;
	}



}
