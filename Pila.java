package practica7;
import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Implementación de pilas.
 */
public class Pila<T> implements Iterable<T>{

	private Lista<T> list;

	/**
	 * Constructor que crea una pila vacia.
	 */
	public Pila(){
		Lista vacio = new Lista();
		list = vacio;
	}
	/**
	 * Constructor que crea una pila apartir de
	 * una lista.
	 */
	public Pila(Lista<T> l){
		this.list = l;
	}
	/**
	 * Constructor que crea una pila apartir de
	 * un arreglo.
	 */
	public Pila(T[] arreglo){
		for(int i = 0;i<arreglo.length;i++){
			list.agregaFinal(arreglo[i]);
		}
	}
	/**
	 * Metodo que nos indica si nuestra pila es vacia o no.
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
	 * pila sin sacarlo.
	 * @return T elemento
	 */
	public T mira(){
		if(list.getLongitud() == 0){
			throw new EmptyStackException();
		}else{
			return list.getUltimo();
		}
	}
	/**
	 * Metodo saca que regreasa el siguiente elementos a salir de la
	 * pila, eleminandolo de esta
	 * @return T elemento
	 */
	public T saca(){
		if(list.getLongitud() == 0){
			throw new EmptyStackException();
		}else{
			return list.eliminaUltimo();
		}
	}
	/**
	 * Metodo mete que mete un elemento a la pila
	 * @param T elemento
	 */
	public void mete(T t){
		list.agregaFinal(t);
	}
	/**
	 * Metodo toString que imprime nuestra pila en un
	 * formato agradable
	 * @return String cadR
	 */
	@Override
	public String toString(){
		String cadR = list.imPila();
		return cadR;
	}
	/**
	 * Metodo equals que nos indica si nuestra
	 * pila es igual a otra
	 * @param Object o
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o){
		return list.equals(o);
	}
	/**
	 * Metodo iterator que hace nuestras pilas
	 * iterables
	 * @return Iterator<T>
	 */
	@Override
	public Iterator<T> iterator(){
		return list.iterator();
	}
}