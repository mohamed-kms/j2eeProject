/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author grace
 */
@Entity
public class Conseillers extends Personnes implements Serializable {

    public Conseillers() {}
    
    public Conseillers(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
    }
    
    @Override
    public String toString() {
        return "Conseiller{" + "Nom : " + getNom() + " "
                + "Prenom : " + getPrenom() + " "
                + "Adresse : " + getAdresse() + "}";
    }
}
