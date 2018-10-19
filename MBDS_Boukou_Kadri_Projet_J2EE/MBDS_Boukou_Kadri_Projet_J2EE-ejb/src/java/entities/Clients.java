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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
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
    }

    @Override
    public String toString() {
        return "Client{" + "Nom : " + getNom() + " "
                + "Prenom : " + getPrenom() + " "
                + "Adresse : " + getAdresse() + "}";
    }
    
    
    
}
