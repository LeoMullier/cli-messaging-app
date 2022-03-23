// Declaration du package
package utc;

import java.io.BufferedReader;
// Importation des bibliotheques
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
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
            int i = 0;
            String[] tab_pseudo = new String[20];
            Socket[] tab_socket_comm = new Socket[20];
        	devoir1_threads_serv[] tab_threads = new devoir1_threads_serv[20];
        	
        	
        	// Mise en place des sockets
            System.out.println("(!) Le serveur de la messagerie a correctement démarré et est prêt.");
            ServerSocket conn = new ServerSocket(10098);

        	
        	// Boucle infini de fonctionnement du serveur
        	while (true) {
        		
        		
	            
	            System.out.println("(!) En attente d'une première demande de connexion.");
	            tab_socket_comm[i] = conn.accept();
	            
	            
	            // Mise en place du thread pour ce client
    			tab_threads[i] = new devoir1_threads_serv(tab_socket_comm[i], tab_pseudo, i);
    			tab_threads[i].start();
    			i++;
        	}   
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            /*
	            //Mise en place des canaux de communication entrant et sortant
	            //OutputStream outToClient = serv_comm.getOutputStream();
	            //InputStream inFromClient = serv_comm.getInputStream();
	            //BufferedReader inFromClient = new BufferedReader(new InputStreamReader(serv_comm.getInputStream()));
		        //PrintStream outToClient = new PrintStream(serv_comm.getOutputStream());
	            DataInputStream inFromClient = new DataInputStream(serv_comm.getInputStream());
				DataOutputStream outToClient = new DataOutputStream(serv_comm.getOutputStream());
	
				
				// Exemple de réception de message
				
				System.out.println("Message A: " + dIn.readUTF());
				
	            
	            
	            //while(!chaineRecue.startsWith("END"))
	            while (compt <100)
	            {
	            	
	                System.out.println("(!) Lecture en cours, en attente de l'arrivée d'un paquet.");
	                chaineRecue = inFromClient.readUTF();
	                System.out.println("(!) Paquet réceptionné.");
	                System.out.println(">>> "+chaineRecue);
	
	                if(chaineRecue.startsWith("pseudo:"))
	                {
	                	
	                    System.out.println("(!) Nouvel utilisateur entrant.");
	                    outToClient.writeUTF("pseudo_valide");
	                    outToClient.writeInt(i);
	                    //double ecriture pose probleme: parfois rien n est recu chez le client, parfois le int est recu correctement, a creuser
	                    String pseudo = chaineRecue.substring(7, chaineRecue.length());
	                    System.out.println("----> pseudo :"+pseudo);
	                    tab_pseudo[i] = pseudo;
	                    
	                    
	                    // Mise en place des 2 threads
	        			tab_threads[i] = new devoir1_threads_serv(serv_comm, tab_pseudo, inFromClient, outToClient);
	        			outToClient.close();
	                    inFromClient.close();
	        			tab_threads[i].start();
	        			i++;
	                }
	                
	                else if(chaineRecue.startsWith("id_"))
	                {
	                
	                    System.out.println("(!) 1 nouveau message reçu.");
	                    outToClient.writeUTF("msg: " + chaineRecue);
	                }
	                
	                else
	                {
	                
	                    System.out.println("(!) Paquet incorrect et non-traité.");
	                }
	                
	                compt++;
	                
	                System.out.println("itteration "+compt);
	                System.out.println(" ");
	                //out.write(("suivant ?").getBytes());
	                //byteElement=new byte[20];
	                //byteElement=0.getBytes();
	                chaineRecue="a";
	            }
	            
	            System.out.println("Fin");
	            outToClient.close();
	            inFromClient.close();
	            serv_comm.close();
        	}*/
        	
        	
        	
        	
        	
        	
        	
        } 
        catch (IOException ex) 
        {
                Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
