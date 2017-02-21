package co.expochick.business.exportacion.controller;

import co.expochick.backend.persistence.entity.PrecioCantidad;
import co.expochick.backend.persistence.facades.PrecioCantidadFacade;
import co.expochick.frontend.util.Managedbean;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mariana
 */
@Named(value = "precioXCantidadManagedBean")
@RequestScoped
public class PrecioCantidadManagedBean implements Serializable, Managedbean<PrecioCantidad>{

    @EJB private PrecioCantidadFacade prefc;
    private PrecioCantidad precioxCant;
    
    public PrecioCantidadManagedBean() {
    }
    
    public PrecioCantidad getPrecioxCant() {
        return precioxCant;
    }

    public void setPrecioxCant(PrecioCantidad precioxCant) {
        this.precioxCant = precioxCant;
    }
    
    
    @PostConstruct
    public void init (){
        precioxCant = new PrecioCantidad();
    }
    
    public void registrarPrecioCant(){
        try {
            prefc.create(precioxCant);
            mensajeExito("Registrado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public void eliminarPrecioCant(PrecioCantidad precioxCant){
        try {
            prefc.remove(precioxCant);
            mensajeExito("Eliminado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public String actualizarPrecioCant(PrecioCantidad precioxCant){
        this.precioxCant = precioxCant;
        return "precioPorCantidadModificar";
    }
    
    public void modificarPrecioCant(){
        try {
            prefc.edit(precioxCant);
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public List<PrecioCantidad> listarPrecioCant(){
        try{
            return this.prefc.findAll();
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

    @Override
    public PrecioCantidad getObject(Integer i) {
        return prefc.find(i);
    }

    
}
