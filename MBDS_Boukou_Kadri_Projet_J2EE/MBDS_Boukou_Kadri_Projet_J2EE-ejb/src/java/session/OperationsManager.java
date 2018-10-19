/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteBancaire;
import entities.Operations;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mohamed-kms
 */

@Stateless
@LocalBean
public class OperationsManager {
    
    @PersistenceContext(unitName = "MBDS_Boukou_Kadri_Projet_J2EE-ejbPU")
    private EntityManager em;
    
    public void persist(Object object){
        em.persist(object);
    }
    
    public List<Operations> getAllOperations(){
        Query q = em.createNamedQuery("Operations.findAll");
        return q.getResultList();
    }
    
  public void creerOperation(Operations op) {
        persist(op);
    }
}
