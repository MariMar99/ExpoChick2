package co.expochick.business.exportacion.controller;

import co.expochick.backend.persistence.entity.Inconterm;
import co.expochick.backend.persistence.facades.IncontermFacade;
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
@Named(value = "incotermManagedBean")
@SessionScoped
public class IncotermManagedBean implements Serializable{

    @EJB private IncontermFacade infc;
    private Inconterm inconterm;
    private List<Inconterm> item = null;
    
    public IncotermManagedBean() {
    }

    public Inconterm getInconterm() {
        return inconterm;
    }

    public void setInconterm(Inconterm inconterm) {
        this.inconterm = inconterm;
    }
    
    @PostConstruct
    public void init(){
        inconterm = new Inconterm();
    }
    
    public void registrarInconterm(){
        try {
            infc.create(inconterm);
            mensajeExito("Registrado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public void eliminarInconterm(Inconterm inconterm){
        try {
            infc.remove(inconterm);
            mensajeExito("Eliminado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public String actualizarInconterm(Inconterm inconterm){
        this.inconterm = inconterm;
        return "incontermModificar";
    }
    
    public void modificarInconterm(){
        try {
            infc.edit(inconterm);
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public List<Inconterm> listaInconterms(){
        return infc.findAll();
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
//    public Inconterm getObjectByKey(Integer key) {
//        return infc.find(key);
//    }
    
}
