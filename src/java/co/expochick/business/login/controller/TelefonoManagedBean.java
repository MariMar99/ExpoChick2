package co.expochick.business.login.controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import co.expochick.backend.persistence.entity.Telefono;
import co.expochick.backend.persistence.facades.TelefonoFacade;
import co.expochick.frontend.util.Managedbean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Mariana
 */
@Named(value = "telefonoManagedBean")
@RequestScoped
public class TelefonoManagedBean implements Serializable, Managedbean<Telefono> {

    @EJB private TelefonoFacade telfc;
    private Telefono tel;
    
    public TelefonoManagedBean() {
    }

    public Telefono getTel() {
        return tel;
    }

    public void setTel(Telefono tel) {
        this.tel = tel;
    }
    
    @PostConstruct
    public void init(){
        tel = new Telefono();
    }
    
    public void registrarTel(){
        try {
            telfc.create(tel);
        } catch (Exception e) {
        }
    }
    
    public void eliminarTel (Telefono tel){
        try {
            telfc.remove(tel);
        } catch (Exception e) {
        }
    }
    
    public void modificarTel(Telefono tel){
        try {
            telfc.edit(tel);
        } catch (Exception e) {
        }
    }
    
    public List<Telefono> listarTel(){
        try {
            return telfc.findAll();
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Telefono getObject(Integer i) {
        return telfc.find(i);
    }
}
