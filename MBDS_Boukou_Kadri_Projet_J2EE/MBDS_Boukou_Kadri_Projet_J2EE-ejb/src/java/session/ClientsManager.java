/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Clients;
<<<<<<< HEAD
import entities.Conseillers;
=======
>>>>>>> 4c1b497b79b06ec2472bc9dbdb9c95d8d0a68b2a
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
<<<<<<< HEAD
 * @author mohamed-kms
=======
 * @author grace
>>>>>>> 4c1b497b79b06ec2472bc9dbdb9c95d8d0a68b2a
 */
@Stateless
@LocalBean
public class ClientsManager {

    @PersistenceContext(unitName = "MBDS_Boukou_Kadri_Projet_J2EE-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public void creerClient(Clients clients) {
        persist(clients);
    }

    /**
     *
     * @return
     */
    public List<Clients> getAllClients() {
        Query q = em.createNamedQuery("Clients.findAll");
        return q.getResultList();
    }
<<<<<<< HEAD
    
    public List<String> getAllUsernames() {
        Query q = em.createNamedQuery("Clients.findAllUsernames");
        return q.getResultList();
    }
    
    public List<String> getAllPasswords(){
        Query q = em.createNamedQuery("Clients.findAllPasswords");
        return q.getResultList();
    }
=======
>>>>>>> 4c1b497b79b06ec2472bc9dbdb9c95d8d0a68b2a

    
    public Clients update(Clients client) {
        return em.merge(client);
    }

    public Clients getClient(Long id) {
        return em.find(Clients.class, id);
    }

<<<<<<< HEAD
    public Clients findById(Long id) {
        return em.find(Clients.class, id);
    }
    
    public Clients findByUsername(String username){
        Query q = em.createQuery("SELECT c FROM Clients c WHERE c.username = :username");
        q.setParameter("username", username);
        return (Clients) q.getResultList().get(0);
    }
   

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
=======
    public Clients findById(long id) {
        return em.find(Clients.class, id);
    }
>>>>>>> 4c1b497b79b06ec2472bc9dbdb9c95d8d0a68b2a
}
