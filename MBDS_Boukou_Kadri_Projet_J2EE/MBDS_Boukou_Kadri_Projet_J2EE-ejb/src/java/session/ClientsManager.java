/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Clients;
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

    
    public Clients update(Clients client) {
        return em.merge(client);
    }

    public Clients getClient(Long id) {
        return em.find(Clients.class, id);
    }

    public Clients findById(long id) {
        return em.find(Clients.class, id);
    }
}
