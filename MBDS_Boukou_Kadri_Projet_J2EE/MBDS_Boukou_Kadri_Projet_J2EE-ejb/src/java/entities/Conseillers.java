/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author grace
 */
@NamedQueries({
    @NamedQuery(name = "Conseillers.findAll", query = "SELECT d FROM Conseillers d"),
    @NamedQuery(name = "Conseillers.findByConseillerId", query = "SELECT c FROM Conseillers c WHERE c.id = :id"),
    @NamedQuery(name = "Conseillers.findAllUsernames", query = "SELECT c.username FROM Conseillers c"),
    @NamedQuery(name = "Conseillers.findAllPasswords", query = "SELECT c.password FROM Conseillers c"),
    @NamedQuery(name = "Conseillers.findByUsername", query = "SELECT c FROM Conseillers c WHERE c.username = :username")})

@Entity
@DiscriminatorValue("CONSEILLERS")
public class Conseillers extends Personnes implements Serializable {
 
    private String username;
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<CompteBancaire> comptesGeres;

    public Conseillers() {}
    
    public Conseillers(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
        this.username = nom + "CONS";
        this.password = prenom + "cpswd";
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

    public List<CompteBancaire> getComptesGeres() {
        return comptesGeres;
    }

    public void setComptesGeres(List<CompteBancaire> comptesGeres) {
        this.comptesGeres = comptesGeres;
    }
    
    
    
   
    @Override
    public String toString() {
        return "Conseiller{" + "Nom : " + getNom() + " "
                + "Prenom : " + getPrenom() + " "
                + "Adresse : " + getAdresse() + "}";
    }
}
