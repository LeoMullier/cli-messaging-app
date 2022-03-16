package utc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author imineyou
 */
public class Serveur {
    
    
    public static void main(String[] args) {
        int nb_compte=0;
        try {
            ServerSocket server=new ServerSocket(7000);
            System.out.println("Serveur à l'écoute ......");
            Socket client=server.accept();
            System.out.println(" nouveau client ...");
                
                
            ObjectInputStream ins=new ObjectInputStream(client.getInputStream());
            System.out.println("les information du compte client : "+(Compte)ins.readObject());
      
            
            
            
            ObjectOutputStream outs=new ObjectOutputStream(client.getOutputStream());
            outs.writeUTF("votre numéro de compte est "+(++nb_compte));
            outs.flush();
           
                
                
                
                
                
              //  InputStream in=client.getInputStream();
              //   byte []b=new byte[1024];
              //  in.read(b);
              //  System.out.println("le client à dit: "+new String(b));
                
              //   DataInputStream ins=new DataInputStream(client.getInputStream());
              //   System.out.println("le client à dit: "+ins.readUTF());
           
                
               // OutputStream out=client.getOutputStream();
               // out.write("bonjour mon client".getBytes());
            // DataOutputStream outs=new DataOutputStream(client.getOutputStream());
            // outs.writeUTF("bonjour mon client .....");
           
        } catch (IOException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
}
