package controller;

import EJB.ProductoFacade;
import entity.Producto;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.ShowMessage;

@Named(value = "productoController")
@ViewScoped
public class ProductoController implements Serializable{

    private Producto producto;
    private Producto productoSeleccionado;
    
    @EJB
    private ProductoFacade productoFacade;

    public ProductoController() {
        this.productoFacade = new ProductoFacade();
        this.producto = new Producto();
    }
    
    public void agregarProducto() {
        if (validarProducto() && this.producto.getId() <= 0) {
            productoFacade.create(producto);
            ShowMessage.showInfo("Notificación", "Producto agregado con éxito");
            producto = new Producto(); 
            listarProductos(); 
            return;
        } else if (validarProducto() && this.producto.getId() > 0){
            productoFacade.edit(producto);
            ShowMessage.showInfo("Notificación", "Producto Editado con éxito");
            producto = new Producto(); 
            listarProductos(); 
            return;
        }
    }
    
    public void cargarProducto(Producto  p){
        this.producto = p;
    }
    
    public void seleccionarProductoEliminar(Producto producto) {
        this.productoSeleccionado = producto;
    }
    
    public void eliminar(){
        if (productoSeleccionado != null){
            productoFacade.remove(productoSeleccionado);
            ShowMessage.showInfo("Notificacion", "Se a eliminado el producto");
            producto = new Producto();
            listarProductos();
        } else {
            ShowMessage.showError("Notificacion", "A ourrido un error en la eliminacion del producto");
            return;
        }
    }
    
    // Método para validar los campos del producto
    private boolean validarProducto() {
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            ShowMessage.showFatal("Notificación", "El nombre es obligatorio");
            return false;
        }

        if (!producto.getNombre().matches("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s]+$")) {
            ShowMessage.showFatal("Notificación", "El nombre solo debe contener letras");
            producto.setNombre("");
            return false;
        }

        if (producto.getDescripcion() == null || producto.getDescripcion().isEmpty()) {
            ShowMessage.showFatal("Notificación", "La descripción es obligatoria");
            return false;
        }

        if (!producto.getDescripcion().matches("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s]+$")) {
            ShowMessage.showFatal("Notificación", "La descripción solo debe contener letras");
            producto.setDescripcion("");
            return false;
        }

        if (producto.getPrecio() == null || producto.getPrecio().doubleValue() <= 0.00) {
            ShowMessage.showFatal("Notificación", "El precio debe ser mayor a 0 y en formato válido (ej: 00.00)");
            return false;
        }

        return true;
    }
    
    public void validarNombre() {
        List<Producto> productosEncontrados = productoFacade.findByNombre(producto.getNombre());

        if (!productosEncontrados.isEmpty()) {
            ShowMessage.showFatal("Notificación", "El producto ya existe");
            producto.setNombre("");
        }
    }

    
    public List<Producto> listarProductos(){
        List<Producto> lista = this.productoFacade.findAll();
        return lista;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }
}
