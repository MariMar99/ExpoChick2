/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.expochick.frontend.login.controller;

import co.expochick.backend.persistence.entity.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mariana
 */
@Named(value = "seguridadController")
@SessionScoped
public class seguridadController implements Serializable {

    public void verificarSesion(){
       try {
         Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioL");
          if(user == null){
              FacesContext.getCurrentInstance().getExternalContext().redirect("./../permisos.xhtml");
              
          }         
       } catch (Exception e) {
           
       }
   }
    
    public void cerrarSesion(){
       FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
   }
    
    public seguridadController() {
    }
    
}
