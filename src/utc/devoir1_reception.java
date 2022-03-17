// D�claration du package
package utc;

// Importation des biblioth�ques
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
	
	
	public devoir1_reception(Socket client, String nom) {
		this.client = client;
		this.nom = nom;
	}
	

	//@Override
	public void run() {
		
		try {
			
			// Initialisations
			byte byteElement[] = new byte[20];
			String chaineRecue;
			InputStream inputFromServer=client.getInputStream();
			
			while(true) {
				
				// Lecture du contenu de InputStream
				inputFromServer.read(byteElement);
				chaineRecue = new String(byteElement);
				
				// Affichage de l'�l�ment re�u si on n'en est pas l'auteur
				if(!(chaineRecue.startsWith(nom))) {
					
					System.out.println(chaineRecue);
				}
				
				byteElement=new byte[20];
			}
		
		// Gestion de l'exception
		} catch(IOException ex) {
			Logger.getLogger(devoir1_reception.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
