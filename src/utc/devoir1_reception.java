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

public class devoir1_reception extends devoir1_client{
	
	private Socket client;
	
	public devoir1_reception(Socket client) {
		this.client=client;
	}
	
	//@Override
	public void run() {
		try {
			InputStream ins=client.getInputStream();
			while(true) {
				// Lecture du contenu de InputStream
			}
		} catch(IOException ex) {
			Logger.getLogger(devoir1_reception.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
