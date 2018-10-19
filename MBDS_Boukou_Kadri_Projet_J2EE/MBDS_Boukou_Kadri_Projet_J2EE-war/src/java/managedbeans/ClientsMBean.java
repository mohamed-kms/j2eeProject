/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

<<<<<<< HEAD
import entities.Clients;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import session.ClientsManager;

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
    
    @EJB
    private ClientsManager clientsManager;

    public List<Clients> getClients(){
        return clientsManager.getAllClients();
    }
=======
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author grace
 */
@Named(value = "clientsMBean")
@Dependent
public class ClientsMBean {

>>>>>>> 4c1b497b79b06ec2472bc9dbdb9c95d8d0a68b2a
    /**
     * Creates a new instance of ClientsMBean
     */
    public ClientsMBean() {
    }
    
<<<<<<< HEAD
    public List<String> getPasswords(){
        return clientsManager.getAllPasswords();
    }
    
    public List<String> getUsernames(){
        return clientsManager.getAllUsernames();
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
    
    public String retraitDepot(Long compteId){
        return "ComptesRetraitDepot?id = " + compteId;
    }
    
    public String comptesOperation(Long compteId){
        return "ComptesOperation?id = " + compteId;
    }
    
    public String virement(Long compteId){
        return "CompteVirement?id = " + compteId;
    }
    
    public String cloturer(Long compteId){
        return "ComptesCloturer?id = " + compteId;
    }
    
=======
>>>>>>> 4c1b497b79b06ec2472bc9dbdb9c95d8d0a68b2a
}
