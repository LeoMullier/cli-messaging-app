// Declaration du package
package utc;

// Importation des bibliotheques
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.ByteBuffer;


public class devoir1_server 
{

    public static void main(String[] args) 
    {
        try 
        {
            // Initialisations
            System.out.println("aaa...");
            ServerSocket conn = new ServerSocket(10090);            
            Socket serv_comm = conn.accept();
            OutputStream outToClient = serv_comm.getOutputStream();
            InputStream inFromClient = serv_comm.getInputStream();


            byte byteElement[]=new byte[20];
            int compt=0;
            String chaineRecue="a";
            System.out.println("bbb...");
            //while(!chaineRecue.startsWith("END"))
            while (compt <100)
            {
                System.out.println("lecture...");
                inFromClient.read(byteElement);
                chaineRecue=new String(byteElement);
                System.out.println(chaineRecue);

                if(chaineRecue.startsWith("pseudo:"))
                {
                    System.out.println("pseudo:");
                    outToClient.write(("pseudo_valide").getBytes());
                    int id_tmp=22;
                    outToClient.write(id_tmp);
                    //double ecriture pose probleme: parfois rien n est recu chez le client, parfois le int est recu correctement, a creuser
                }
                else if(chaineRecue.startsWith("id_"))
                {
                
                    System.out.println("id_");
                }
                
                else
                {
                
                    System.out.println("Choix incorrect");
                }
                compt++;
                
                System.out.println("itteration "+compt);
                //out.write(("suivant ?").getBytes());
                byteElement=new byte[20];
                //byteElement=0.getBytes();
                chaineRecue="a";
            }
            
            System.out.println("Fin");
            outToClient.close();
            inFromClient.close();
            serv_comm.close();
        } 
        catch (IOException ex) 
        {
                Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
