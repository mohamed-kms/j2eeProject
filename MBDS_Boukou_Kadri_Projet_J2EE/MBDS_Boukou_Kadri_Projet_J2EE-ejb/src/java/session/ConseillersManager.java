/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Clients;
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
    
    public void creerConseillers(Conseillers conseillers) {
        persist(conseillers);
    }
    
    /**
     *
     * @return
     */
    public List<Conseillers> getAllConseillers() {
        Query q = em.createNamedQuery("Conseillers.findAll");
        return q.getResultList();
    }
    
    public List<String> getAllUsernames() {
        Query q = em.createNamedQuery("Conseillers.findAllUsernames");
        return q.getResultList();
    }
    
    public List<String> getAllPasswords(){
        Query q = em.createNamedQuery("Conseillers.findAllPasswords");
        return q.getResultList();
    }
    
    public Conseillers findByUsername(String username){
        Query q = em.createQuery("SELECT c FROM Conseillers c WHERE c.username = :username");
        q.setParameter("username", username);
        return (Conseillers) q.getResultList().get(0);
    }

    
    public Conseillers update(Conseillers conseiller) {
        return em.merge(conseiller);
    }

    public Conseillers getConseiller(Long id) {
        return em.find(Conseillers.class, id);
    }

    public Conseillers findById(Long id) {
        return em.find(Conseillers.class, id);
    }
}
