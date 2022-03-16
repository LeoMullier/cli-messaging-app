/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utc;

/**
 *
 * @author imineyou
 */
public class CompteBnp extends Compte{   
    private String typeCompte;
    private float decouverte;
    
    public CompteBnp(String titulaire, int solde,String typeCompte,float decouverte) {
        super(titulaire, solde);
        this.typeCompte=typeCompte;
        this.decouverte=decouverte;
    }

    public float getDecouverte() {
        return decouverte;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    @Override
    public String toString() {
        return "ce compte est un compte "+ typeCompte+", "+ super.toString()+", la est découverte de "+decouverte +" euro"; 
    }    
}
