package utc;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSimple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket soc= new Socket("localhost",10030);
			System.out.println("ok");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
