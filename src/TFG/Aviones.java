package TFG;


public class Aviones {

	/**
	 * @uml.property  name="modelo"
	 */
	private String modelo;

	/**
	 * Getter of the property <tt>modelo</tt>
	 * @return  Returns the modelo.
	 * @uml.property  name="modelo"
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Setter of the property <tt>modelo</tt>
	 * @param modelo  The modelo to set.
	 * @uml.property  name="modelo"
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @uml.property  name="pasajeros"
	 */
	private String pasajeros;

	/**
	 * Getter of the property <tt>pasajeros</tt>
	 * @return  Returns the pasajeros.
	 * @uml.property  name="pasajeros"
	 */
	public String getPasajeros() {
		return pasajeros;
	}

	/**
	 * Setter of the property <tt>pasajeros</tt>
	 * @param pasajeros  The pasajeros to set.
	 * @uml.property  name="pasajeros"
	 */
	public void setPasajeros(String pasajeros) {
		this.pasajeros = pasajeros;
	}

		
		/**
		 */
		public Aviones(){
		}

		@Override
		public String toString() {
			return "Aviones [Modelo de avion =" + modelo + ", numero de asientos=" + pasajeros
					+ "]\n";
		}



}
