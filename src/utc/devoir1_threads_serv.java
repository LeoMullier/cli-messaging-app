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
	public String[] tab_pseudo;
	public int i;

	
	
	public devoir1_threads_serv(Socket serv_comm, String[] tab_pseudo, int i) {
		this.serv_comm = serv_comm;
		this.tab_pseudo = tab_pseudo;
		this.i = i;
	}
	
	
	//@Override
	public void run() {
		
		try {
			
			
			// Initialisations
			int compt = 0;
			String chaineRecue = "a";
			DataInputStream inFromClient = new DataInputStream(serv_comm.getInputStream());
			DataOutputStream outToClient = new DataOutputStream(serv_comm.getOutputStream());
            
            
            //while(!chaineRecue.startsWith("END"))
            while (compt <100)
            {
            	
            	
            	// Attente de l'arrivée d'un nouveau packet
                System.out.println("(!) Lecture en cours, en attente de l'arrivée d'un paquet.");
                chaineRecue = inFromClient.readUTF();
                System.out.println("(!) Paquet réceptionné.");
                System.out.println(">>> "+chaineRecue);
                
                
                // Si le packet est le pseudo d'un nouvel utilisateur
                if (chaineRecue.startsWith("pseudo:"))
                {
                	
                	
                	// Validation du pseudo auprès du client
                    System.out.println("(!) Nouvel utilisateur entrant.");
                    outToClient.writeUTF("pseudo_valide");
                    outToClient.writeInt(i);
                    
                    // Isolation du pseudo et stockage dans le tableau
                    String pseudo = chaineRecue.substring(7, chaineRecue.length());
                    System.out.println("(!) Le pseudo du nouvel utilisateur est " + pseudo + ".");
                    tab_pseudo[i] = pseudo;
                }
                
                
                // Si le packet est un message
                else if(chaineRecue.startsWith("id_"))
                {
                	
                	
                	// Isolation de l'identifiant et du message
                	String idS = chaineRecue.substring(3, 4);
                	int idI = Integer.parseInt(idS);
                	String origine = tab_pseudo[idI];
                	chaineRecue = chaineRecue.substring(5, chaineRecue.length());
                	
                	
                	// Controle des différentes informations
                	/*
                	System.out.println("idS:"+idS);
                	system.out.println("idI:"+idI);
                	System.out.println("origine:"+origine);
                	*/
                	
                	
                	// Traitement du message vers les clients
                    System.out.println("(!) 1 nouveau message reçu.");
                    outToClient.writeUTF(origine + " :: " + chaineRecue);
                }
                
                else
                {
                
                    System.out.println("(!) Paquet incorrect et non-traité.");
                }
                
                compt++;
                
                //System.out.println("titteration "+compt);
                System.out.println(" ");
                //out.write(("suivant ?").getBytes());
                //byteElement=new byte[20];
                //byteElement=0.getBytes();
                chaineRecue="a";
            }
			
			
			
			
			
			
			
			// Initialisations
			//DataOutputStream outToServer = new DataOutputStream(socClient.getOutputStream());
			System.out.println("Thread envoi2222222222222");
			
			String chaineRecueT = inFromClient.readUTF();
            System.out.println("t(!) Paquet réceptionné.");
            System.out.println("t>>> "+chaineRecueT);
            
            
			if(chaineRecueT.startsWith("id_"))
            {
            
                System.out.println("t(!) 1 nouveau message reçu.");
                outToClient.writeUTF("tmsg: " + chaineRecueT);
            }
		
			
		// Gestion de l'exception
		} catch(IOException ex)
		{
			
			Logger.getLogger(devoir1_reception.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
