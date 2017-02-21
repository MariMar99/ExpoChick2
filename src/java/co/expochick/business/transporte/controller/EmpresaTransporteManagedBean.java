package co.expochick.business.transporte.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import co.expochick.backend.persistence.entity.EmpresaTransporte;
import co.expochick.backend.persistence.facades.EmpresaTransporteFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Mariana
 */
@Named(value = "empresaTransporteManagedBean")
@SessionScoped
public class EmpresaTransporteManagedBean implements Serializable {

   @EJB private EmpresaTransporteFacade empTransfc;
   private EmpresaTransporte empTrans;
    
    public EmpresaTransporteManagedBean() {
    }

    public EmpresaTransporte getEmpTrans() {
        return empTrans;
    }

    public void setEmpTrans(EmpresaTransporte empTrans) {
        this.empTrans = empTrans;
    }
    
    @PostConstruct 
    public void init(){
        empTrans = new EmpresaTransporte();
    }
    
    public void registrarEmpresaTrans(){
        try {
            empTransfc.create(empTrans);
        } catch (Exception e) {
        }
    }
    
    public void eliminarEmpresaTrans(EmpresaTransporte empTrans){
        try {
            empTransfc.remove(empTrans);
        } catch (Exception e) {
        }
    }
    
    public void modificarEmpresaTrans(){
        try {
            empTransfc.edit(empTrans);
        } catch (Exception e) {
        }
    }
    
    public List<EmpresaTransporte> listaEmpresaTrans(){
        try {
            return empTransfc.findAll();
        } catch (Exception e) {
        }return null;
    }
    
    
    
    
}
