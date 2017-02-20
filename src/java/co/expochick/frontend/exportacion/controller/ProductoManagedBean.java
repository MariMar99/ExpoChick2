package co.expochick.frontend.exportacion.controller;

import co.expochick.backend.persistence.entity.Producto;
import co.expochick.backend.persistence.facades.ProductoFacade;
//import co.expochick.frontend.converters.IConverterManagedBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mariana
 */
@Named(value = "productoManagedBean")
@RequestScoped
public class ProductoManagedBean implements Serializable{

    @EJB private ProductoFacade profc;
    private Producto producto;
    
    public ProductoManagedBean() {
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    @PostConstruct
    public void init(){
        producto = new Producto();
    }
    
    public void registrarProducto(){
        try {
            profc.create(producto);
            mensajeExito("Registrado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public void eliminarProducto(Producto producto){
        try {
            profc.remove(producto);
            mensajeExito("Eliminado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public void actualizarProducto(Producto product){
        this.producto = product;
    }
    
    public void modificarProducto(){
        try {
            profc.edit(producto);
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public List<Producto> listarProducto(){
        try{
            return this.profc.findAll();
        }catch (Exception e){
            mensajeError (e);
        } return null;
    }
    
    
    private void mensajeError(Exception e) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se ha Producido el siguiente Error: ", e.getMessage()));
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Insertar:", e.getMessage());
        RequestContext.getCurrentInstance().showMessageInDialog(msg);
    }

    private void mensajeExito(String operacion) {
        String msg = "Se ha realizado exitosamente la operacion de " + operacion;
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
        FacesMessage sal = new FacesMessage(FacesMessage.SEVERITY_INFO,"Opereción con Exito : ", msg);
        RequestContext.getCurrentInstance().showMessageInDialog(sal);
    }

//    @Override
//    public Producto getObjectByKey(Integer key) {
//        return profc.find(key);
//    }
    
}
