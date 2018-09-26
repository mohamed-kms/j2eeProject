/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteBancaire;
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
        creerCompte(new CompteBancaire("John Lennon", 150000));
        creerCompte(new CompteBancaire("Paul McCartney", 950000));
        creerCompte(new CompteBancaire("Ringo Starr", 20000));
        creerCompte(new CompteBancaire("Georges Harrisson", 100000));
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
}
