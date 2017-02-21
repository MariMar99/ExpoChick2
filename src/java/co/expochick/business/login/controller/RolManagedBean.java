package co.expochick.business.login.controller;

import co.expochick.backend.persistence.entity.Rol;
import co.expochick.backend.persistence.facades.RolFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Mariana
 */
@Named(value = "rolManagedBean")
@Dependent
public class RolManagedBean {

    @EJB private RolFacade rolfcl;
    private Rol rol;
    
    public RolManagedBean() {
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    @PostConstruct
    public void init(){
        rol = new Rol();
    }
    
    public void registrarRol(){
        try {
            rolfcl.create(rol);
        } catch (Exception e) {
        }
    }
    
    public void eliminarRol(Rol rol){
        try {
            rolfcl.remove(rol);
        } catch (Exception e) {
        }
    }
    
    public void modificarRol(){
        try {
            rolfcl.edit(rol);
        } catch (Exception e) {
        }
    }
    
    public List<Rol> listarRol(){
        try {
            return this.rolfcl.findAll();
        } catch (Exception e) {
        }return null;
    }
    
}
