package co.expochick.business.transporte.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import co.expochick.backend.persistence.entity.Transporte;
import co.expochick.backend.persistence.facades.TransporteFacade;
import java.util.List;
import javax.annotation.PostConstruct;


/**
 *
 * @author Mariana
 */
@Named(value = "transporteManagedBean")
@SessionScoped
public class TransporteManagedBean implements Serializable {

    @EJB private TransporteFacade transfc;
    private Transporte trans;
    
    public TransporteManagedBean() {
    }

    public Transporte getTrans() {
        return trans;
    }

    public void setTrans(Transporte trans) {
        this.trans = trans;
    }
    
    @PostConstruct
    public void init(){
        trans = new Transporte();
    }
    
    public void registrarTransporte(){
        try {
            transfc.create(trans);
        } catch (Exception e) {
        }
    }
    
    public void eliminarTransporte(Transporte trans){
        try {
            transfc.remove(trans);
        } catch (Exception e) {
        }
    }
    
    public void modificarTransporte(Transporte trans){
        try {
            transfc.edit(trans);
        } catch (Exception e) {
        }
    }
    
    public List<Transporte> listarTransporte(){
        try {
            return transfc.findAll();
        } catch (Exception e) {
        }return null;
    }
    
}
