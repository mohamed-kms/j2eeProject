/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Administrateurs;
import entities.Conseillers;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import session.AdminsManager;
import session.ConseillersManager;

/**
 *
 * @author mohamed-kms
 */
@Named(value = "administrateursMBean")
@SessionScoped
public class AdministrateursMBean implements Serializable{

    @EJB
    private ConseillersManager conseillersManager;

    private Long id;
    private Administrateurs admin;
    private String newAdminNom;
    private String newAdminPrenom;
    private String newAdminAdresse;
    private String username;
    private String password;
    @EJB
    private AdminsManager adminsManager;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Administrateurs getAdmin() {
        return admin;
    }

    public String getNewAdminNom() {
        return newAdminNom;
    }

    public void setNewAdminNom(String newAdminNom) {
        this.newAdminNom = newAdminNom;
    }

    public String getNewAdminPrenom() {
        return newAdminPrenom;
    }

    public void setNewAdminPrenom(String newAdminPrenom) {
        this.newAdminPrenom = newAdminPrenom;
    }

    public String getNewAdminAdresse() {
        return newAdminAdresse;
    }

    public void setNewAdminAdresse(String newAdminAdresse) {
        this.newAdminAdresse = newAdminAdresse;
    }
    
    

    public void setAdmin(Administrateurs admin) {
        this.admin = admin;
    }

    public AdminsManager getAdminsManager() {
        return adminsManager;
    }

    public void setAdminsManager(AdminsManager adminsManager) {
        this.adminsManager = adminsManager;
    }
    
    public void creerAdmin(){
        Administrateurs newAdmin = new Administrateurs(newAdminNom, newAdminPrenom, newAdminAdresse);
        this.adminsManager.creerAdministrateurs(newAdmin);
    }
    
    public void creerConseiller(){
        Conseillers newCons = new Conseillers(newAdminNom, newAdminPrenom, newAdminAdresse);
        this.conseillersManager.creerConseillers(newCons);
    }
    
    
    public List<Administrateurs> getAdmins(){
        return adminsManager.getAllAdministrateurs();
    }
    
    public String checkLogin(){
        if(adminsManager.getAllUsernames().contains(username) && adminsManager.getAllPasswords().contains(password)){
            this.admin = adminsManager.findByUsername(username);
            this.id = admin.getId();
            return adminsOperation(this.id);
        }else{
            return "AdministrateurLoginPage";
        }
    }
    
    public String adminsOperation(Long conseillerId){
        return "AdminsOperation?id = " + conseillerId;
    }
    

    /**
     * Creates a new instance of AdministrateursMBean
     */
    public AdministrateursMBean() {
    }
    
}
