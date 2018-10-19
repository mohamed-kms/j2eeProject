/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

    
    private int solde;    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @OneToMany(mappedBy = "compteBancaire", cascade = CascadeType.ALL)
    private List<Operations> operations;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Clients> proprietaires;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Conseillers conseiller;

    public CompteBancaire(Set<Clients> proprietaires, int solde, Conseillers conseiller) {
        this.solde = solde;
        this.proprietaires = proprietaires;
        this.conseiller = conseiller;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public CompteBancaire() {
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

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public Set<Clients> getProprietaires() {
        return proprietaires;
    }
    
    public String printProps(){
        String props = "";
        for(Iterator<Clients> it = proprietaires.iterator(); it.hasNext();){
            Clients c = it.next();
            props += c.getNom() + " " + c.getPrenom() + "\n\n\n\n\n\n";
        }
        String msg = "Ce compte n'existe pas";
        FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage("Compte", facesMsg);
        return props;
    }

    public void setProprietaires(Set<Clients> proprietaires) {
        this.proprietaires = proprietaires;
    }

    public Conseillers getConseiller() {
        return conseiller;
    }

    public void setConseiller(Conseillers conseiller) {
        this.conseiller = conseiller;
    }
    
    

    @Override
    public String toString() {
        return "CompteBancaire{" + "id=" + id + ", proprietaires=" + proprietaires + ", solde=" + solde + ", conseiller=" + conseiller + '}';
    }
    
    
    
}
