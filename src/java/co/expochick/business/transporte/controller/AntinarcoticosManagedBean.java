package co.expochick.business.transporte.controller;

import co.expochick.backend.persistence.entity.Antinarcoticos;
import co.expochick.backend.persistence.facades.AntinarcoticosFacade;
import co.expochick.frontend.util.Managedbean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Mariana
 */
@Named(value = "antinarcoticosManagedBean")
@RequestScoped
public class AntinarcoticosManagedBean implements Serializable, Managedbean<Antinarcoticos> {

    @EJB private AntinarcoticosFacade antifc;
    private Antinarcoticos anti;
    
    public AntinarcoticosManagedBean() {
    }

    public Antinarcoticos getAnti() {
        return anti;
    }

    public void setAnti(Antinarcoticos anti) {
        this.anti = anti;
    }
    
    @PostConstruct
    public void init(){
        anti = new Antinarcoticos();
    }
    
    public void registrarAntinarcoticos(){
        try {
            antifc.create(anti);
        } catch (Exception e) {
        }
    }
    
    public void eliminarAntinarcoticos(Antinarcoticos anti){
        try {
            antifc.remove(anti);
        } catch (Exception e) {
        }
    }
    
    public String actualizarAntinarcoticos(Antinarcoticos anti){
        this.anti = anti;
        return "antinarcoticosModificar";
    }
    
    public void modificarAntinarcoticos(){
        try {
            antifc.edit(anti);
        } catch (Exception e) {
        }
    }
    
    public List<Antinarcoticos> listarAntinarcoticos(){
        try {
            return antifc.findAll();
        } catch (Exception e) {
        }return null;
    }

    @Override
    public Antinarcoticos getObject(Integer i) {
        return antifc.find(i);
    }
    
}
