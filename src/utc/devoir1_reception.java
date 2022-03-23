// Déclaration du package
package utc;

import java.io.DataInput;
// Importation des bibliothèques
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


public class devoir1_reception extends Thread{
	
	private Socket client;
	private String nom;
	private DataInputStream inFromServer;
	
	
	public devoir1_reception(Socket client, String nom, DataInputStream inFromServer) {
		this.client = client;
		this.nom = nom;
		this.inFromServer = inFromServer;
	}
	

	//@Override
	public void run() {
		
		try {
			
			// Initialisations
			String chaineRecue;
			//System.out.println("dans le thread reception");
			
			while(true) {
				
				// Lecture du contenu de InputStream
				chaineRecue = inFromServer.readUTF();
				
				// Affichage de l'élément reçu si on n'en est pas l'auteur
				//if(!(chaineRecue.startsWith(nom))) {
					
					System.out.println(chaineRecue);
					System.out.println(" ");
				//}
			}
		
		// Gestion de l'exception
		} catch(IOException ex) {
			Logger.getLogger(devoir1_reception.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
