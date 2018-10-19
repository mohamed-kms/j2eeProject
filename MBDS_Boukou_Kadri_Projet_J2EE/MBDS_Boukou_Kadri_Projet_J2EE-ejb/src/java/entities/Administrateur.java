/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mohamed-kms
 */
@Entity
@DiscriminatorValue("ADMINISTRATEUR")
public class Administrateur extends Personne implements Serializable {

    public Administrateur() {
    }

    public Administrateur(Long id, String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
    }

    
}
