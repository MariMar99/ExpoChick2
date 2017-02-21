package co.expochick.business.transporte.controller;

import co.expochick.backend.persistence.entity.Ciudad;
import co.expochick.backend.persistence.facades.CiudadFacade;
import co.expochick.frontend.util.Managedbean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Mariana
 */
@Named(value = "ciudadManagedBean")
@RequestScoped
public class CiudadManagedBean implements Serializable, Managedbean<Ciudad> {

    @EJB private CiudadFacade ciudadfc;
    private Ciudad ciudad;
    
    public CiudadManagedBean() {
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
   
    @PostConstruct
    public void init(){
        ciudad = new Ciudad();
    }
    
    public void registrarCiudad(){
        try {
            ciudadfc.create(ciudad);
        } catch (Exception e) {
        }
    }
    
    public void modificarCiudad(){
        try {
            ciudadfc.edit(ciudad);
        } catch (Exception e) {
        }
    }
    
    public List<Ciudad> listarCiudad(){
        return ciudadfc.findAll();        
    }
    
    //Converter
    
    public Ciudad getSelect(){
        if (ciudad == null) {
            ciudad = new Ciudad();
            int selectedItemIndex = -1;
        }
        return ciudad;
    }

    @Override
    public Ciudad getObject(Integer i) {
        return ciudadfc.find(i);
    }



}