/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
=======
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
>>>>>>> 4c1b497b79b06ec2472bc9dbdb9c95d8d0a68b2a
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
<<<<<<< HEAD
 * @author mohamed-kms
 */

@NamedQueries({
    @NamedQuery(name = "Clients.findAll", query = "SELECT d FROM Clients d"),
    @NamedQuery(name = "Clients.findByClientId", query = "SELECT c FROM Clients c WHERE c.id = :id"),
    @NamedQuery(name = "Clients.findAllUsernames", query = "SELECT c.username FROM Clients c"),
    @NamedQuery(name = "Clients.findAllPasswords", query = "SELECT c.password FROM Clients c"),
    @NamedQuery(name = "Clients.findByUsername", query = "SELECT c FROM Clients c WHERE c.username = :username")})

@Entity
@DiscriminatorValue("CLIENTS")
public class Clients extends Personne implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private Map<String, String> clientsLoginDict = new HashMap<>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "CLIENT_ASSOC_COMPTE", joinColumns = @JoinColumn(name = "CLIENT_ID"), inverseJoinColumns = @JoinColumn(name = "COMPTE_ID"))
    private List<CompteBancaire> listeComptesBancaire;

    public Clients() {
    }

    public Clients(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
        this.username = this.nom + "CL" + this.id;
        this.password = this.prenom + "pswd" + this.id;
        this.clientsLoginDict.put(username, password);
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

    public Map<String, String> getClientsLoginDict() {
        return clientsLoginDict;
    }

    public void setClientsLoginDict(Map<String, String> clientsLoginDict) {
        this.clientsLoginDict = clientsLoginDict;
=======
 * @author grace
 */
@NamedQueries({
    @NamedQuery(name = "Clients.findAll", query = "SELECT d FROM Clients d")
})
@Entity
@DiscriminatorValue("CLIENTS")
public class Clients extends Personnes implements Serializable {
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "CLIENT_ASSOC_COMPTE", joinColumns = @JoinColumn(name = "CLIENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COMPTE_ID"))
    private List<CompteBancaire> listComptesBancaire;   // Un Client peut avoir 1..* Comptes
    
    public Clients() {}
    
    public Clients(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
>>>>>>> 4c1b497b79b06ec2472bc9dbdb9c95d8d0a68b2a
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "Clients{" + "Nom=" + getNom() + "Prenom" + getPrenom() + '}';
    }

    public List<CompteBancaire> getListeComptesBancaire() {
        return listeComptesBancaire;
    }

    public void setListeComptesBancaire(List<CompteBancaire> listeComptesBancaire) {
        this.listeComptesBancaire = listeComptesBancaire;
=======
        return "Client{" + "Nom : " + getNom() + " "
                + "Prenom : " + getPrenom() + " "
                + "Adresse : " + getAdresse() + "}";
>>>>>>> 4c1b497b79b06ec2472bc9dbdb9c95d8d0a68b2a
    }
    
    
    
}
