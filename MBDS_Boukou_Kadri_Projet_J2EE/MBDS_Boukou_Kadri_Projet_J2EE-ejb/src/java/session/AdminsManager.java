/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Administrateurs;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mohamed-kms
 */
@Stateless
@LocalBean
public class AdminsManager {

    @PersistenceContext(unitName = "MBDS_Boukou_Kadri_Projet_J2EE-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public void creerAdministrateurs(Administrateurs administrateur) {
        persist(administrateur);
    }
    
    public void initBase() {
        Administrateurs a = new Administrateurs("EDOUARD", "Amosse", "Nice");
        creerAdministrateurs(a);

    }

    /**
     *
     * @return
     */
    public List<Administrateurs> getAllAdministrateurs() {
        Query q = em.createNamedQuery("Administrateurs.findAll");
        return q.getResultList();
    }
    
    public List<String> getAllUsernames() {
        Query q = em.createNamedQuery("Administrateurs.findAllUsernames");
        return q.getResultList();
    }
    
    public List<String> getAllPasswords(){
        Query q = em.createNamedQuery("Administrateurs.findAllPasswords");
        return q.getResultList();
    }
    
    public Administrateurs update(Administrateurs administrateur) {
        return em.merge(administrateur);
    }

    public Administrateurs getAdministrateur(Long id) {
        return em.find(Administrateurs.class, id);
    }

    public Administrateurs findById(Long id) {
        return em.find(Administrateurs.class, id);
    }
    
    public Administrateurs findByUsername(String username){
        Query q = em.createQuery("SELECT c FROM Administrateurs c WHERE c.username = :username");
        q.setParameter("username", username);
        return (Administrateurs) q.getResultList().get(0);
    }
}
