/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author imineyou
 */
public class Client {
    
    public static void main(String[] args) {
        
        try {
            Socket client=new Socket("localhost", 7000);
            System.out.println("connecté ....");
            
            Compte cpt=new Compte("lili lili", 300);
            
            ObjectOutputStream outs=new ObjectOutputStream(client.getOutputStream());
            outs.writeObject(cpt);
          
            
            
            ObjectInputStream ins=new ObjectInputStream(client.getInputStream());
            System.out.println("le serveur à dit:"+ins.readUTF());
            
            
            
         //   OutputStream out=client.getOutputStream();
         //   out.write("bonjour serveur".getBytes());
           
           // DataOutputStream outs=new DataOutputStream(client.getOutputStream());
           // outs.writeUTF("bonjour serveur .....");
            
            
            
         //   InputStream in=client.getInputStream();
         //   byte []b=new byte[1024];
         //   in.read(b);
         //   System.out.println("le Serveur à dit: "+new String(b));
            
            
          //  DataInputStream ins=new DataInputStream(client.getInputStream());
          //  System.out.println("le serveur à dit: "+ins.readUTF());
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
