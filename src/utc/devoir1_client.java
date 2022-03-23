// DÃ©claration du package
package utc;


// Importation des bibliothÃ¨ques
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.ByteBuffer;


public class devoir1_client {
	
	public static void main(String[] args) {
	
		try {
			
			
			// Mise en place du socket
			//Socket client = new Socket ("172.26.226.240", 10090);
			Socket socClient = new Socket ("localhost", 10098);
			
			
			// Mise en place des canaux de communication entrant et sortant
			//OutputStream outToServer = client.getOutputStream();
			//InputStream inFromServer = client.getInputStream();
			//BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        //PrintStream outToServer = new PrintStream(client.getOutputStream());
			DataOutputStream outToServer = new DataOutputStream(socClient.getOutputStream());
			DataInputStream inFromServer = new DataInputStream(socClient.getInputStream());
			
			
			// Initialisations
			String chaineRecue = "chaineRecue_vide";
			String nom = "nom_vide";
			
			
			// Exemple d'envoi de message
			/*
			dOut.writeByte(1);
			dOut.writeUTF("This is the first type of message.");
			dOut.flush(); // Send off the data
			*/
			
			
			// Déclaration du pseudo pour créer un nouvel utilisateur
			while(!(chaineRecue.startsWith("pseudo_valide")))
			{
				
				// Saisie du pseudo choisi par l'utilisateur
				System.out.println("Saississez le pseudo que vous souhaitez utiliser pour cette discussion : ");
				Scanner sc = new Scanner(System.in);
				nom = sc.next();
				
				
				// Envoi du pseudo au server
				outToServer.writeUTF("pseudo:" + nom);
				outToServer.flush();
				
				
				// Réception de la validation ou non du pseudo de la part du server
				chaineRecue = inFromServer.readUTF();
				//System.out.println(">>> " + chaineRecue);
			}
			
			
			// Récupération de l'identifiant unique généré par le server
			int id = inFromServer.readInt();
			//System.out.println(">>> " + id);
			
			
			// Message de bienvenue à l'utilisateur
			System.out.println("Bienvenue dans la discussion " + nom + " !");
			System.out.println(" ");
			
			
			// Fermeture des canaux pour pseudo
			//inFromServer.close();
			//outToServer.close();
			
			
			// Mise en place des 2 threads
			devoir1_reception threadReception = new devoir1_reception(socClient, nom, inFromServer);
			devoir1_envoi threadEnvoi = new devoir1_envoi(socClient, id, outToServer);
			threadEnvoi.start();
			threadReception.start();
			
			
			
			// Fermeture de la socket
			//System.out.println("fffffffff");
			//Scanner sc = new Scanner(System.in);
			//nom = sc.next();
			//socClient.close();
		
		// Gestion de l'exception
		} catch (IOException ex) {
			Logger.getLogger(devoir1_client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
