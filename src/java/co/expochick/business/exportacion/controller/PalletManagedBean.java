package co.expochick.business.exportacion.controller;

import co.expochick.backend.persistence.entity.Pallet;
import co.expochick.backend.persistence.facades.PalletFacade;
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
@Named(value = "palletManagedBean")
@SessionScoped
public class PalletManagedBean implements Serializable{

    @EJB private PalletFacade pllfc;
    private Pallet pallet;
            
    public PalletManagedBean() {
    }

    public Pallet getPallet() {
        return pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }
    
    @PostConstruct
    public void init(){
        pallet = new Pallet();
    }
    
    public void registrarPallet(){
        try {
            pllfc.create(pallet);
            mensajeExito("Registrado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public void eliminarPallet(Pallet pallet){
        try {
            pllfc.remove(pallet);
            mensajeExito("Eliminado");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public String actualizarPallet(Pallet pallet){
        this.pallet = pallet;
        return "palletModificar";
    }
    
    public void modificarPallet(){
        try {
            pllfc.edit(pallet);
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public List<Pallet> listarPallets(){
        return pllfc.findAll();
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
//    public Pallet getObjectByKey(Integer key) {
//        return pllfc.find(key);
//    }
}
