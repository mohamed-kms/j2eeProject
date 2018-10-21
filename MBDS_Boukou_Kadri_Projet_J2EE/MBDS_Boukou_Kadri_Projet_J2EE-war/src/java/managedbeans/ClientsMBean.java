/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Clients;
import entities.CompteBancaire;
import entities.Operations;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import session.ClientsManager;
import session.GestionDeCompteBancaire;
import session.OperationsManager;

/**
 *
 * @author mohamed-kms
 */
@Named(value = "clientsMBean")
@SessionScoped
public class ClientsMBean implements Serializable{

    private Long id;
    private Clients client;
    private String username;
    private String password;
    private Long idCompteChoisi;
    private Long compteDestinataire;
    private CompteBancaire compteChoisi;
    private String operationsPossibles;
        private int montant;


    
    @EJB
    private ClientsManager clientsManager;
    
    @EJB
    private OperationsManager operationsManager;
    
    @EJB
    private GestionDeCompteBancaire compteBancaireManager;

    public Long getIdCompteChoisi() {
        return idCompteChoisi;
    }

    public void setIdCompteChoisi(Long idCompteChoisi) {
        this.idCompteChoisi = idCompteChoisi;
    }

    
    public List<Clients> getClients(){
        return clientsManager.getAllClients();
    }
    
    public List<String> getPasswords(){
        return clientsManager.getAllPasswords();
    }
    
    public List<String> getUsernames(){
        return clientsManager.getAllUsernames();
    }

    public CompteBancaire getCompteChoisi() {
        return compteChoisi;
    }

    public void setCompteChoisi(CompteBancaire compteChoisi) {
        this.compteChoisi = compteChoisi;
    }

    public String getOperationsPossibles() {
        return operationsPossibles;
    }

    public void setOperationsPossibles(String operationsPossibles) {
        this.operationsPossibles = operationsPossibles;
    }

    public Long getCompteDestinataire() {
        return compteDestinataire;
    }

    public void setCompteDestinataire(Long compteDestinataire) {
        this.compteDestinataire = compteDestinataire;
    }
    
    

    public String effectuerOperation(){
        CompteBancaire compteDestination =
            compteBancaireManager.findById((long) idCompteChoisi);
        if(operationsPossibles.equals("ajouter")){
            compteDestination.deposer(montant);
            operationsManager.creerOperation(new Operations(operationsPossibles, montant, compteDestination));
        }else{
            compteDestination.retirer(montant);
            operationsManager.creerOperation(new Operations(operationsPossibles, montant, compteDestination));
        }
        compteBancaireManager.update(compteDestination);
        return clientsOperation(id);
    }    
    
    public void transferer() {
    // vrai si on peut faire le transfert
    // permet d'afficher toutes les messages d'erreur en 1 fois
    boolean ok = true;
    CompteBancaire compteSource = compteBancaireManager.findById((long) idCompteChoisi);;
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

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public OperationsManager getOperationsManager() {
        return operationsManager;
    }

    public void setOperationsManager(OperationsManager operationsManager) {
        this.operationsManager = operationsManager;
    }

    public GestionDeCompteBancaire getCompteBancaireManager() {
        return compteBancaireManager;
    }

    public void setCompteBancaireManager(GestionDeCompteBancaire compteBancaireManager) {
        this.compteBancaireManager = compteBancaireManager;
    }
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientsManager getClientsManager() {
        return clientsManager;
    }

    public void setClientsManager(ClientsManager clientsManager) {
        this.clientsManager = clientsManager;
    }
    
    public void loadClient(){
        this.client = clientsManager.getClient(id);
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    /**
     *
     * @param clientId
     * @return
     */
    public String clientsOperation(Long clientId){
        return "ClientsOperation?id = " + clientId;
    }
    
    public String checkLogin(){
        if(getUsernames().contains(username) && getPasswords().contains(password)){
            this.client = clientsManager.findByUsername(username);
            this.id = client.getId();
            return clientsOperation(this.id);
        }else{
            return "ClientLoginPage";
        }
    }
    
    public String retraitDepot(){
        return "ClientsRetraitDepot";
    }
    
    public String comptesOperation(Long compteId){
        return "ComptesOperation?id = " + compteId;
    }
    
    public String virement(){
        return "ClientsVirement";
    }
    
    public String cloturer(Long compteId){
        return "ComptesCloturer?id = " + compteId;
    }

}
