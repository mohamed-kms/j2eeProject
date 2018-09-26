/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author mohamed-kms
 */
@NamedQueries({
    @NamedQuery(name = "Operations.findAll", query = "SELECT d FROM Operations d")})
@Entity
public class Operations implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    private String operationName;
    

    @ManyToOne(fetch=FetchType.LAZY)
    private CompteBancaire compteBancaire;

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }
    
    
    public Operations() {
    }

    public Operations(String operationName, CompteBancaire compteBancaire) {
        this.operationName = operationName;
        this.compteBancaire = compteBancaire;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
    
    
    
    
}
