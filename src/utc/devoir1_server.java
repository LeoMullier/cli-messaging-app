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
	            DataOutputStream outToClient = new DataOutputStream(tab_socket_comm[i].getOutputStream());
	            
	            
	            // Mise en place du thread pour ce client
    			tab_threads[i] = new devoir1_threads_serv(tab_socket_comm[i], outToClient, tab_pseudo, tab_threads, i);
    			tab_threads[i].start();
    			i++;
        	} 
        } 
        
        
        // Gestion des exceptions
        catch (IOException ex){
                
        	
        	Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
