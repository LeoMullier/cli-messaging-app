// Déclaration du package
package utc;


// Importation des bibliothÃ¨ques
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class devoir1_threads_serv extends Thread{
	
	public Socket serv_comm;
	public DataOutputStream outToClient;
	public String[] tab_pseudo;
	public devoir1_threads_serv[] tab_threads;
	public int i;

	
	
	public devoir1_threads_serv(Socket serv_comm, DataOutputStream outToClient, String[] tab_pseudo, devoir1_threads_serv[] tab_threads, int i) {
		this.serv_comm = serv_comm;
		this.outToClient = outToClient;
		this.tab_pseudo = tab_pseudo;
		this.tab_threads =  tab_threads;
		this.i = i;
	}


	public void envoyer_aux_autres(String chaine) throws IOException {
	
		
		outToClient.writeUTF(chaine);
	}
	
	
	//@Override
	public void run() {
		
		try {
			
			
			// Initialisations
			boolean sortir = false;
			String chaineRecue = "a";
			DataInputStream inFromClient = new DataInputStream(serv_comm.getInputStream());
			//DataOutputStream outToClient = new DataOutputStream(serv_comm.getOutputStream());
            
            
            //while(!chaineRecue.startsWith("END"))
            while (sortir == false){
            	
            	
            	// Attente de l'arrivée d'un nouveau packet
                System.out.println("(!) Lecture en cours, en attente de l'arrivée d'un paquet.");
                chaineRecue = inFromClient.readUTF();
                System.out.println("(!) Paquet réceptionné.");
                System.out.println(">>> "+chaineRecue);
                
                
                // Si le packet est le pseudo d'un nouvel utilisateur
                if (chaineRecue.startsWith("pseudo:"))
                {
                	
                	
                    // Isolation du pseudo et stockage dans le tableau
                    System.out.println("(!) Nouvel utilisateur entrant.");
                    String pseudo = chaineRecue.substring(7, chaineRecue.length());
                    System.out.println("(!) Le pseudo du nouvel utilisateur est " + pseudo + ".");
                    
                    
                    // Validation du pseudo auprès du client
                    boolean deja_utilise = false;
                    for (int j = 0 ; j < i ; j++) {
                    	
                    	
                    	if (tab_pseudo[j] == pseudo) {
                    		
                    		
                    		outToClient.writeUTF("pseudo_deja_utilise");
                    		deja_utilise = true;
                    	}
                    }
                    
                    
                    if (deja_utilise == false) {
                    	
                    	
                    	tab_pseudo[i] = pseudo;
	                    outToClient.writeUTF("pseudo_valide");
	                    int construction_id = 1234 + i;
	                    outToClient.writeInt(construction_id);
	                    int k = 0;
	                    while (tab_threads[k] != null) {
                    		
                    		
                    		tab_threads[k].envoyer_aux_autres(pseudo + " :: a rejoint la conversation.");
                    		k++;
                    	}
                    }
                }
                
                
                // Si le packet est un message
                else if(chaineRecue.startsWith("id_"))
                {
                	
                	
                	// Isolation de l'identifiant et du message
                	String idS = chaineRecue.substring(3, 7);
                	int idI = Integer.parseInt(idS);
                	int recuperation_id = idI - 1234; 
                	//System.out.println("recup"+recuperation_id);
                	String origine = tab_pseudo[recuperation_id];
                	chaineRecue = chaineRecue.substring(8, chaineRecue.length());
                	
                	
                	// Controle des différentes informations
                	/*
                	System.out.println("idS:"+idS);
                	system.out.println("idI:"+idI);
                	System.out.println("origine:"+origine);
                	*/
                	
                	
                	// Traitement du message vers les clients et virification de l'ordre de fin
                    if (chaineRecue.startsWith("fin")){
                    	
                    	
                    	sortir = true;
                    	int k = 0;
                    	while (tab_threads[k] != null) {
                    		
                    		
                    		tab_threads[k].envoyer_aux_autres(origine + " :: a quitté la conversation.");
                    		k++;
                    	}
                    } 
                    
                    
                    else {
                    	
                    	
                    	System.out.println("(!) 1 nouveau message reçu.");
                    	//outToClient.writeUTF(origine + " :: " + chaineRecue);
                    	int k = 0;
                    	while (tab_threads[k] != null) {
                    		
                    		
                    		tab_threads[k].envoyer_aux_autres(origine + " :: " + chaineRecue);
                    		k++;
                    	}
                    }  
                }
                
                
                else
                {
                
                	
                    System.out.println("(!) Paquet incorrect et non-traité.");
                }
                
                
                System.out.println(" ");
                chaineRecue="vide";
            }
			
			
            // Fermeture de la communication
            outToClient.close();
            inFromClient.close();
            serv_comm.close();
            Thread.currentThread().interrupt();
			
			
		} 
		
		
		// Gestion de l'exception
		catch(IOException ex) {
			
			
			Logger.getLogger(devoir1_reception.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
