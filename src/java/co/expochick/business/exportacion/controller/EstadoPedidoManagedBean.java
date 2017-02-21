package co.expochick.business.exportacion.controller;

import co.expochick.backend.persistence.entity.EstadoPedido;
import co.expochick.backend.persistence.facades.EstadoPedidoFacade;
import co.expochick.frontend.util.Managedbean;
//import com.expochick.frontend.converters.IConverterManagedBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mariana
 */
@Named(value = "estadoPedidoManagedBean")
@SessionScoped
public class EstadoPedidoManagedBean implements Serializable{

    @EJB private EstadoPedidoFacade espfc;
    private EstadoPedido estadoPedido;
    
    public EstadoPedidoManagedBean() {
    }

   
    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }
    
    
    
    
    @PostConstruct
    public void init(){
        estadoPedido = new EstadoPedido();
    }
    
    public void registrarEstadoPedido(){
        try {
            espfc.create(estadoPedido);
            mensajeExito("Registrado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public void eliminarEstadoPedido(){
        try {
            espfc.remove(estadoPedido);
            mensajeExito("Eliminado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public String actualizarEstadoPedido(EstadoPedido estadoPedido){
        this.estadoPedido = estadoPedido;
        return "estadoPedidoModificar";
    }
    
    public void modificarEstadoPedido(){
        try {
            espfc.edit(estadoPedido);
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public List<EstadoPedido> listarEstadoPedidos(){
        return espfc.findAll();
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

   

    
}
