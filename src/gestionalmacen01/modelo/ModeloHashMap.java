
/**
 * Write a description of class ModeloHaspMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package gestionalmacen01.modelo;
<<<<<<< HEAD
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;


public class ModeloHashMap implements ModeloAbs
{
    private HashMap <Integer,Producto> mapa;
    
    public ModeloHashMap()
    {
       mapa = new HashMap  <Integer,Producto>();
    }

	@Override
	public boolean insertarProducto(Producto p) {
		if(mapa.containsKey(p.getCodigo())) return false;
		mapa.put(p.getCodigo(),p);
		return true;
	}

	@Override
	public boolean borrarProducto(int codigo) {
//Borra la entrada con esa clave, si remove devuelve algo, es que existia.
		return mapa.get(codigo) != null;
		
	}

	@Override
	public boolean modificarProducto(Producto nuevo) {
		if(mapa.containsKey(nuevo.getCodigo())){
		mapa.put(nuevo.getCodigo(), nuevo);
		return true;
	}
	return false;
	}


	@Override
	public Producto buscarProducto(int codigo) {
//Aqui no hay bucle , el mapa va directo a la casilla del codigo.
		return mapa.get(codigo);
	}

	@Override
	public void imprimirProductosTodos() {
		for(Producto p : mapa.values()) {
			System.out.println(p);
		}
		
	}

	@Override
	public List<Producto> obtenerProductos() {
		return new ArrayList<>(mapa.values());
	}

	@Override
	public List<Producto> obtenerProductosStockMin() {
		List<Producto> listaMin = new ArrayList<>();
		for (Producto p : mapa.values()) {
			if(p.getStock() < p.getStock_min()) {
				listaMin.add(p);
			}
		}
		return listaMin;
	}    
=======
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ModeloHashMap implements ModeloAbs
{
    private HashMap <Integer,Producto> mapa;
    
    public ModeloHashMap()
    {
       mapa=new HashMap  <Integer,Producto>();
    }

	@Override
	public boolean insertarProducto(Producto p) {
		mapa.put(p.codigo, p);
		return true;
	}

	@Override
	public boolean borrarProducto(int codigo) {
		return  (mapa.remove(codigo) != null);
	}

	@Override
	public boolean modificarProducto(Producto nuevo) {
		return (mapa.containsKey(nuevo.codigo));
	}

	@Override
	public Producto buscarProducto(int codigo) {
		return mapa.get(codigo);
	}

	@Override
	public void imprimirProductosTodos() {
		for ( Producto p: mapa.values()) {
			System.out.println(p);
		}
	}

	@Override
	public List<Producto> obtenerProductos() {
//hago que la lista se llene con los valores de la coleccion
		return new ArrayList<Producto>(mapa.values());
	}

	@Override
	public List<Producto> obtenerProductosStockMin() {
		var listaStockMin = new ArrayList<Producto> ();
		for ( Producto p: mapa.values()) {
			if (p.stock <= p.stock_min) {
			listaStockMin.add(p);
		}
		}
		return listaStockMin;
	}

   // COMPLETAR
    
    
    
>>>>>>> branch 'master' of https://github.com/3r4yn/Proyectocrud.git
}
