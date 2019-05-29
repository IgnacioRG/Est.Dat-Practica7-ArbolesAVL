package practica7;
import java.util.function.Consumer;
/**
 * @author Rivera Garcia Ignacio 
 * @author Garcia Zarraga Angelica Lizbeth
 * Clase que representa un Arbol Binario.
 */
public abstract class ArbolBinario<T>{
	/**
	 * Clase interna Vertice
	 */
	protected class Vertice{
		public T elemento;
		public Vertice izquierdo;
		public Vertice derecho;
		public Vertice padre;
		public boolean paso;

		/**
	 	 * contructor de vertice apartir de un elemento.
	 	 */
		public Vertice(T elemento){
			this.elemento = elemento;
		}

		public Vertice(){
			this.elemento = null;
		}
	}

	protected Vertice raiz;
	protected int elementos;
	/**
	 * constructor de un ArbolBinario vacio.
	 */
	public ArbolBinario(){
		raiz = null;
		elementos = 0;
	}
	/**
	 * constructor de un ArbolBinario apartir de un arreglo.
	 */
	public ArbolBinario(T[] arreglo){
		Vertice r = new Vertice(arreglo[0]);
		raiz = r;
		elementos = 1;
		Vertice supp = raiz;
		int j = 2;

		for(int i = 2;i<arreglo.length;i+=2){
			if(arreglo[i] != null){
				Vertice nuevo = new Vertice(arreglo[i]);
				Vertice nuevo2 = new Vertice(arreglo[i-1]);
				supp.izquierdo = nuevo;
				supp.derecho = nuevo2;
				nuevo.padre = supp;
				nuevo2.padre = supp;
				supp = supp.izquierdo;
				j = j+2;
				elementos = elementos + 2;
				continue;
			}
		}
		if(arreglo[j-1] != null){
			Vertice nuevo3 = new Vertice(arreglo[j-1]);
			supp.izquierdo = nuevo3;
			nuevo3.padre = supp;
			elementos = elementos + 1;
		}
	}
	//metodos agrega,elimina,contiene.
	public abstract void agrega(T elemento);
	public abstract boolean elimina(T elemento);
	public abstract boolean contiene(T elemento);
	/**
	 * bfs realiza un recorrido en el ArbolBinario por niveles y aplica una funcion en ese orden.
	 * @param Consumer funcion
	 */
	public void bfs(Consumer<T> funcion){
		//por recorridoeles
		if(raiz == null){
			return;
		}
		Cola helpfulR = new Cola();
		Cola recorrido = new Cola();
		Vertice supp = raiz;
		if(supp != null){
			helpfulR.mete(supp);
			while(!helpfulR.esVacia()){
				Vertice nAc = (Vertice)helpfulR.saca();
				recorrido.mete(nAc);
				if(nAc.derecho != null){
					helpfulR.mete(nAc.derecho);
				}

				if(nAc.izquierdo != null){
					helpfulR.mete(nAc.izquierdo);
				}
			}
		}
		String cad = "";
		while(!recorrido.esVacia()){
			Vertice nApp = (Vertice)recorrido.saca();
			cad = cad + nApp.elemento + ", ";
			funcion.accept(nApp.elemento);
		}
		System.out.println(cad);
	}
	/**
	 * dfs realiza un recorrido dependiendo del tipo que nos indique el usuario,
	 * 1 := preorder, 2:= inorder, 3:= postorder, lanzamos una Exception si el numero no es alguno de estos.
	 * @param Consumer funcion
	 */
	public void dfs(int tipo, Consumer<T> funcion){
		if(tipo < 1 || tipo > 3){
			throw new IllegalArgumentException("Su numero no corresponde a un tipo de helpfulR.");
		}
		if(raiz == null){
			return;
		}
		if(tipo == 1){
			Pila aOperar = new Pila();
			Cola recorrido = new Cola();

			Vertice supp = raiz;
			aOperar.mete(supp);
			while(!aOperar.esVacia()){
				Vertice nAc	= (Vertice)aOperar.saca();
				recorrido.mete(nAc);
				if(nAc.izquierdo != null){
					aOperar.mete(nAc.izquierdo);
				}
				if(nAc.derecho != null){
					aOperar.mete(nAc.derecho);
				}
				
			}
			String cad = "";
			while(!recorrido.esVacia()){
				Vertice nApp = (Vertice)recorrido.saca();
				cad = cad + nApp.elemento + ", ";
				funcion.accept(nApp.elemento);
			}
			System.out.println(cad);
		}

		if(tipo == 2){
			Pila aOperar = new Pila();
			Cola recorrido = new Cola();

			Vertice supp = raiz;
			aOperar.mete(supp);
			while(!aOperar.esVacia()){
				Vertice nAc = (Vertice)aOperar.mira();
				if(nAc.derecho != null && nAc.derecho.paso == false){
					aOperar.mete(nAc.derecho);
					continue;
				}
				aOperar.saca();
				recorrido.mete(nAc);
				nAc.paso = true;
				if(nAc.izquierdo != null){
					aOperar.mete(nAc.izquierdo);
					continue;
				}
			}
			String cad = "";
			while(!recorrido.esVacia()){
				Vertice nApp = (Vertice)recorrido.saca();
				cad = cad + nApp.elemento + ", ";
				againPaso(nApp);
				funcion.accept(nApp.elemento);
			}

			System.out.println(cad);
			
		}

		if(tipo == 3){
			Pila aOperar = new Pila();
			Cola recorrido = new Cola();

			Vertice supp = raiz;
			aOperar.mete(supp);
			while(!aOperar.esVacia()){
				Vertice nAc = (Vertice)aOperar.mira();

				if(nAc.derecho != null && nAc.derecho.paso == false){
					aOperar.mete(nAc.derecho);
					continue;
				}
				if(nAc.izquierdo != null && nAc.izquierdo.paso == false){
					aOperar.mete(nAc.izquierdo);
					continue;
				}
				aOperar.saca();
				recorrido.mete(nAc);
				nAc.paso = true;
			}
			String cad = "";
			while(!recorrido.esVacia()){
				Vertice nApp = (Vertice)recorrido.saca();
				cad = cad + nApp.elemento + ", ";
				againPaso(nApp);
				funcion.accept(nApp.elemento);
			}
			System.out.println(cad);
		}
	}
	/**
	 *
	 *
	 */
	protected void giraIzquierda(Vertice v){
		if(v.derecho == null){
			return;
		}
		Vertice a = v.padre.padre;
		Vertice hijoI = v.izquierdo;
		Vertice p = v.padre;

		if(p != null){
			v.padre = a;
			v.izquierdo = p;
			p.padre = v;
			p.derecho = hijoI;
		}else{
			Vertice hijoD = v.derecho;
			v.derecho = hijoD.izquierdo;
			v.padre = hijoD;
			hijoD.izquierdo = v;
			raiz = hijoD;
		}
	} 
	/**
	 *
	 *
	 */
	protected void giraDerecha(Vertice v){
		if(v.izquierdo == null){
			return;
		}
		Vertice a = v.padre.padre;
		Vertice hijoD = v.derecho;
		Vertice p = v.padre;

		if(p != null){
			v.padre = a;
			v.derecho = p;
			p.padre = v;
			p.izquierdo = hijoD;
		}else{
			Vertice hijoI = v.izquierdo;
			v.izquierdo = hijoI.derecho;
			v.padre = hijoI;
			hijoI.derecho = v;
			raiz = hijoI;
		}
	}

	/**
	 * getElementos nos regresa el numero de elementos del ArbolBinario.
	 * @return int
	 */
	public int getElementos(){
		return elementos;
	}
	/**
	 * metodo auxiliar againPaso que regresa el valor false en el atributo paso de todos los vertices.
	 * @param Vertice v
	 */
	public void againPaso(Vertice v){
		v.paso = false;
	}
}