/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.CompteBancaire;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.GestionDeCompteBancaire;

/**
 *
 * @author mohamed-kms
 */
@Named(value = "compteBancaireMBean")
@ViewScoped
public class CompteBancaireMBean implements Serializable{

    @EJB
    private GestionDeCompteBancaire gestionDeCompteBancaire;
    
    public List<CompteBancaire>getComptes(){
        return gestionDeCompteBancaire.getAllComptes();
    }
    
    public String showDetails(int compteId){
        return "CompteBancaireDetails?idCompteBancaire = " + compteId;
    }

    /**
     * Creates a new instance of CompteBancaireMBean
     */
    public CompteBancaireMBean() {
    }
    
}
