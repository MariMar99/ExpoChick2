package co.expochick.business.exportacion.controller;

import co.expochick.backend.persistence.entity.Trazabilidad;
import co.expochick.backend.persistence.facades.TrazabilidadFacade;
//import com.expochick.frontend.converters.IConverterManagedBean;
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
@Named(value = "trazabilidadManagedBean")
@RequestScoped
public class TrazabilidadManagedBean implements Serializable{

    @EJB private TrazabilidadFacade trafc;
    private Trazabilidad trazabilidad;
    
    public TrazabilidadManagedBean() {
    }

    public Trazabilidad getTrazabilidad() {
        return trazabilidad;
    }

    public void setTrazabilidad(Trazabilidad trazabilidad) {
        this.trazabilidad = trazabilidad;
    }
    
    @PostConstruct
    public void init(){
        trazabilidad = new Trazabilidad();
    }
    
    public void registrarTrazabilidad(){
        try {
            trafc.create(trazabilidad);
            mensajeExito("Registrado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public void eliminarTrazabilidad(Trazabilidad trazabilidad){
        try {
            trafc.remove(trazabilidad);
            mensajeExito("Eliminado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public String actualizarTrazbilidad(Trazabilidad trazabilidad){
        this.trazabilidad = trazabilidad;
        return "trazabilidadModificar";
    }
    
    public void modificarTrazabilidad(){
        try {
            trafc.edit(trazabilidad);
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public List<Trazabilidad> listaTrazabilidad(){
        return trafc.findAll();
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
//    public Trazabilidad getObjectByKey(Integer key) {
//        return trafc.find(key);
//    }
}
