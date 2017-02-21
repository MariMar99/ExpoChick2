package co.expochick.business.transporte.controller;


import co.expochick.backend.persistence.entity.Continente;
import co.expochick.backend.persistence.facades.ContinenteFacade;
//import co.expochick.frontend.converters.IConverterManagedBean;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Mariana
 */
@Named(value = "continenteManagedBean")
//@SessionScoped
@RequestScoped
//public class ContinenteManagedBean implements Serializable, IConverterManagedBean<Continente> {
public class ContinenteManagedBean implements Serializable{

    @EJB private ContinenteFacade contfc;
    private Continente continente;
    
    public ContinenteManagedBean() {
    }

    public ContinenteFacade getCantfc() {
        return contfc;
    }

    public void setCantfc(ContinenteFacade contfc) {
        this.contfc = contfc;
    }
    
    @PostConstruct
    public void init(){
        continente = new Continente();
    }
    
    public List<Continente> listarContinente(){
        return contfc.findAll();
    }

//    @Override
//    public Continente getObjectByKey(Integer key) {
//        return contfc.find(key);
//    }
    //Converter
    
    public Continente getSelect(){
        if (continente == null) {
            continente = new Continente();
            int selectedItemIndex = -1;
        }
        return continente;
    }
    
    @FacesConverter(forClass = Continente.class)
    public static class ContinenteConverter implements Converter {

      @Override
      public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
          if (value == null || value.length() == 0) {
              return null;
          }
          ContinenteConverter controlador = (ContinenteConverter) facesContext.getApplication().getELResolver().
                  getValue(facesContext.getELContext(), null, "continenteManagedBean");
          return controlador.getContinente(getKey(value));
      }

      java.lang.Integer getKey(String value) {
          java.lang.Integer key;
          key = Integer.valueOf(value);
          return key;
      }

      String getStringKey(java.lang.Integer value) {
          StringBuilder sb = new StringBuilder();
          sb.append(value);
          return sb.toString();
      }

      @Override
      public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
          if (object == null) {
              return null;
          }
          if (object instanceof Continente) {
             Continente  o = (Continente) object;
              return getStringKey(o.getIdContinente());
          } else {
              throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Continente.class.getName());
          }
      }

        private Object getContinente(Integer key) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
