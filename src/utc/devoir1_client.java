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
import java.nio.ByteBuffer;


public class devoir1_client {
	
	public static void main(String[] args) {
	
		try {
			
			// Initialisations
			Socket client = new Socket ("172.22.19.3", 10090);
			OutputStream outToServer = client.getOutputStream();
			InputStream inFromServer = client.getInputStream();
			String chaineRecue = "pseudo_inconnu";
			String nom = "vide";
			//out.write("Bonjour de la part du client".getBytes());
			
			while(chaineRecue != "pseudo_valide") {
				
				// Envoi du pseudo choisi par l'utilisateur
				System.out.println("Saississez le pseudo que vous souhaitez utiliser pour cette discussion : ");
				Scanner sc = new Scanner(System.in);
				nom = sc.next();		
				outToServer.write(("pseudo:"+nom).getBytes());
				
				// Récéption de la réponse du serveur autorisant ou non à utiliser ce pseudo
				byte byteElement[]=new byte[20];
				inFromServer.read(byteElement);
				chaineRecue = new String(byteElement);
			}
			
			// Message de bienvenue à l'utilisateur
			System.out.println("Bienvenue dans la discussion "+nom+ "!");
			
			// Récupération de l'identifiant unique généré par le serveur
			byte byteElement[]=new byte[20];
			int id = inFromServer.read();
			
			// Fermeture des canaux pour pseudo
			inFromServer.close();
			outToServer.close();
			
			// Mise en place des 2 threads
			devoir1_reception threadReception = new devoir1_reception(client, nom);
			devoir1_envoi threadEnvoi = new devoir1_envoi(client, id);
			threadReception.run();
			threadEnvoi.run();
			
			/*while(compteur!=nbMax){
				in.read(b);
				System.out.println("le serveur a dit: "+new String(b));
				System.out.println("Le client envoie le message:message"+compteur);
				out.write(("message"+compteur).getBytes());
				compteur++;
				b=new byte[20];
			}
			
			//out.write(("END").getBytes());
			
			devoir1_reception msg_reception = new devoir1_reception(client);
			msg_reception.run();
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException ex) {
				Logger.getLogger(devoir1_client.class.getName()).log(Level.SEVERE, null, ex);
			}
			*/
			
			// Fermeture de la socket
			client.close();
		
		// Gestion de l'exception
		} catch (IOException ex) {
			Logger.getLogger(devoir1_client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}