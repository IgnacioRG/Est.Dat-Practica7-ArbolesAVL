package practica7;
import java.util.NoSuchElementException;
import java.util.Iterator;
/**
 * Implementación de colas.
 */
public class Cola<T> implements Iterable<T>{

	private Lista<T> list;
	
	/**
	 * Constructor que crea una cola vacia.
	 */
	public Cola(){
		Lista vacio = new Lista();
		list = vacio;
	}
	/**
	 * Constructor que crea una cola apartir de
	 * una lista.
	 */
	public Cola(Lista<T> l){
		list = l;
	}
	/**
	 * Constructor que crea una cola apartir de
	 * un arreglo.
	 */
	public Cola(T[] arreglo){
		for(int i = 0;i<arreglo.length;i++){
			list.agregaFinal(arreglo[i]);
		}
	}
	/**
	 * Metodo que nos indica si nuestra cola es vacia o no.
	 * @return boolean
	 */
	public boolean esVacia(){
		if(list.getLongitud() == 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Metodo mira que nos regresa el elemento proximo a salir de la
	 * cola sin sacarlo.
	 * @return T elemento
	 */
	public T mira(){
		if(list.getLongitud() == 0){
			throw new NoSuchElementException("La cola no tiene elementos.");
		}else{
			return list.getPrimero();
		}
	}
	/**
	 * Metodo saca que regreasa el siguiente elementos a salir de la
	 * cola, eleminandolo de esta
	 * @return T elemento
	 */
	public T saca(){
		if(list.getLongitud() == 0){
			throw new NoSuchElementException("La cola no tiene elementos.");
		}else{
			return list.eliminaPrimero();
		}
	}
	/**
	 * Metodo mete que mete un elemento a la cola
	 * @param T elemento
	 */
	public void mete(T t){
		list.agregaFinal(t);
	}
	/**
	 * Metodo toString que imprime nuestra cola en un
	 * formato agradable
	 * @return String cadR
	 */
	@Override
	public String toString(){
		String cadR = list.toString();
		return cadR;
	}
	/**
	 * Metodo equals que nos indica si nuestra
	 * cola es igual a otra
	 * @param Object o
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o){
		return list.equals(o);
	}
	/**
	 * Metodo iterator que hace nuestras colas
	 * iterables
	 * @return Iterator<T>
	 */
	@Override
	public Iterator<T> iterator(){
		return list.iterator();
	}
}