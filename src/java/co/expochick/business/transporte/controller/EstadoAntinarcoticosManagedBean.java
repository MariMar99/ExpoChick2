package co.expochick.business.transporte.controller;

import co.expochick.backend.persistence.entity.EstadoAntinarcoticos;
import co.expochick.backend.persistence.facades.EstadoAntinarcoticosFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Mariana
 */
@Named(value = "estadoAntinarcoticosManagedBean")
@RequestScoped
public class EstadoAntinarcoticosManagedBean {

    @EJB private EstadoAntinarcoticosFacade estAntifc;
    private EstadoAntinarcoticos estAnti;
    
    public EstadoAntinarcoticosManagedBean() {
    }

    public EstadoAntinarcoticos getEstAnti() {
        return estAnti;
    }

    public void setEstAnti(EstadoAntinarcoticos estAnti) {
        this.estAnti = estAnti;
    }
    
    @PostConstruct
    public void init(){
        estAnti = new EstadoAntinarcoticos();
    }
    
    public void registrarEstadoAnti(){
        try {
            estAntifc.create(estAnti);
        } catch (Exception e) {
        }
    }
    
    public void eliminarEstadoAnti(EstadoAntinarcoticos estAnti){
        try {
            estAntifc.remove(estAnti);
        } catch (Exception e) {
        }
    }
    
    public void modificarEstadoAnti(EstadoAntinarcoticos estAnti){
        try {
            estAntifc.edit(estAnti);
        } catch (Exception e) {
        }
    }
    
    public List<EstadoAntinarcoticos> listarEstadoAnti(){
        try {
            return estAntifc.findAll();
        } catch (Exception e) {
        }return null;
    }
}
