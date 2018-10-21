/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Conseillers;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import session.ConseillersManager;
import session.GestionDeCompteBancaire;

/**
 *
 * @author mohamed-kms
 */
@Named(value = "conseillersMBean")
@SessionScoped
public class ConseillersMBean implements Serializable{

    
    private Long id;
    private String username;
    private String password;
    private Conseillers Conseiller;
    
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

    public Conseillers getConseiller() {
        return Conseiller;
    }

    public void setConseiller(Conseillers Conseiller) {
        this.Conseiller = Conseiller;
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
            this.Conseiller = conseillersManager.findByUsername(username);
            this.id = Conseiller.getId();
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
