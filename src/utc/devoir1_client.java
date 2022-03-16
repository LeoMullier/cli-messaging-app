package utc;

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

public class devoir1_client {
	public static void main(String[] args) {
		
	//int compteur=0;
	//byte b[]=new byte[20];
	
	try {
		Socket client = new Socket ("172.22.19.3", 10090);
		OutputStream out = client.getOutputStream();
		InputStream in = client.getInputStream();
		
		out.write("Bonjour de la part du client".getBytes());
		
		System.out.println("Saississez le nom que vous souhaitez utiliser pour cette discussion :");
		Scanner sc = new Scanner(System.in);
		String nom = sc.next();		
		
		out.write(("nom: "+nom).getBytes());
		
		while(compteur!=nbMax){
			in.read(b);
			System.out.println("le serveur a dit: "+new String(b));
			System.out.println("Le client envoie le message:message"+compteur);
			out.write(("message"+compteur).getBytes());
			compteur++;
			b=new byte[20];
		}
		
		//out.write(("END").getBytes());
		
//		devoir1_reception msg_reception = new devoir1_reception(client);
//		msg_reception.run();
//		
//		try {
//			Thread.sleep(20);
//		} catch (InterruptedException ex) {
//			Logger.getLogger(devoir1_client.class.getName()).log(Level.SEVERE, null, ex);
//		}
		in.close();
		out.close();
		client.close();
		
	} catch (IOException ex) {
		Logger.getLogger(devoir1_client.class.getName()).log(Level.SEVERE, null, ex);
	}
	}
}