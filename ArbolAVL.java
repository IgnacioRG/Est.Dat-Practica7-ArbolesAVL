/**
 * @author Rivera Garcia Ignacio @author Garcia Zarraga Angelica Lizbeth
 * @date 28 abril 2019
 * Clase que representa un Arbol Binario.
 */
package practica7;
public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T>{
	/**
	 * clase interna VerticeAVL que agrega el atributo altura
	 */
    protected class VerticeAVL extends Vertice{

		public int altura;
		/**
	 	 * Constructor de VerticeAVL apartir de un elemento
	 	 * @param T elemento
	 	 */
		public VerticeAVL(T elemento){
	    	super(elemento);
	    	altura = 0;
		}

		public VerticeAVL(){
			super();
			altura = 0;
		}
    }
    /**
	 * Constructor de ArbolAVL vacio que llama al metodo de la clase padre
	 * ArbolBinarioBusqueda
	 */
    public ArbolAVL(){
		super();
    }
    /**
	 * Constructor de ArbolAVL apartir de un arreglo de elementos
	 * @param T[] elementos
	 */
    public ArbolAVL(T[] elementos){
		ArbolAVL arbol = new ArbolAVL();
		arbol.agrega(elementos[0]);

		for(int i = 1;i<elementos.length;i++){
			arbol.agrega(elementos[i]);
		}
	}
	/**
	 * getAltura nos regresa la altura del VerticeAVL
	 * calculandola si es distinto de null
	 * @param VerticeAVL v
	 * @return int
	 */
    protected int getAltura(Vertice v){
    	if(v == null){
    		return -1;
    	}

    	VerticeAVL va = new VerticeAVL();
    	if(v.getClass() == va.getClass()){
    		va = (VerticeAVL) v;
    		return va.altura;
    	}else{

    		VerticeAVL hd = (VerticeAVL)v.derecho;
			VerticeAVL hi = (VerticeAVL)v.izquierdo;

			if(hd.altura == hi.altura){
				return hd.altura + 1;
			}else{
				if(hd.altura < hi.altura){
					return hi.altura + 1;
				}else{
					return hd.altura + 1;
				}
			}
    	}
    }
    /**
	 * actualizaAltura calcula y actualiza la altura de un VerticeAVL
	 * @param VerticeAVL v
	 */
    protected void actualizaAltura(Vertice v){
    	VerticeAVL va = new VerticeAVL();
    	if(v == null){
    		va = (VerticeAVL)v;
    		va.altura = -1;
    		v = va;
    	}

    	if(v.getClass() == va.getClass()){
    		va = (VerticeAVL) v;
    		VerticeAVL hd = (VerticeAVL) v.derecho;
			VerticeAVL hi = (VerticeAVL) v.izquierdo;

			if(getAltura(hd) == getAltura(hi)){
				va.altura = getAltura(hd) + 1;
				v = va;
			}else{
				if(getAltura(hd) < getAltura(hi)){
					va.altura = getAltura(hi) + 1;
					v = va;
				}else{
					va.altura = getAltura(hd) + 1;
					v = va;
				}
			}
    	}else{
    		return;
    	}

		
    }
    /**
	 * getBalance nos indica si nuestro Vertice rompe la regla de
	 * los arboles AVL
	 * @param Vertice v
	 * @return int
	 */
    protected int getBalance(Vertice v){
    	if(v == null){
    		return 0;
    	}

		int aHD = getAltura((VerticeAVL)v.derecho);
		int aHI = getAltura((VerticeAVL)v.izquierdo);

		return Math.abs(aHD - aHI);
    }
    /**
	 * agrega agrega un elemento al arbol AVL y despues balanceando
	 * @param T elemento
	 */
    @Override
    public void agrega(T elemento){
		VerticeAVL nuevo = new VerticeAVL(elemento);
		boolean fueAgregado = false;
		VerticeAVL supp = (VerticeAVL)raiz;
		while(!fueAgregado){
			if(supp.elemento.compareTo(nuevo.elemento)<=0){
				if(supp.izquierdo != null){
					supp = (VerticeAVL) supp.izquierdo;
					continue;
				}else{
					supp.izquierdo = nuevo;
					nuevo.padre = supp;
					rebalanceo(nuevo);
					supp = (VerticeAVL) raiz;
					fueAgregado = true;
				}
			}else{

				if(supp.derecho != null){
					supp = (VerticeAVL) supp.derecho;
					continue;
				}else{
					supp.derecho = nuevo;
					nuevo.padre = supp;
					rebalanceo(nuevo);
					supp = (VerticeAVL) raiz;
					fueAgregado = true;
				}
			}
		}
    }
    /**
	 * elimina elimina la primera aparicion del elemento y balancea
	 * @param T elemento
	 * @return boolean
	 */
    @Override
    public boolean elimina(T elemento){
    	VerticeAVL heir = busca(elemento);
		boolean a = super.elimina(elemento);

		if(a){
			rebalanceo(heir);
			return a;
		}else{
			return a;
		}
    }
    /**
	 * giraIzquierda gira un VerticeAVL a la izquierda, actualizando
	 * su altura y la de su hijo derecho.
	 * @param VerticeAVL v
	 */
    protected void giraIzquierda(VerticeAVL v){
		if(v.derecho == null){
			return;
		}
		VerticeAVL a = (VerticeAVL) v.padre.padre;
		VerticeAVL hijoI = (VerticeAVL) v.izquierdo;
		VerticeAVL p = (VerticeAVL) v.padre;

		if(p != null){
			v.padre = a;
			v.izquierdo = p;
			p.padre = v;
			p.derecho = hijoI;
		}else{
			VerticeAVL hijoD = (VerticeAVL) v.derecho;
			v.derecho = hijoD.izquierdo;
			v.padre = hijoD;
			hijoD.izquierdo = v;
			raiz = hijoD;
		}
		VerticeAVL hD = (VerticeAVL) v.derecho;
		actualizaAltura(v);
		actualizaAltura(hD);
    }
    /**
	 * giraDerecha gira un VerticeAVL a la derecha, actualizando
	 * su altura y la de su hijo izquierdo.
	 * @param VerticeAVL v
	 */
    protected void giraDerecha(VerticeAVL v){
		if(v.izquierdo == null){
			return;
		}
		VerticeAVL a = (VerticeAVL) v.padre.padre;
		VerticeAVL hijoD = (VerticeAVL) v.derecho;
		VerticeAVL p = (VerticeAVL) v.padre;

		if(p != null){
			v.padre = a;
			v.derecho = p;
			p.padre = v;
			p.izquierdo = hijoD;
		}else{
			VerticeAVL hijoI = (VerticeAVL) v.izquierdo;
			v.izquierdo = hijoI.derecho;
			v.padre = hijoI;
			hijoI.derecho = v;
			raiz = hijoI;
		}
		VerticeAVL hI = (VerticeAVL) v.izquierdo;
		actualizaAltura(v);
		actualizaAltura(hI);
    }
    /**
	 * rebalanceo rebalancea nuestro arbol AVL de forma recursiva
	 * @param VerticeAVL v
	 */
    public void rebalanceo(VerticeAVL v){
    	if(v == raiz){
    		actualizaAltura(v);
    		return;
    	}else{
    		if(getBalance(v)<2){
    			rebalanceo((VerticeAVL)v.padre);
    		}else{
    			int aHD = getAltura((VerticeAVL)v.derecho);
				int aHI = getAltura((VerticeAVL)v.izquierdo);

				if(aHD < aHI){
					giraDerecha(v);
					rebalanceo((VerticeAVL)v.padre);
				}else{
					giraIzquierda(v);
					rebalanceo((VerticeAVL)v.padre);
				}
    		}
    	}
    }
    /**
	 * busca nos da la direccion del VerticeAVL en nuestro arbol en el
	 * cual vamos a empezar con el rebalanceo
	 * @param T elemento
	 * @return VerticeAVL
	 */
    public VerticeAVL busca(T elemento){
		boolean fueEncontrado = false;
		VerticeAVL supp = (VerticeAVL)raiz;
		VerticeAVL heredero = null;
		while(!fueEncontrado){

			if(supp.elemento.compareTo(elemento)<=0){
				if(supp.elemento == elemento){
					fueEncontrado = true;
					heredero = supp;
					break;
				}
				if(supp.izquierdo != null){
					supp = (VerticeAVL)supp.izquierdo;
					continue;
				}else{
					fueEncontrado = true;
				}
			}else{
				if(supp.derecho != null){
					supp = (VerticeAVL)supp.derecho;
					continue;
				}else{
					fueEncontrado = true;
				}
			}
		}
		return heredero;
	}
}
