package co.expochick.business.transporte.controller;

import co.expochick.backend.persistence.entity.Pais;
import co.expochick.backend.persistence.facades.PaisFacade;
//import co.expochick.frontend.converters.IConverterManagedBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Mariana
 */
@Named(value = "paisManagedBean")
@SessionScoped
//public class PaisManagedBean implements Serializable, IConverterManagedBean<Pais> {
public class PaisManagedBean implements Serializable{

    @EJB private PaisFacade paisfc;
    private Pais pais;
    
    public PaisManagedBean() {
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    @PostConstruct
    public void init(){
        pais = new Pais();
    }
    
    public void registrarPais(){
        try {
            paisfc.create(pais);
        } catch (Exception e) {
        }
    }
    
    public List<Pais> listarPais(){
        try {
            return paisfc.findAll();
        } catch (Exception e) {
        }return null;
    }

//    @Override
    public Pais getObjectByKey(Integer key) {
        return paisfc.find(key);
    }
    
}
