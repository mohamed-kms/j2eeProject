/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Clients;
import entities.CompteBancaire;
import entities.Conseillers;
import entities.Operations;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class GestionDeCompteBancaire {

    @PersistenceContext(unitName = "MBDS_Boukou_Kadri_Projet_J2EE-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void creerCompte(CompteBancaire cb) {
        persist(cb);
    }

    public List<CompteBancaire> getAllComptes() {
        Query q = em.createNamedQuery("CompteBancaire.findAll");
        return q.getResultList();
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public void creerComptesTest() {
        Clients client1 = new Clients("John", "Lennon", "ATL");
        Clients client2 = new Clients("Aaron", "Lennon", "ATL");
        Clients client3 = new Clients("Shawn", "Lewis", "ATL");
        Clients client4 = new Clients("Deborrah", "Bridle", "ATL");
        Conseillers c = new Conseillers("Dwayne", "Johnson", "ATL");
        Conseillers c1 = new Conseillers("Dominique", "Terroto", "ATL");
        Set<Clients> proprietaires1 = new HashSet<>();
        Set<Clients> proprietaires2 = new HashSet<>();
        Set<Clients> proprietaires3 = new HashSet<>();

        proprietaires1.add(client2);
        proprietaires1.add(client1);

        proprietaires2.add(client1);
        proprietaires2.add(client4);

        proprietaires3.add(client4);
        proprietaires3.add(client3);
        proprietaires3.add(client2);
        creerCompte(new CompteBancaire(proprietaires1, 150000, c));
        creerCompte(new CompteBancaire(proprietaires2, 150000, c1));
        creerCompte(new CompteBancaire(proprietaires3, 150000, c));

    }

    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }

    public CompteBancaire getCompteBancaire(Long id) {
        return em.find(CompteBancaire.class, id);
    }

    public CompteBancaire findById(long id) {
        return em.find(CompteBancaire.class, id);
    }

    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        int val = source.retirer(montant);
        if (val == 0) {
            // La source n'a plus assez d'argent !!
            // Il faudrait afficher un message d'erreur.
            return;
        }
        destination.deposer(montant);
        update(source);
        update(destination);
    }
    
    public List<Operations> findMesOperations(Long id){
        Query q = em.createNamedQuery("SELECT o FROM Operations o WHERE o.compteBancaire_id = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }

    public void cloturerCompte(CompteBancaire compteBancaire) {
        em.remove(em.merge(compteBancaire));
    }
}
