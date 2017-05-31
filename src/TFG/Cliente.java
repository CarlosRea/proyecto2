package TFG;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;

import TFGUI.interfaz;
import tabuSearch.BestCandidate;
import tabuSearch.NeighborHood;
import tabuSearch.TabuList;


public class Cliente {
	public static List<Vuelos> vuelos = new ArrayList<Vuelos>();
	public static List<Aviones> aviones = new ArrayList<Aviones>();
	public static List<Puertas> puertas = new ArrayList<Puertas>();
	
	/**
	 */
	public Cliente(){
		System.out.println(vuelos.size());
		System.out.println(puertas.size());
		
	}
	
	/**
	 * 
	 * @return tab The domain of the main variables
	 */
	public ArrayList<Integer[]> getDomains() {
		ArrayList<Integer[]> domains=new ArrayList<Integer[]>();
		
		for (int i=0; i<vuelos.size(); ++i) {
			Integer[] tab = new Integer[puertas.size()];
			for (int j=0;j<puertas.size();j++){
				tab[j] = j; 
			}
			domains.add(tab);
		}
		/*
		for (Integer[] dom:domains){
			for(int i=0;i<dom.length;i++){
				System.out.print(dom[i]);
			}
			System.out.println("/");
		}
		*/
		return domains;
	}

	/**
	 * Generate randomly a solution within the domains
	 * @param domains The domain in which we generate randomly a solution
	 * @return solution The generated ramdomly solution
	 */
	public int[] generateSolution(ArrayList<Integer[]> domains) {
		Random rand = new Random();
		int[] solution = new int[domains.size()];
		int j=0;
		for (int i=0; i<domains.size(); ++i) {
			//Integer[] values = domains.get(i);
			//int r = rand.nextInt(domains.get(i).length);   // 0 .. getSize()-1
			if (j==domains.get(i).length)
				j=0;
			solution[i]=domains.get(i)[j];
			j++;
			/*for (int j=0; j<=r; ++j) {
				solution[i] = values.nextElement();  // only the r-th is relevant
			}*/
		}

		//Print for display
		System.out.print("Generated Solution : [" + solution[0]);
		
		for (int i = 1; i<solution.length ;i++) {
			System.out.print(", ");
			System.out.print(solution[i]);
		}
		System.out.print("]");
		System.out.println("\n");
		
		return solution;
	}

	/**
	 * Cost or fitness of an alldifferent constraint
	 * @param sol
	 * @return
	 */
	public int minimizarRetrazos(int[] sol) {
		
		int n = 0;
		
		for(int i=0;i<vuelos.size();i++){
			for(int j=0;j<vuelos.size();j++){
				if (i!=j && sol[i]==sol[j]){
					int minutos=vuelos.get(i).isTimeOverLap(vuelos.get(j));
					if (minutos<=15)n+=5000;
					else n+=minutos;
				}
			}
		}
		
		return n;
	}

	// Fitness of a solution for the n-queens problem
	public int fitness(int[] sol) {
		int n = 0;
		
		// allDifferent on Q
		n += minimizarRetrazos(sol);
		n += minimizarTrasbordos(sol);

		return n;
	}
	/*
	private int minimizarTrasbordos(int[] sol) {
		int minutos=0;
		for (int a=0;a<vuelos.size();a++){
			Integer [] trasbordos=vuelos.get(a).getTrasbordos();
			for(int i=0;i<trasbordos.length;i++){
				if (trasbordos[i]!=null){
					for (int j=0;j<puertas.size();j++){
						if(sol[a]==j){//si la puerta es la de la solucion se calcula la distancia de esa puerta a las demas 
							List<Double> distancias=puertas.get(j).getDistanciaPuertas();
							for (int z=0;z<distancias.size();z++){
								minutos+=(int) (distancias.get(z)/28.8)*trasbordos[i];
							}
						}
					}
				}
			}
		}
		
		return minutos;
	}
	*/
	
