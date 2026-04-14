/**
 * Clase ModeloHashMap
 * * @author (tu nombre) 
 * @version (fecha)
 */
package gestionalmacen01.modelo;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;

public class ModeloHashMap implements ModeloAbs
{
    private HashMap <Integer,Producto> mapa;
    
    public ModeloHashMap()
    {
       mapa = new HashMap <Integer,Producto>();
    }

	@Override
	public boolean insertarProducto(Producto p) {
		if(mapa.containsKey(p.getCodigo())) return false;
		mapa.put(p.getCodigo(), p);
		return true;
	}

	@Override
	public boolean borrarProducto(int codigo) {
		// remove devuelve el objeto si existía, o null si no.
		return (mapa.remove(codigo) != null);
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
		return mapa.get(codigo);
	}

	@Override
	public void imprimirProductosTodos() {
		for (Producto p : mapa.values()) {
			System.out.println(p);
		}
	}

	@Override
	public List<Producto> obtenerProductos() {
		return new ArrayList<Producto>(mapa.values());
	}

	@Override
	public List<Producto> obtenerProductosStockMin() {
		List<Producto> listaStockMin = new ArrayList<Producto>();
		for (Producto p : mapa.values()) {
			if (p.getStock() < p.getStock_min()) {
				listaStockMin.add(p);
			}
		}
		return listaStockMin;
	}
}