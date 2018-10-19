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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author mohamed-kms
 */

@NamedQueries({
    @NamedQuery(name = "Conseillers.findAll", query = "SELECT d FROM Conseillers d")})
@Entity
@DiscriminatorValue("CONSEILLERS")
public class Conseillers extends Personne implements Serializable {

    @OneToMany()
    private List<CompteBancaire> comptesGérés;
    
    public Conseillers() {
    }

    public Conseillers(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
    }

    
    
}
