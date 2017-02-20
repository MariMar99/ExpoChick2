package co.expochick.frontend.exportacion.controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import co.expochick.backend.persistence.entity.EstadoProducto;
import co.expochick.backend.persistence.facades.EstadoProductoFacade;
import co.expochick.frontend.util.Managedbean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Mariana
 */
@Named(value = "estadoProductoManagedBean")
@RequestScoped
public class EstadoProductoManagedBean implements Serializable, Managedbean<EstadoProducto> {

    @EJB private EstadoProductoFacade estaProfc;
    private EstadoProducto estProducto;
    
    public EstadoProductoManagedBean() {
    }

    public EstadoProducto getEstProducto() {
        return estProducto;
    }

    public void setEstProducto(EstadoProducto estProducto) {
        this.estProducto = estProducto;
    }
    
    @PostConstruct
    public void init (){
        estProducto = new EstadoProducto();
    }
    
    public void registrarEstadoProducto(){
        try {
            estaProfc.create(estProducto);
        } catch (Exception e) {
        }
    }
    
    public void eliminarEstadoProducto(EstadoProducto estProducto){
        try {
            estaProfc.remove(estProducto);
        } catch (Exception e) {
        }
    }
    
    public void modificarEstadoProducto(EstadoProducto estProducto){        
        try {
            estaProfc.edit(estProducto);
        } catch (Exception e) {
        }
    }
    
    public List<EstadoProducto> listarEstadoPro(){
        try {
            return estaProfc.findAll();
        } catch (Exception e) {
        }return null;
    }

    @Override
    public EstadoProducto getObject(Integer i) {
        return estaProfc.find(i);
    }
}
