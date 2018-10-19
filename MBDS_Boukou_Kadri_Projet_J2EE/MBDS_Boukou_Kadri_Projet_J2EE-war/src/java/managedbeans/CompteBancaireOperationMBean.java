/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.CompteBancaire;
import entities.Operations;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import session.GestionDeCompteBancaire;
import session.OperationsManager;

/**
 *
 * @author mohamed-kms
 */
@Named(value = "compteBancaireOperationMBean")
@ViewScoped
public class CompteBancaireOperationMBean implements Serializable {

    private Long id;
    private Long compteDestinataire;
    private CompteBancaire compteBancaire;
    private int montant;

    public Long getCompteDestinataire() {
        return compteDestinataire;
    }

    public void setCompteDestinataire(Long compteDestinataire) {
        this.compteDestinataire = compteDestinataire;
    }
    
    
    
    private String operationsPossibles;

    public String getOperationsPossibles() {
        return operationsPossibles;
    }

    public void setOperationsPossibles(String operationsPossibles) {
        this.operationsPossibles = operationsPossibles;
    }

    public List<CompteBancaire> getComptes(){
        return compteBancaireManager.getAllComptes();
    }
    
    public CompteBancaireOperationMBean() {
  
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    
    
    

    @EJB
    private GestionDeCompteBancaire compteBancaireManager;

    @EJB
    private OperationsManager operationsManager;
    

    public List<Operations> getOperations() {
        return operationsManager.getAllOperations();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public CompteBancaire getDetails(){
        return compteBancaire;
    }
    
    public String update(){
        System.out.println("###UPDATE###");
        compteBancaire = compteBancaireManager.update(compteBancaire);
        return "ComptesList";
    }
    
    /**
     * Action handler - renvoie vers la page qui affiche la liste des clients
     */
    public String list(){
        System.out.println("###LIST###");
        return "ComptesList";
    }
    
    public void loadCompteBancaire(){
        this.compteBancaire = compteBancaireManager.getCompteBancaire(id);
    }
    
    public void effectuerOperation(){
        if(operationsPossibles.equals("ajouter")){
            compteBancaire.deposer(montant);
            operationsManager.creerOperation(new Operations(operationsPossibles, montant, compteBancaire));
        }else{
            compteBancaire.retirer(montant);
            operationsManager.creerOperation(new Operations(operationsPossibles, montant, compteBancaire));
        }
        compteBancaireManager.update(compteBancaire);
    }
    
    public void transferer() {
    // vrai si on peut faire le transfert
    // permet d'afficher toutes les messages d'erreur en 1 fois
            System.out.println(compteDestinataire);

    boolean ok = true;
    CompteBancaire compteSource = compteBancaire;
        System.out.println(compteSource);
    if (compteSource == null) {
      String msg = "Compte source n'existe pas";
      FacesMessage facesMsg =
              new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
      FacesContext.getCurrentInstance().addMessage("transfert:source", facesMsg);
      ok = false;
    }
    CompteBancaire compteDestination =
            compteBancaireManager.findById((long) compteDestinataire);
            System.out.println(compteDestination);

    if (compteDestination == null) {
      String msg = "Compte destination n'existe pas";
      FacesMessage facesMsg =
              new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
      FacesContext.getCurrentInstance().addMessage("transfert:destination", facesMsg);
      ok = false;
    }
    // Tester s'il y a assez d'argent sur le compte source
    if (compteSource != null) {
      double soldeCompteSource = compteSource.getSolde();
      if (soldeCompteSource < montant) {
        String msg = "Pas assez d'argent sur le compte de "
                + compteSource.getProprietaires();
        FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage("transfert:montant", facesMsg);
        ok = false;
      }
    }
    if (ok) {
        operationsManager.creerOperation(new Operations("virement rentrant", montant, compteSource));
        operationsManager.creerOperation(new Operations("virement sortant", montant, compteDestination));
      compteBancaireManager.transferer(compteSource, compteDestination,
              montant);
    }
  }
    
    public String cloturerCompte() {
    compteBancaireManager.cloturerCompte(compteBancaire);
    return null;
  }
    
}