	private static int minimizarTrasbordos(int[] sol) {
		int minutos=0;
		for (int a=0;a<vuelos.size();a++){
			Hashtable<String, String> trasbordos=vuelos.get(a).getTrasbordos();
			if (trasbordos!=null){
			Enumeration<String> values = trasbordos.elements();
			while (values.hasMoreElements()) {
				int num=Integer.parseInt(values.nextElement());
				for (int j=0;j<puertas.size();j++){
					if(sol[a]==j){//si la puerta es la de la solucion se calcula la distancia de esa puerta a las demas 
						List<Double> distancias=puertas.get(j).getDistanciaPuertas();
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

	// Display a solution
	public static void printSolution(int[] sol) {
		System.out.print("{");
		for (int i=0; i<sol.length; ++i) {
			if (i!=0) System.out.print(", ");
			System.out.print(sol[i]);
		}
		System.out.print("}");
	}


	public boolean tabuSearch(int maxIteration, int tabuSize) {
		
		// Generate a first solution
		ArrayList<Integer[]> domains = getDomains();
		
		int[] currentSol = generateSolution(domains);
		int[] bestSol = currentSol; //best known solution
		TabuList tabuList = new TabuList(tabuSize);

		//Cost of the current solution
		int currentCost = fitness(currentSol);
		int bestCost = currentCost;
		int nbIte = 0; 
		
		while(currentCost > 0 && nbIte < maxIteration){
			
			nbIte++;
			
			NeighborHood neighborHood = new NeighborHood(currentSol, domains);
			
			//Verificaciones. se crea un nuevo vecindario, apartir del dominio y la solucion actual.
			try{
				neighborHood.determineNeighboor();
			}
			catch(Exception e){
				return false;
			}
			
			//reduce el actual vecindario, quitando elementos que estan contenidos en la lista tabu.
			neighborHood.reduceNeighborHood(tabuList);
			
			//Get BestCandidate from Neighborhood class
			//obtiene el mejor candidato del vecindario actual.
			BestCandidate bestCandidate = neighborHood.getBestPossibleSolution();
			
			currentCost = bestCandidate.getCost();
			currentSol = bestCandidate.getSolution();
			
			//Add the move to tabuList
			tabuList.addTabuElement(bestCandidate.getMove());
			
			if(currentCost < bestCost)
			{
				bestSol = currentSol;
				bestCost = currentCost;
			}
			System.out.println("*****************************************************************************");
			System.out.println("La mejor solución: " + Arrays.toString(bestSol));
			System.out.println("Costo : " + bestCost);
			System.out.println("Número de iteraciones: " + nbIte);
		}

		System.out.println("Solution Finale : " + Arrays.toString(bestSol));
		
		for(int i=0;i<bestSol.length;i++){
			vuelos.get(i).setPuertas(puertas.get(bestSol[i]));
		}
		
		return bestCost == 0;

	}
	

	public static void main(String[] args) {
		//leemos los datos 
		leerVuelos();
		leerAviones();
		leerPuertas();
		
		calcularDistancias();
		asignarAviones();
		asignarFechaSalida();
		calcularTrasbordos();
		
		Cliente model=new Cliente();

		int maxIteration = 100;
		int tabuSize = 10;

		boolean result = model.tabuSearch(maxIteration, tabuSize);
		if(result){
			System.out.println("Una solución óptima se ha encontrado!");
		}
		else {
			System.out.println("Una solución no óptima se ha encontrado!");
		}
		//asignarvuelos();
		
		for(Vuelos vuelo:vuelos){
			System.out.println("-----------------------------------------------------------------------------");
			System.out.print(" numero de vuelo : "+vuelo.getNumero()+ " // numero de puerta "+vuelo.getPuertas());
			System.out.print(vuelo);
		}
		
		interfaz demo = new interfaz("Asignacion de puertas a vuelos",
				"Asignacion de vuelos");
		demo.pack();
		demo.setVisible(true);
		
	}

	private static void calcularTrasbordos() {
		int iter=vuelos.size()/15;
		for (int x=0;x<vuelos.size()-(vuelos.size()/5);x++) {
			Random rand = new Random();
			Hashtable<String,String> trasbordo=new Hashtable<String,String>();
			for (int i=0;i<iter;i++){
				int r = (int)(Math.random()*((vuelos.size()-1)-iter+1)+iter);   // 0 .. getSize()-1
				int num=(int)(Math.random()*(50-10+1)+10);
				trasbordo.put(vuelos.get(r).getNumero(), String.valueOf(num));
			}
			vuelos.get(x).setTrasbordos(trasbordo);
		}
	}

	private static void asignarFechaSalida() {
		for(Vuelos vuelo:vuelos){
			/*Date date=new Date();
			date=(Date)vuelo.getFechaLlegada().clone();
			date.setTime(45);
*/
			//System.out.println(vuelo.getFechaLlegada().getTime());
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(vuelo.getFechaLlegada().getTimeInMillis()+(3600*850));
			vuelo.setFechaSalida(calendar);
			
			//System.out.println(vuelo.getFechaLlegada().getTime()+" - "+vuelo.getFechaSalida().getTime());
		}
		
	}

	private static void asignarAviones() {
		for(Vuelos vuelo:vuelos){
			int aleatorio = (int) (Math.random()*aviones.size());
			vuelo.setAvion(aviones.get(aleatorio));
		}
		
	}

	private static void calcularDistancias() {

		for (Puertas puerta : puertas) {
			List distancias = new ArrayList<Double>();
			for (Puertas p : puertas) {
				if (!puerta.equals(p)) {
					Double distancia =10* Math
							.sqrt(Math.pow(
									puerta.getPosicionX() - p.getPosicionX(), 2)
									+ Math.pow(
											puerta.getPosicionY()
													- p.getPosicionY(), 2));
					distancias.add(distancia);
				}
			}
			puerta.setDistanciaPuertas(distancias);
		}

	}

	private static void leerVuelos() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(
					"C:/Users/carlos/workspaceAndroid/TFG/src/TFG/ficheros/llegadas2.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			int count = 0;
			Vuelos vuelo = new Vuelos();
			// Lectura del fichero
			String linea;
			while ((linea = br.readLine()) != null) {

				if (linea.contains("Delayed") || linea.contains("Unknown")
						|| linea.contains("A tiempo")) {
					count = 0;
					vuelo = new Vuelos();
				}

				switch (count++) {
				case 1:
					vuelo.setNumero(linea);
					;
					break;
				case 2:
					vuelo.setAerolinea(linea);
					;
					break;
				case 3:
					/*java.sql.Time fecFormatoTime = null;
					try {
						SimpleDateFormat sdf = new java.text.SimpleDateFormat(
								"hh:mm", new Locale("es", "ES"));
						fecFormatoTime = new java.sql.Time(sdf.parse(linea)
								.getTime());
						vuelo.setFechaLlegada(fecFormatoTime);
					} catch (Exception ex) {
						System.out
								.println("Error al obtener el formato de la fecha/hora: "
										+ ex.getMessage());
					}*/

					SimpleDateFormat curFormater = new SimpleDateFormat("hh:mm aa"); 
					Date dateObj = curFormater.parse(linea);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(dateObj);
					vuelo.setFechaLlegada(calendar);
					vuelos.add(vuelo);
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	private static void leerAviones() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File("C:/Users/carlos/workspaceAndroid/TFG/src/TFG/ficheros/aviones.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			// Lectura del fichero
			Aviones avion = new Aviones();
			String linea;
			while ((linea = br.readLine()) != null) {

				if (linea.contains("Asiento")) {
					avion.setPasajeros(linea.substring(10));
					aviones.add(avion);
				} else if (linea.isEmpty())
					avion = new Aviones();
				else
					avion.setModelo(linea);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	private static void leerPuertas() {

		File f = new File("C:/Users/carlos/workspaceAndroid/TFG/src/TFG/ficheros/puertas1.txt");
		Scanner s;

		try {
			s = new Scanner(f);
			int count = 0;
			String terminal = null;
			while (s.hasNextLine()) {
				String cadena = s.nextLine();
				StringTokenizer token = new StringTokenizer(cadena);
				// elimina lineas en blanco
				int n = token.countTokens();
				Puertas puerta = new Puertas();
				if (n != 0) {
					while (token.hasMoreTokens()) {
						String c = token.nextToken();
						if (c.contains("T")) {
							terminal = c;
							break;
						}
						switch (count) {
						case 0: {
							puerta.setTerminal(terminal);
							puerta.setNumero(c);
							count++;
							break;
						}
						case 1: {
							puerta.setPosicionX(Integer.valueOf(c));
							count++;
							break;
						}
						case 2: {
							puerta.setPosicionY(Integer.valueOf(c));
							count = 0;
							puertas.add(puerta);
							break;
						}
						}
					}
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
