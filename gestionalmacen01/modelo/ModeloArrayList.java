/** Implementa la parte de Modelo de Datos
 * * @author (your name) 
 * @version (a version number or a date)
 */
package gestionalmacen01.modelo;
import java.util.ArrayList;
import java.util.List;

public class ModeloArrayList implements ModeloAbs
{
    protected ArrayList <Producto> lista;
    
    public ModeloArrayList()
    {
       lista=new ArrayList <Producto>();
    }

    @Override
    public boolean insertarProducto ( Producto p){
      assert ( p != null ); // No permito productos nulos  
      return lista.add(p);     
    }
 
    @Override
    public boolean borrarProducto ( int codigo ){
      Producto p = buscarProducto(codigo);
      if (p != null) {
          return lista.remove(p);     
      }
      return false;
    }
    
    @Override
    public Producto buscarProducto ( int codigo) {
          for(Producto p: lista) { 
             if(p.getCodigo() == codigo) { //Miramos si coincide con el que busco..
                return p;//Si coincide devuelve el producto..
             }
          }
        return null; //Si termina el bucle y no encontro nada, devuelve "vacio"..
    }
   
    @Override
    public void imprimirProductosTodos (){
      for (Producto p: lista) {
          System.out.println(p);
      }
    }
    
    @Override
    public boolean modificarProducto (Producto nuevo){
       return buscarProducto(nuevo.getCodigo()) != null;
    }

    @Override
    public List<Producto> obtenerProductosStockMin() {
        List<Producto> sublista = new ArrayList<>();
        for(Producto p: lista) {
            if(p.getStock() <= p.getStock_min()){
                sublista.add(p);
            }
        }
        return sublista;
    }

    @Override
    public List<Producto> obtenerProductos() {
        return lista;
    }
}