/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utc;

import java.io.Serializable;

/**
 *
 * @author imineyou
 */
public class Compte implements Serializable{  
    
    
    private String titulaire;
    private float solde;

    public Compte(String titulaire, int solde) {
        this.titulaire=titulaire;
        this.solde=solde;
    }
    public float getSolde() {
        return solde;
    }
    public String getTitulaire() {
        return titulaire;
    }
    public void setSolde(float solde) {
        this.solde = solde;
    }
    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }   
    public String toString(){
       return "le titulaire de ce compte est "+titulaire+", il a "+solde+" dans son compte";
    }    
}


