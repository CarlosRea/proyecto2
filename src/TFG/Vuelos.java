package TFG;

import java.util.Calendar;
import java.util.Hashtable;


public class Vuelos {

	/**
	 * @uml.property name="Numero"
	 */
	private String numero;

	/**
	 * Getter of the property <tt>Nombre</tt>
	 * 
	 * @return Returns the nombre.
	 * @uml.property name="Numero"
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Setter of the property <tt>Nombre</tt>
	 * 
	 * @param Nombre
	 *            The nombre to set.
	 * @uml.property name="Numero"
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @uml.property name="FechaLlegada"
	 */
	private Calendar fechaLlegada;

	/**
	 * Getter of the property <tt>FechaLlegada</tt>
	 * 
	 * @return Returns the fechaLlegada.
	 * @uml.property name="FechaLlegada"
	 */
	public Calendar getFechaLlegada() {
		return fechaLlegada;
	}

	/**
	 * Setter of the property <tt>FechaLlegada</tt>
	 * 
	 * @param FechaLlegada
	 *            The fechaLlegada to set.
	 * @uml.property name="FechaLlegada"
	 */
	public void setFechaLlegada(Calendar fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	/**
	 * @uml.property name="fechaSalida"
	 */
	private Calendar fechaSalida;

	/**
	 * Getter of the property <tt>fechaSalida</tt>
	 * 
	 * @return Returns the fechaSalida.
	 * @uml.property name="fechaSalida"
	 */
	public Calendar getFechaSalida() {
		return fechaSalida;
	}

	/**
	 * Setter of the property <tt>fechaSalida</tt>
	 * 
	 * @param fechaSalida
	 *            The fechaSalida to set.
	 * @uml.property name="fechaSalida"
	 */
	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	/**
		 */
	public Vuelos() {
		avion = new TFG.Aviones();
	}

	/**
	 * @uml.property name="avion"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="vuelos:TFG.Aviones"
	 */
	private Aviones avion;

	/**
	 * Getter of the property <tt>avion</tt>
	 * 
	 * @return Returns the avion.
	 * @uml.property name="avion"
	 */
	public Aviones getAvion() {
		return avion;
	}

	/**
	 * Setter of the property <tt>avion</tt>
	 * 
	 * @param avion
	 *            The avion to set.
	 * @uml.property name="avion"
	 */
	public void setAvion(Aviones avion) {
		this.avion = avion;
	}

	/**
	 * @uml.property name="puertas"
	 * @uml.associationEnd inverse="vuelos:TFG.Puertas"
	 */
	private Puertas puerta;

	/**
	 * Getter of the property <tt>puertas</tt>
	 * 
	 * @return Returns the puertas.
	 * @uml.property name="puertas"
	 */
	public Puertas getPuertas() {
		return puerta;
	}

	/**
	 * Setter of the property <tt>puertas</tt>
	 * 
	 * @param puertas
	 *            The puertas to set.
	 * @uml.property name="puertas"
	 */
	public void setPuertas(Puertas puertas) {
		puerta = puertas;
	}

	/**
	 * @uml.property name="aerolinea"
	 */
	private String aerolinea;

	/**
	 * Getter of the property <tt>aerolinea</tt>
	 * 
	 * @return Returns the aerolinea.
	 * @uml.property name="aerolinea"
	 */
	public String getAerolinea() {
		return aerolinea;
	}

	/**
	 * Setter of the property <tt>aerolinea</tt>
	 * 
	 * @param aerolinea
	 *            The aerolinea to set.
	 * @uml.property name="aerolinea"
	 */
	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}
	
	private Hashtable<String, String> trasbordos;

	@Override
	public String toString() {
		String tras=trasbordos!=null?trasbordos.toString():"";
		return "Vuelos [numero de vuelo=" + numero + ", fecha de Llegada="
				+ fechaLlegada.getTime() + ", fecha de salida= "+fechaSalida.getTime()+ "]-- Trasbordos [ "+ tras +"]\n";
	}
	
	/**
	 * funcion que retorna el tiempo en milisegundos, convertido a minutos de el tiempo que hay 
	 * entre el vuelo que llega a la puerta y el que sale de la puerta
	 * @param vuelo
	 * @return
	 */
	public int isTimeOverLap(Vuelos vuelo) {
		int minutos=(int) (((vuelo.fechaLlegada.getTimeInMillis())-(this.fechaSalida.getTimeInMillis()))/60000);

		//System.out.println(vuelo.fechaLlegada.getTime() +"- " + this.fechaSalida.getTime()+"- "+minutos);
		return minutos;
	}

	public Hashtable<String, String> getTrasbordos() {
		return trasbordos;
	}

	public void setTrasbordos(Hashtable<String, String> trasbordo) {
		this.trasbordos = trasbordo;
	}

}
