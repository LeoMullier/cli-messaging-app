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


public class devoir1_envoi extends Thread{
	
	
	private Socket socClient;
	private int id;
	private DataOutputStream outToServer;
	
	
	public devoir1_envoi(Socket socClient, int id, DataOutputStream outToServer) {
		
		
		this.socClient = socClient;
		this.id = id;
		this.outToServer = outToServer;
	}
	
	
	//@Override
	public void run() {
		
		
		try {
			
			
			// Initialisations
			//DataOutputStream outToServer = new DataOutputStream(socClient.getOutputStream());
			//System.out.println("Thread envoi");
			
			
			while (true)
			{
				
				
				// Lecture du contenu tapé
				Scanner sc = new Scanner(System.in);
				String message = sc.next();
				
				
				// Envoi du message au serveur
				outToServer.writeUTF(("id_" + id + ":" + message));
			}
		
			
		// Gestion de l'exception
		} catch(IOException ex)
		{
			
			
			Logger.getLogger(devoir1_reception.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
