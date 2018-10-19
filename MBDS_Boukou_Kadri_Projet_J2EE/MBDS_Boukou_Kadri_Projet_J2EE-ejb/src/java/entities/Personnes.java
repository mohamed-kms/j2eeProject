/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author grace
 */
@Entity
public class Personnes {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    @Column(nullable = false)
    protected String nom;
    protected String prenom;
    protected String adresse;
    
    // CONSTRUCTEURS
    public Personnes() {}
    
    public Personnes(String nom, String prenom, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }
    
    // GETTERS and SETTERS   
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
