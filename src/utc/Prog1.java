/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utc;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author imineyou
 */
public class Prog1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
        // TODO code application logic here
        InetAddress adress=InetAddress.getByName("www.facebook.com");
        System.out.println(adress.getHostAddress());
        
        System.out.println("bonjour tous le monde .....");
        
        Compte p=new Compte("jean mark", 50);
        
        CompteBnp cp=new CompteBnp("jean mark", 50, "courant", 100);
        
        System.out.println(cp);
        
        
    }
    
}
