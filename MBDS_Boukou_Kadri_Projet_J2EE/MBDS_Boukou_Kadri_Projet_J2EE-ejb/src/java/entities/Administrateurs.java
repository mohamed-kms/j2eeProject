/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author grace
 */
@Entity
@DiscriminatorValue("ADMINISTRATEUR")
public class Administrateurs extends Personnes implements Serializable {
    
    @OneToMany()
    private List<Conseillers> listConseillers;  // Un administrateurs a des Conseillers
    
    public Administrateurs() {}
    
    public Administrateurs(Long id, String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
    }
    
    @Override
    public String toString() {
        return "Client{" + "Nom : " + getNom() + " "
                + "Prenom : " + getPrenom() + " "
                + "Adresse : " + getAdresse() + "}";
    }

}
