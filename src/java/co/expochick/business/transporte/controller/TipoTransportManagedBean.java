/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.expochick.business.transporte.controller;

import co.expochick.backend.persistence.entity.TipoTransporte;
import co.expochick.backend.persistence.facades.TipoTransporteFacade;
import co.expochick.frontend.util.Managedbean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Mariana
 */
@Named(value = "tipoTransportManagedBean")
@SessionScoped
public class TipoTransportManagedBean implements Serializable, Managedbean <TipoTransporte> 
{

    @EJB private TipoTransporteFacade tipoTransfc;
    private TipoTransporte tipoTrans;
    
    public TipoTransportManagedBean() {
    }
    
       
    public TipoTransporte getTipoTrans() {
        return tipoTrans;
    }

    public void setTipoTrans(TipoTransporte tipoTrans) {
        this.tipoTrans = tipoTrans;
    }
    
    @PostConstruct
    public void init(){
        tipoTrans = new TipoTransporte();
    }
    
    public void registrarTipoTrans(){
        try {
            tipoTransfc.create(tipoTrans);
        } catch (Exception e) {
        }
    }
    
    public void eliminarTipoTrans(){
        try {
            tipoTransfc.remove(tipoTrans);
        } catch (Exception e) {
        }
    }
    
    public List<TipoTransporte> listarTipoTrans(){
        return tipoTransfc.findAll();
    }
    
    //Converter
    
    public TipoTransporte getSelect(){
        if (tipoTrans == null) {
            tipoTrans = new TipoTransporte();
            int selectedItemIndex = -1;
        }
        return tipoTrans;
    }

    @Override
    public TipoTransporte getObject(Integer i) {
        return tipoTransfc.find(i);
    }
    
    
    
}
