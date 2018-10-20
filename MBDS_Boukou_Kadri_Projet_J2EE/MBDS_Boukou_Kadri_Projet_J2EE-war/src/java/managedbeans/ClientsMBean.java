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
