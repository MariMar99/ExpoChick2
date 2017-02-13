/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.expochick.backend.persistence.facades;

import co.expochick.backend.persistence.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author APRENDIZ
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ExpoChickPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
 
    public Usuario iniciarSesion(Usuario us){
     TypedQuery<Usuario> q = em.createQuery("FROM Usuario us WHERE us.cedula = ?1 and us.clave = ?2 ", Usuario.class);
        q.setParameter(1, us.getCedula());
        q.setParameter(2, us.getClave());
        if (!q.getResultList().isEmpty()){
            return q.getResultList().get(0);
        } else {
            return null;
        }
    }
            
            
}


