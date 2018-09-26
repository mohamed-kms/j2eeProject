/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
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
 * @author mohamed-kms
 */
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT d FROM CompteBancaire d")})
@Entity
public class CompteBancaire implements Serializable {

    
    private String nom;
    private int solde;    

    @OneToMany(mappedBy = "compteBancaire")
    @JoinTable
  (
      name="COMPTEBANCAIRE_OPERATION",
      joinColumns={ @JoinColumn(name="COMPT_ID", referencedColumnName="ID") },
      inverseJoinColumns={ @JoinColumn(name="OP_ID", referencedColumnName="ID", unique=true) }
  )
    private List<Operations> operations;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public CompteBancaire() {
    }

    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
    }
    
    public void deposer(int montant){
        solde += montant;
    }
    
    public int retirer(int montant){
        if(montant < solde){
            solde -= montant;
            return montant;
        }else{
            return 0;
        }
    }

    public List<Operations> getOperations() {
        return operations;
    }

    public void setOperations(List<Operations> operations) {
        this.operations = operations;
    }

    

    
    public String getNom() {
        return nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CompteBancaire{" + "nom=" + nom + ", solde=" + solde + ", operations=" + operations + ", id=" + id + '}';
    }

    
    
    
    
}
