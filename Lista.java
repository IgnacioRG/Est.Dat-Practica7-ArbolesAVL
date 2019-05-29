/**
 * @author Rivera Garcia Ignacio 
 * @author Garcia Zarraga Angelica Lizbeth
 * Clase Lista para implementar listas doblemente ligadas
 */
package practica7;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lista<T> implements Iterable<T>{


	// Clase interna para representar los nodos de nuestras listas.
	private class Nodo{
		
		public T elemento;
		public Nodo siguiente;
		public Nodo anterior;

		/**
		 *	Constructor que crea nuestro nodo
		 */
		public Nodo(T elemento){
			this.elemento = elemento;
		}
	}



	// Clase para iterar la lista.
	private class Iterador implements Iterator<T>{

		public Nodo siguiente;

		/**
	 	 * Constructor del iterador
	 	 */
		public Iterador(){
			this.siguiente = cabeza;
		}
		/**
	 	 * hasNext nos indica se existe un nodo siguiente.
	 	 * @return boolean
	 	 */
		@Override
		public boolean hasNext(){
			if(siguiente != null){
				return true;
			}
			return false;
		}
		/**
	 	 * next nos regresa el elemento siguiente.
	 	 * @return T
	 	 */
		@Override
		public T next(){
			if(this.siguiente == null){
				throw new NoSuchElementException("No existe el siguiente elemento");
			}else{
				return siguiente.elemento;
			}
		}
	}



	private Nodo cabeza;
	private Nodo ultimo;
	private int longitud;
	
	/**
	 * Constructor de Lista donde creamos una lista vacia.
	 */
	public Lista(){
		cabeza = null;
		ultimo = cabeza;
		longitud = 0;
	}

	/**
	 * Constructor de Lista donde creamos una lista
	 * apartir de un arreglo.
	 * @param T[]
	 */
	public Lista(T[] arreglo){
		for(int i=0;i<arreglo.length;i++){
			if(cabeza == null){
				if(arreglo[i] != null){
					Nodo nuevo = new Nodo(arreglo[i]);
					nuevo.anterior = null;
					nuevo.siguiente = null;
					cabeza = nuevo;
					ultimo = nuevo;
					longitud++;
				}else{
					continue;
				}
			}else{
				if(arreglo[i] != null){
					Nodo nuevo = new Nodo(arreglo[i]);
					ultimo.siguiente = nuevo;
					nuevo.anterior = ultimo;
					nuevo.siguiente = null;
					ultimo = nuevo;
					longitud++;
				}else{
					continue;
				}
			}
		}
	}
	/**
	 * getPrimero nos regresa el primer elemento de la lista
	 * @return T
	 */
	public T getPrimero(){
		if(cabeza == null){
			throw new NoSuchElementException("La Lista es vacia.");
		}else{
			if(cabeza.elemento != null){
				return cabeza.elemento;
			}else{
				throw new NoSuchElementException("La Lista es vacia.");
			}
		}
	}
	/**
	 * getUltimo nos regresa el ultimo elemento de la lista
	 * @return T
	 */
	public T getUltimo(){
		if(ultimo == null){
			throw new NoSuchElementException("La Lista es vacia.");		   
		}else{
			if(ultimo.elemento != null){
				return ultimo.elemento;
			}else{
				throw new NoSuchElementException("La Lista es vacia.");
			}
		}
	}
	/**
	 * eleminaPrimero elimina el primer elemento de la lista
	 * @return T
	 */
	public T eliminaPrimero(){
		if(longitud == 1){
			T eElem = null;
			eElem = cabeza.elemento;
			cabeza = null;
			ultimo = null;
			longitud = 0;
			return eElem;
		}
		if(cabeza != null){
			T eElem = null;
			eElem = cabeza.elemento;
			cabeza = cabeza.siguiente;
			cabeza.anterior = null;
			longitud--;
			return eElem;
		}else{
			throw new NoSuchElementException("La Lista es vacia.");
		}
	}
	/**
	 * eliminaUlimo elimina el ultimo elemento de la lista
	 * @return T
	 */
	public T eliminaUltimo(){
		if(longitud == 1){
			T eElem = null;
			eElem = cabeza.elemento;
			cabeza = null;
			ultimo = null;
			longitud = 0;
			return eElem;
		}
		if(ultimo != null){
			T eElem = null;
			eElem = ultimo.elemento;
			ultimo = ultimo.anterior;
			ultimo.siguiente = null;
			longitud--;
			return eElem;
		}else{
			throw new NoSuchElementException("La Lista es vacia.");
		}
	}
	/**
	 * agregaInicio agrega un nodo con el elemento t al inicio
	 * @param T
	 */
	public void agregaInicio(T t){
		if(cabeza == null){
			Nodo nuevo = new Nodo(t);
			cabeza = nuevo;
			ultimo = nuevo;
			longitud++;
		}else{
			if(cabeza.elemento != null){
		   		Nodo nuevo = new Nodo(t);
				cabeza.anterior = nuevo;
				nuevo.siguiente = cabeza;
				nuevo.anterior = null;
				cabeza = nuevo;
				longitud++;
			}else{
				cabeza.elemento = t;
			}
		}
	}
	/**
	 * agregaFinal agrega un nodo con el elemento t al final
	 * @param T
	 */
	public void agregaFinal(T t){
		if(t == null){
			throw new NullPointerException();
		}
		if(ultimo == null){
			Nodo nuevo = new Nodo(t);
			cabeza = nuevo;
			ultimo = nuevo;
			longitud++;
		}else{
			if(ultimo.elemento != null){
				Nodo nuevo = new Nodo(t);
				ultimo.siguiente = nuevo;
				nuevo.anterior = ultimo;
				nuevo.siguiente = null;
				ultimo = nuevo;
				longitud++;
			}
		}
	}
	/**
	 * contiene nos indica si un elemento t existe dentro de
	 * nuestra lista
	 * @param T
	 * @return boolean
	 */
	public boolean contiene(T t){
		if(cabeza.elemento == t){
			return true;
		}else{
			Nodo supp = null;
			supp = cabeza;
			while(supp.siguiente != null){
				supp = supp.siguiente;
				if(supp.elemento == t){
					return true;
				}else{
					continue;
				}
			}
			return false;
		}
	}
	/**
	 * getLongitud nos dice el tamaño de nuestra lista
	 * @return longitud
	 */
	public int getLongitud(){
		return longitud;
	}
	/**
	 * elimina, elimina la primera aparicion del elemento t en la lista
	 * y nos dice si fue eliminado
	 * @param T
	 * @return boolean
	 */
	public boolean elimina(T t){
		if(cabeza.elemento == t){
			eliminaPrimero();
			return true;
		}else{
			Nodo supp = null;
			supp = cabeza;
			while(supp.siguiente != null){
				supp = supp.siguiente;
				if(supp.elemento == t){
					supp.elemento = null;
					return true;
				}else{
					continue;
				}
			}
			return false;
		}
	}
	/**
	 * limpia, limpia nuestra lista de tal forma que
	 * que queda como nueva
	 */
	public void limpia(){
		cabeza = null;
		ultimo = null;
		longitud = 0;
	}
	/**
	 * get nos regresa el elemento del indice que nos de el usuario
	 * el indice comienza en 0.
	 * @param int indx
	 * @return T
	 */ 
	public T get(int indx){
		if(indx < 0 || indx > longitud){
			throw new IndexOutOfBoundsException("El indice no esta en el rango de la lista.");
		}else{
			if(indx == 0 && cabeza.elemento != null){
				return cabeza.elemento;
			}else{
				int apuntadorx = 0;
				Nodo supp = null;
				supp = cabeza;
				while(supp.siguiente != null){
					supp = supp.siguiente;
					apuntadorx++;
					if((indx - apuntadorx) == 0 && supp.elemento != null){
						return supp.elemento;
					}else{
						continue;
					}
				}
				throw new NoSuchElementException("No se que paso");
			}
		}
	}
	/**
	 * inserta, inserta el elemento t en el indice indx que nos
	 * de el usuario.
	 * @param int indx, T t
	 */
	public void inserta(int indx, T t){
		if(indx < 0 || indx > longitud){
			throw new IndexOutOfBoundsException("El indice no esta en el rango de la lista.");
		}else{
			if(indx == 0){
				cabeza.elemento = t;
			}else{
				int apuntadorx = 0;
				Nodo supp = null;
				supp = cabeza;
				while(supp.siguiente != null){
					supp = supp.siguiente;
					apuntadorx++;
					if((indx - apuntadorx) == 0){
						supp.elemento = t;
					}else{
						continue;
					}
				}
			}
		}
	}
	/**
	 * toArray "convierte" nuestra lista en un array
	 * @return Object[]
	 */
	public Object[] toArray(){
		Object[] arrayR = new Object[longitud];
		Nodo supp = null;
		supp = cabeza;
		for(int i = 0;i<arrayR.length;i++){
			arrayR[i] = supp.elemento;
			supp = supp.siguiente;
		}
		return arrayR;
	}
	/**
	 * reversa nos regresa nuestra lista, pero al reves
	 * @return Lista<T>
	 */
	public Lista<T> reversa(){
		Object[] arrayI = toArray();
		Object[] arrayR = new Object[arrayI.length];
		int apu = arrayI.length - 1;
		for(int i = 0;i<arrayI.length;i++){
			arrayR[i] = arrayI[apu];
			apu--;
		}
		Lista listaR = new Lista(arrayR);
		return listaR;
	}
	/**
	 * copia, hace una lista identica a la nuestra
	 * @return Lista<T>
	 */
	public Lista<T> copia(){
		Object[] arrayR = toArray();
		Lista listaR = new Lista(arrayR);
		return listaR;
		// Aquí va su código.
	}
	/**
	 * metodo toString para imprimir nuestra lista
	 * en un formato agredable
	 * @return String
	 */
	@Override
	public String toString(){
		if(cabeza == null){
			return "[]";
		}else{
			if(cabeza.siguiente == null){
				return "[" + cabeza.elemento + "]";
			}else{
				Nodo supp = null;
				supp = cabeza;
				String cadR = "[" + supp.elemento;
				while(supp.siguiente != null){
					cadR = cadR +", " + supp.siguiente.elemento;
					supp = supp.siguiente;
				}
				return cadR + "]";
			}
		}
	}
	/**
	 * equals nos dice si un objeto es igual a otro
	 * @param Object o
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o){
		if (o == null || getClass() != o.getClass()){
			return false;
		}else{
			Lista lista = (Lista)o;
			if(lista == null || longitud != lista.longitud){
				return false;
			}else{
				return true;
			}
		}
	 
	   
	}
	/**
	 * iterator crea un nuevo iterador
	 * @return iterator<T>
	 */
	@Override
	public Iterator<T> iterator(){
		return new Iterador();
	}

	//metodos setters para colas y pilas

	public void setCabeza(){
		cabeza = null;
	}

	public void setUltimo(){
		ultimo = null;
	}

	public void setLongitud(int l){
		longitud = l;
	}

	//metodo auxiliar para imprimir la pila
	public String imPila(){
		if(cabeza == null){
			return "";
		}else{
			if(cabeza.siguiente == null){
				return "" + cabeza.elemento;
			}else{
				Nodo supp = null;
				supp = cabeza;
				String cadR = "" + supp.elemento;
				while(supp.siguiente != null){
					cadR = cadR +"\n" + supp.siguiente.elemento;
					supp = supp.siguiente;
				}
				return cadR + "\n";
			}
		}
	}
	/**
	 * Metodo Principal
	 * @param args
	 */
	public static void main(String[] args){
		
	}
}