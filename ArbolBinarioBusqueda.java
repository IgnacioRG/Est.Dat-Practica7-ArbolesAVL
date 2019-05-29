/**
 * @author Rivera Garcia Ignacio 
 * @author Garcia Zarraga Angelica Lizbeth
 * Clase que representa un arbol binario de busqueda.
 */
package practica7;
public class ArbolBinarioBusqueda<T extends Comparable<T>> extends ArbolBinario<T>{
	/**
	 * Constructor de ArbolBinarioBusqueda vacio.
	 */
	public ArbolBinarioBusqueda(){
		raiz = null;
		elementos = 0;
	}
	/**
	 * Constructor de ArbolBinarioBusqueda apartir de un
	 * arreglo (de manera ordenada).
	 */
	public ArbolBinarioBusqueda(T[] a){
		Vertice r = new Vertice(a[0]);
		raiz = r;
		Vertice supp = raiz;
		for(int i = 1;i<a.length;i++){
			Vertice nuevo = new Vertice(a[i]);

			if(supp.elemento.compareTo(nuevo.elemento)<=0){
				if(supp.izquierdo != null){
					supp = supp.izquierdo;
					i--;
					continue;
				}else{
					supp.izquierdo = nuevo;
					nuevo.padre = supp;
					supp = raiz;
				}
			}else{

				if(supp.derecho != null){
					supp = supp.derecho;
					i--;
					continue;
				}else{
					supp.derecho = nuevo;
					nuevo.padre = supp;
					supp = raiz;
				}
			}
		}
	}
	/**
	 * agrega que agregara un elemento a nuestro ArbolBinarioBusqueda.
	 * @param T elemento.
	 */
	@Override
	public void agrega(T elemento){
		Vertice nuevo = new Vertice(elemento);
		boolean fueAgregado = false;
		Vertice supp = raiz;
		while(!fueAgregado){
			if(supp.elemento.compareTo(nuevo.elemento)<=0){
				if(supp.izquierdo != null){
					supp = supp.izquierdo;
					continue;
				}else{
					supp.izquierdo = nuevo;
					nuevo.padre = supp;
					supp = raiz;
					fueAgregado = true;
				}
			}else{

				if(supp.derecho != null){
					supp = supp.derecho;
					continue;
				}else{
					supp.derecho = nuevo;
					nuevo.padre = supp;
					supp = raiz;
					fueAgregado = true;
				}
			}
		}
	}
	/**
	 * contiene que buscara un elemento en nuestro ArbolBinarioBusqueda
	 * y nos indica si esta o no.
	 * @param T elemento
	 * @return boolean
	 */
	@Override
	public boolean contiene(T elemento){
		Vertice b = new Vertice(elemento);
		boolean fueEncontrado = false;
		boolean a = false;
		Vertice supp = raiz;
		while(!fueEncontrado){

			if(supp.elemento.compareTo(b.elemento)<=0){
				if(supp.elemento == elemento){
					fueEncontrado = true;
					a = true;
					break;
				}
				if(supp.izquierdo != null){
					supp = supp.izquierdo;
					continue;
				}else{
					fueEncontrado = true;
					a = false;
				}
			}else{
				if(supp.derecho != null){
					supp = supp.derecho;
					continue;
				}else{
					fueEncontrado = true;
					a = false;
				}
			}
		}
		return a;
	}
	/**
	 * elimina, elimina un elemento de nuestro ArbolBinarioBusqueda
	 * y nos indica si fue eliminado o no.
	 * @param T elemento
	 * @return boolean
	 */
	@Override
	public boolean elimina(T elemento){
		Vertice b = new Vertice(elemento);
		boolean fueEncontrado = false;
		boolean eliminado = false;
		Vertice supp = raiz;
		while(!fueEncontrado){

			if(supp.elemento.compareTo(b.elemento)<=0){

				if(supp.elemento == elemento){
					fueEncontrado = true;
					//caso1
					if(supp.izquierdo == null && supp.derecho == null){
						casoUNO(supp);
						eliminado = true;
						break;
					}
					//caso 2 y 3
					if(supp.izquierdo != null && supp.derecho == null){
						casoDOS(supp,supp.izquierdo);
						eliminado = true;
						break;
					}
					if(supp.izquierdo == null && supp.derecho != null){
						casoDOS(supp,supp.derecho);
						eliminado = true;
						break;
					}
					//caso3
					if(supp.izquierdo != null && supp.derecho != null){
						eliminado = casoTRES(supp);
						break;
					}
				}

				if(supp.izquierdo != null){
					supp = supp.izquierdo;
					continue;
				}else{
					fueEncontrado = true;
					eliminado = false;
				}
			}else{
				if(supp.derecho != null){
					supp = supp.derecho;
					continue;
				}else{
					fueEncontrado = true;
					eliminado = false;
				}
			}
		}
		return eliminado;
	}
	/**
	 * metodo auxiliar casoUNO en el cual el vertice a eliminar
	 * no tiene hijos.
	 * @param Vertice v
	 */
	public void casoUNO(Vertice v){
		Vertice der = v.padre.derecho;
		Vertice izq = v.padre.izquierdo;

		if(v == der){
			v.padre.derecho = null;
		}else{
			v.padre.izquierdo = null;
		}
	}
	/**
	 * metodo auxiliar casoDOS en el cual el vertice a eliminar
	 * tiene solo un hijo, derecho o izquierdo
	 * @param Vertice v, hijo
	 */
	public void casoDOS(Vertice v,Vertice hijo){
		Vertice der = v.padre.derecho;
		Vertice izq = v.padre.izquierdo;

		if(v == der){
			v.padre.derecho = hijo;
			hijo.padre = v.padre;
		}else{
			v.padre.izquierdo = hijo;
			hijo.padre = v.padre;
		}
	}
	/**
	 * metodo auxiliar casoTRES en el cual el vertice a eliminar
	 * tiene los dos hijos, derecho e izquierdo
	 * @param Vertice v
	 */
	public boolean casoTRES(Vertice v){
		Vertice der = v.derecho;
		Vertice izq = (Vertice)izquierdaM(der);

		if(izq != null){
			T el = izq.elemento;
			elimina(izq.elemento);
			v.elemento = izq.elemento;
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Metodo auxiliar izquierdaM que nos regresa el vertice mas izquierdo
 	 * del hijo izquierdo del elemento a eliminar
	 */
	public Vertice izquierdaM(Vertice v){
		if(v.izquierdo == null){
			return v;
		}else{
			Vertice sig = v.izquierdo;
			return izquierdaM(sig);
		}
	}
	/**
	 * Metodo Principal donde da inicio nuestro programa.
	 * @param String args[]
	 */
	public static void main (String args[]){

		//pruebas unitarias
		Integer[] arreglox = {8,5,9,3,10,6,1,13};
		ArbolBinarioBusqueda ab = new ArbolBinarioBusqueda (arreglox);

		//Recorridos

		System.out.println("-------------------------");
		System.out.println("BFS");
		ab.bfs(x -> System.out.println(x));
		System.out.println("\n");

		System.out.println("DFS Preorder.");
		ab.dfs(1, x -> System.out.println(x));
		System.out.println("\n");

		System.out.println("DFS Inorder.");
		ab.dfs(2, x -> System.out.println(x));
		System.out.println("\n");
		
		System.out.println("DFS Postorder.");
		ab.dfs(3, x -> System.out.println(x));
		System.out.println("-------------------------");

		//Agrega, Contiene y Elimina

		System.out.println("Agrega 4");
		ab.agrega(4);
		ab.dfs(2, x -> System.out.println(x));
		System.out.println("\n");

		System.out.println("Contiene 0?");
		System.out.println(ab.contiene(0));
		ab.dfs(2, x -> System.out.println(x));
		System.out.println("\n");

		System.out.println("Contiene 9?");
		System.out.println(ab.contiene(9));
		ab.dfs(2, x -> System.out.println(x));
		System.out.println("\n");

		System.out.println("Elimina 5");
		System.out.println(ab.elimina(5));
		ab.dfs(2, x -> System.out.println(x));
		System.out.println("-------------------------");
	}
}