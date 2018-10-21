/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Clients;
import entities.CompteBancaire;
import entities.Conseillers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import session.ClientsManager;
import session.ConseillersManager;
import session.GestionDeCompteBancaire;

/**
 *
 * @author mohamed-kms
 */
@Named(value = "conseillersMBean")
@SessionScoped
public class ConseillersMBean implements Serializable{

    @EJB
    private ClientsManager clientsManager;

    
    private Long id;
    private String newClientNom;
    private String newClientPrenom;
    private String newClientAdresse;
    private String username;
    private String password;
    private Conseillers conseiller;
    
    @EJB
    private ConseillersManager conseillersManager;

    
    
    @EJB
    private GestionDeCompteBancaire gestionDeCompteBancaire;
    
    
    public List<Conseillers> getConseillers(){
        return conseillersManager.getAllConseillers();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewClientNom() {
        return newClientNom;
    }

    public void setNewClientNom(String newClientNom) {
        this.newClientNom = newClientNom;
    }

    public String getNewClientPrenom() {
        return newClientPrenom;
    }

    public void setNewClientPrenom(String newClientPrenom) {
        this.newClientPrenom = newClientPrenom;
    }

    public String getNewClientAdresse() {
        return newClientAdresse;
    }

    public void setNewClientAdresse(String newClientAdresse) {
        this.newClientAdresse = newClientAdresse;
    }
    
    public void creerClient(){
        Clients newClient = new Clients(newClientNom, newClientPrenom, newClientAdresse);
        Set<Clients> proprietaires = new HashSet<>();
        proprietaires.add(newClient);
        CompteBancaire compteBancaire = new CompteBancaire(proprietaires, 0, conseiller);
        List<CompteBancaire> listeComptesBancaire = new ArrayList<>();
        listeComptesBancaire.add(compteBancaire);
        newClient.setListeComptesBancaire(listeComptesBancaire);
        conseiller.getComptesGeres().add(compteBancaire);
        this.clientsManager.creerClient(newClient);
        //this.conseillersManager.update(conseiller);
    }

    public Conseillers getConseiller() {
        return conseiller;
    }

    public void setConseiller(Conseillers Conseiller) {
        this.conseiller = Conseiller;
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
    
    public String conseillersOperation(Long conseillerId){
        return "ConseillersOperation?id = " + conseillerId;
    }
    
    public String comptesGeres(){
        return "ComptesList";
    }

    public String checkLogin(){
        if(conseillersManager.getAllUsernames().contains(username) && conseillersManager.getAllPasswords().contains(password)){
            this.conseiller = conseillersManager.findByUsername(username);
            this.id = conseiller.getId();
            return conseillersOperation(this.id);
        }else{
            return "ConseillerLoginPage";
        }
    }
    /**
     * Creates a new instance of ConseillersMBean
     */
    public ConseillersMBean() {
    }
    
}
