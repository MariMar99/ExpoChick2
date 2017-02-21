package co.expochick.business.login.controller;

import co.expochick.backend.persistence.entity.EstadoCliente;
import co.expochick.backend.persistence.facades.EstadoClienteFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Mariana
 */
@Named(value = "estadoClienteManagedBean")
@RequestScoped
public class EstadoClienteManagedBean {

    @EJB private EstadoClienteFacade estClifc;
    private EstadoCliente estCli;
            
    public EstadoClienteManagedBean() {
    }

    public EstadoCliente getEstCli() {
        return estCli;
    }

    public void setEstCli(EstadoCliente estCli) {
        this.estCli = estCli;
    }
    
    @PostConstruct
    public void init(){
        estCli = new EstadoCliente();
    }
    
    public void registrarEstadoCli(){
        try {
            estClifc.create(estCli);
        } catch (Exception e) {
        }
    }
    
    public void eliminarEstadoCli(EstadoCliente estCli){
        try {
            estClifc.remove(estCli);
        } catch (Exception e) {
        }
    }
    
    public void modificarEstadoCli(EstadoCliente estCli){
        try {
            estClifc.edit(estCli);
        } catch (Exception e) {
        }
    }
    
    public List<EstadoCliente> listarEstadoCli(){
        try {
            return estClifc.findAll();
        } catch (Exception e) {
        }return null;
    }
}
