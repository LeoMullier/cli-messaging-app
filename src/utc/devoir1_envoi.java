// Déclaration du package
package utc;

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


public class devoir1_envoi extends Thread{
	
	private Socket client;
	private int id;
	
	
	public devoir1_envoi(Socket client, int id) {
		this.client = client;
		this.id = id;
	}
	
	
	//@Override
	public void run() {
		
		try {
			
			// Initialisations
			OutputStream outToServer = client.getOutputStream();
			
			while(true) {
				
				// Lecture du contenu tapé
				Scanner sc = new Scanner(System.in);
				String message = sc.next();
				
				// Envoi du message au serveur
				outToServer.write((id+":"+message).getBytes());
			}
		
		// Gestion de l'exception
		} catch(IOException ex) {
			Logger.getLogger(devoir1_reception.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
