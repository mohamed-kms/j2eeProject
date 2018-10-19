/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Conseillers;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author grace
 */
@Stateless
@LocalBean
public class ConseillersManager {

    @PersistenceContext(unitName = "MBDS_Boukou_Kadri_Projet_J2EE-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    
    /**
     *
     * @return
     */
    public List<Conseillers> getAllConseillers() {
        Query q = em.createNamedQuery("Conseillers.findAll");
        return q.getResultList();
    }

    
    public Conseillers update(Conseillers conseiller) {
        return em.merge(conseiller);
    }

    public Conseillers getConseiller(Long id) {
        return em.find(Conseillers.class, id);
    }

    public Conseillers findById(long id) {
        return em.find(Conseillers.class, id);
    }
}
