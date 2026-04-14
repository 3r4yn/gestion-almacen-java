/** Implementa la parte de Modelo de Datos
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package gestionalmacen01.modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModeloArrayList implements ModeloAbs
{
    protected ArrayList <Producto> lista;
    
    public ModeloArrayList()
    {
       lista=new ArrayList <Producto>();
    }

    public boolean insertarProducto ( Producto p){
      assert ( p != null ); // No permito productos nulos  
      return lista.add(p);     
    }
 
    public boolean borrarProducto ( int codigo ){
      Producto p = buscarProducto(codigo);
      if (p != null) {
    	  return lista.remove(p);     }
      return false;
    }
    
    public Producto buscarProducto ( int codigo) {
    	  for(Producto p: lista) { 
    		 if(p.getCodigo() == codigo) { //Miramos si coincide con el que busco..
    			return p;//Si coincide devuelve el producto..
    		 }
    	  }
    	return null; //Si termina el bucle y no encontro nada, devuelve "vacio"..
    }
   
    // Funciona pero no es una solución independiente del la mécanismo de salida.
    // El acceso a datos debe ser independiente de la visualización de los mismos.
    public void imprimirProductosTodos (){
      for (Producto p: lista) {
    	  System.out.println(p);
      }
    }
    
    
    // Solo chequea si el producto ya existia en el almacen.
    // No tiene que hacer nada pues se ha cambiado la referencia
    public boolean modificarProducto (Producto nuevo){
       return true;
    }

    // Devuelvo una lista con los productos con stock mínimo
    // Será el programa principal quien se encargue de mostrarlos
	public List<Producto> obtenerProductosStockMin() {
			List<Producto> sublista = new ArrayList<>();
			for(Producto p: lista) {
			if(p.getStock() < p.getStock_min()){
				sublista.add(p);
			}
		}
		return sublista;
	}

	

    // Devuelvo la lista con los datos
	public List<Producto> obtenerProductos() {
		return lista;
	}
    
     
}    
