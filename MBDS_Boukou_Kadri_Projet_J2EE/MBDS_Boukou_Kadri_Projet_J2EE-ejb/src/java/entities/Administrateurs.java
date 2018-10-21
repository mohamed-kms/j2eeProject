/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author mohamed-kms
 */

@NamedQueries({
    @NamedQuery(name = "Administrateurs.findAll", query = "SELECT d FROM Administrateurs d"),
    @NamedQuery(name = "Administrateurs.findByAdministrateurId", query = "SELECT c FROM Administrateurs c WHERE c.id = :id"),
    @NamedQuery(name = "Administrateurs.findAllUsernames", query = "SELECT c.username FROM Administrateurs c"),
    @NamedQuery(name = "Administrateurs.findAllPasswords", query = "SELECT c.password FROM Administrateurs c"),
    @NamedQuery(name = "Administrateurs.findByUsername", query = "SELECT c FROM Administrateurs c WHERE c.username = :username")})

@Entity
@DiscriminatorValue("ADMINISTRATEUR")
public class Administrateurs extends Personnes implements Serializable {

    private String username;
    private String password;
    
    public Administrateurs() {
    }

    public Administrateurs(Long id, String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
    }

    
}
