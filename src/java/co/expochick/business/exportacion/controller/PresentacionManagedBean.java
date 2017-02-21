package co.expochick.business.exportacion.controller;

import co.expochick.backend.persistence.entity.Presentacion;
import co.expochick.backend.persistence.facades.PresentacionFacade;
import co.expochick.frontend.util.Managedbean;
//import co.expochick.frontend.converters.IConverterManagedBean;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
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
@Named(value = "presentacionManagedBean")
//@SessionScoped
@RequestScoped
public class PresentacionManagedBean implements Serializable, Managedbean<Presentacion> {

    private Presentacion presentacion;
    @EJB
    private PresentacionFacade prfc;

    public PresentacionManagedBean() {
    }

    public Presentacion getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(Presentacion presentacion) {
        this.presentacion = presentacion;
    }

    @PostConstruct
    public void init() {
        presentacion = new Presentacion();
    }

    public void registrarPresentacion() {
        try {
            prfc.create(presentacion);
            mensajeExito("Registrado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }

    public void eliminarPresentacion(Presentacion presentacion) {
        try {
            prfc.remove(presentacion);
            mensajeExito("Eliminado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }

    public String actualizarPresentacion(Presentacion presentacion) {
        this.presentacion = presentacion;
        return "presentacionModificar";
    }

    public void modificarPresentacion() {
        try {
            prfc.edit(presentacion);
        } catch (Exception e) {
            mensajeError(e);
        }
    }

    public List<Presentacion> listarPresentacion() {
        try {
            return this.prfc.findAll();
        } catch (Exception e) {
            mensajeError(e);
        }
        return null;
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
        FacesMessage sal = new FacesMessage(FacesMessage.SEVERITY_INFO, "Opereción con Exito : ", msg);
        RequestContext.getCurrentInstance().showMessageInDialog(sal);
    }

    @Override
    public Presentacion getObject(Integer i) {
        return prfc.find(i);
    }
}
