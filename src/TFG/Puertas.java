package TFG;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Puertas {

	/**
	 * @uml.property  name="numero"
	 */
	private String numero;

	/**
	 * Getter of the property <tt>numero</tt>
	 * @return  Returns the numero.
	 * @uml.property  name="numero"
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Setter of the property <tt>numero</tt>
	 * @param numero  The numero to set.
	 * @uml.property  name="numero"
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/** 
	 * @uml.property name="tiempoFuncionamiento"
	 */
	private int tiempoFuncionamiento;

	/** 
	 * Getter of the property <tt>tiempoFuncionamiento</tt>
	 * @return  Returns the tiempoFuncionamiento.
	 * @uml.property  name="tiempoFuncionamiento"
	 */
	public int getTiempoFuncionamiento() {
		return tiempoFuncionamiento;
	}

	/** 
	 * Setter of the property <tt>tiempoFuncionamiento</tt>
	 * @param tiempoFuncionamiento  The tiempoFuncionamiento to set.
	 * @uml.property  name="tiempoFuncionamiento"
	 */
	public void setTiempoFuncionamiento(int tiempoFuncionamiento) {
		this.tiempoFuncionamiento = tiempoFuncionamiento;
	}

	/**
	 * @uml.property  name="tipo"
	 */
	private String tipo;

	/**
	 * Getter of the property <tt>tipo</tt>
	 * @return  Returns the tipo.
	 * @uml.property  name="tipo"
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Setter of the property <tt>tipo</tt>
	 * @param tipo  The tipo to set.
	 * @uml.property  name="tipo"
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/** 
	 * @uml.property name="vuelos"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="puertas:TFG.Vuelos"
	 */
	private List vuelos;

	/** 
	 * Getter of the property <tt>vuelos</tt>
	 * @return  Returns the vuelos.
	 * @uml.property  name="vuelos"
	 */
	public List getVuelos() {
		return vuelos;
	}

	/**
	 * @uml.property  name="posicionX"
	 */
	private int posicionX;

	/** 
	 * Getter of the property <tt>posicionXY</tt>
	 * @return  Returns the posicionXY.
	 * @uml.property  name="posicionX"
	 */
	public int getPosicionX() {
		return posicionX;
	}

	/** 
	 * Setter of the property <tt>posicionXY</tt>
	 * @param posicionXY  The posicionXY to set.
	 * @uml.property  name="posicionX"
	 */
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	/**
	 * @uml.property  name="posicionY"
	 */
	private int posicionY;

	/**
	 * Getter of the property <tt>posicionY</tt>
	 * @return  Returns the posicionY.
	 * @uml.property  name="posicionY"
	 */
	public int getPosicionY() {
		return posicionY;
	}

	/**
	 * Setter of the property <tt>posicionY</tt>
	 * @param posicionY  The posicionY to set.
	 * @uml.property  name="posicionY"
	 */
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	/**
	 * @uml.property  name="distanciaPuertas"
	 */
	private List distanciaPuertas;

	/**
	 * Getter of the property <tt>distanciaPuertas</tt>
	 * @return  Returns the distanciaPuertas.
	 * @uml.property  name="distanciaPuertas"
	 */
	public List getDistanciaPuertas() {
		return distanciaPuertas;
	}

	/**
	 * Setter of the property <tt>distanciaPuertas</tt>
	 * @param distanciaPuertas  The distanciaPuertas to set.
	 * @uml.property  name="distanciaPuertas"
	 */
	public void setDistanciaPuertas(List distanciaPuertas) {
		this.distanciaPuertas = distanciaPuertas;
	}

		
		/**
		 */
		public Puertas(){
		}

		/**
		 * @uml.property  name="terminal"
		 */
		private String terminal;

		/**
		 * Getter of the property <tt>terminal</tt>
		 * @return  Returns the terminal.
		 * @uml.property  name="terminal"
		 */
		public String getTerminal() {
			return terminal;
		}

		/**
		 * Setter of the property <tt>terminal</tt>
		 * @param terminal  The terminal to set.
		 * @uml.property  name="terminal"
		 */
		public void setTerminal(String terminal) {
			this.terminal = terminal;
		}

		@Override
		public String toString() {
			return "Puertas [numero=" + numero + ", posicionX=" + posicionX
					+ ", posicionY=" + posicionY + ", terminal=" + terminal
					+ "] \n";
		}

		/** 
		 * Getter of the property <tt>vuelos</tt>
		 * @return  Returns the vuelos.
		 * @uml.property  name="vuelos"
		 */
		public Vuelos getVuelos(int i) {
			return (Vuelos) vuelos.get(i);
		}

		/**
		 * Returns an iterator over the elements in this list in proper sequence.
		 * @return  an iterator over the elements in this list in proper sequence.
		 * @see java.util.List#iterator()
		 * @uml.property  name="vuelos"
		 */
		public Iterator vuelosIterator() {
			return vuelos.iterator();
		}

		/**
		 * Returns <tt>true</tt> if this list contains no elements.
		 * @return  <tt>true</tt> if this list contains no elements.
		 * @see java.util.List#isEmpty()
		 * @uml.property  name="vuelos"
		 */
		public boolean isVuelosEmpty() {
			return vuelos.isEmpty();
		}

		/**
		 * Returns <tt>true</tt> if this list contains the specified element.
		 * @param element  element whose presence in this list is to be tested.
		 * @return  <tt>true</tt> if this list contains the specified element.
		 * @see java.util.List#contains(Object)
		 * @uml.property  name="vuelos"
		 */
		public boolean containsVuelos(Vuelos vuelos) {
			return this.vuelos.contains(vuelos);
		}

		/**
		 * Returns <tt>true</tt> if this list contains all of the elements of the specified collection.
		 * @param elements  collection to be checked for containment in this list.
		 * @return  <tt>true</tt> if this list contains all of the elements of the specified collection.
		 * @see java.util.List#containsAll(Collection)
		 * @uml.property  name="vuelos"
		 */
		public boolean containsAllVuelos(Collection vuelos) {
			return this.vuelos.containsAll(vuelos);
		}

		/**
		 * Returns the number of elements in this list.
		 * @return  the number of elements in this list.
		 * @see java.util.List#size()
		 * @uml.property  name="vuelos"
		 */
		public int vuelosSize() {
			return vuelos.size();
		}

		/**
		 * Returns an array containing all of the elements in this list in proper sequence.
		 * @return  an array containing all of the elements in this list in proper sequence.
		 * @see java.util.List#toArray()
		 * @uml.property  name="vuelos"
		 */
		public Vuelos[] vuelosToArray() {
			return (Vuelos[]) vuelos.toArray(new Vuelos[vuelos.size()]);
		}

		/**
		 * Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array.
		 * @param a  the array into which the elements of this list are to be stored.
		 * @return  an array containing all of the elements in this list in proper sequence.
		 * @see java.util.List#toArray(Object[])
		 * @uml.property  name="vuelos"
		 */
		public Vuelos[] vuelosToArray(Vuelos[] vuelos) {
			return (Vuelos[]) this.vuelos.toArray(vuelos);
		}

		/**
		 * Inserts the specified element at the specified position in this list (optional operation)
		 * @param index  index at which the specified element is to be inserted.
		 * @param element  element to be inserted.
		 * @see java.util.List#add(int,Object)
		 * @uml.property  name="vuelos"
		 */
		public void addVuelos(int index, Vuelos vuelos) {
			this.vuelos.add(index, vuelos);
		}

		/**
		 * Appends the specified element to the end of this list (optional operation).
		 * @param element  element to be appended to this list.
		 * @return  <tt>true</tt> (as per the general contract of the <tt>Collection.add</tt> method).
		 * @see java.util.List#add(Object)
		 * @uml.property  name="vuelos"
		 */
		public boolean addVuelos(Vuelos vuelos) {
			return this.vuelos.add(vuelos);
		}

		/**
		 * Removes the element at the specified position in this list (optional operation).
		 * @param index  the index of the element to removed.
		 * @return  the element previously at the specified position.
		 * @see java.util.List#remove(int)
		 * @uml.property  name="vuelos"
		 */
		public Object removeVuelos(int index) {
			return vuelos.remove(index);
		}

		/**
		 * Removes the first occurrence in this list of the specified element  (optional operation).
		 * @param element  element to be removed from this list, if present.
		 * @return  <tt>true</tt> if this list contained the specified element.
		 * @see java.util.List#remove(Object)
		 * @uml.property  name="vuelos"
		 */
		public boolean removeVuelos(Vuelos vuelos) {
			return this.vuelos.remove(vuelos);
		}

		/**
		 * Removes all of the elements from this list (optional operation).
		 * @see java.util.List#clear()
		 * @uml.property  name="vuelos"
		 */
		public void clearVuelos() {
			vuelos.clear();
		}

		/**
		 * Setter of the property <tt>vuelos</tt>
		 * @param vuelos  the vuelos to set.
		 * @uml.property  name="vuelos"
		 */
		public void setVuelos(List vuelos) {
			this.vuelos = vuelos;
		}


}
