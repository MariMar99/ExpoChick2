package co.expochick.business.exportacion.controller;

import co.expochick.backend.persistence.entity.Pedido;
import co.expochick.backend.persistence.facades.PedidoFacade;
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
@Named(value = "pedidoManagedBean")
@SessionScoped
public class PedidoManagedBean implements Serializable{

   @EJB private PedidoFacade pedfc;
   private Pedido pedido;
   
    public PedidoManagedBean() {
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    @PostConstruct
    public void init(){
        pedido = new Pedido();
    }
    
    public void registrarPedido(){
        try {
            pedfc.create(pedido);
            mensajeExito("Registrado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public void eliminarPedido(Pedido pedido){
        try {
            pedfc.remove(pedido);
            mensajeExito("Eliminado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public String actualizarPedido(Pedido pedido){
        this.pedido = pedido;
        return "pedidoModificar";
    }
    
    public void modificarPedido(){
        try {
            pedfc.edit(pedido);
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public List<Pedido> listarPedidos(){
        return pedfc.findAll();
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
//    public Pedido getObjectByKey(Integer key) {
//        return pedfc.find(key);
//    }
    
}
