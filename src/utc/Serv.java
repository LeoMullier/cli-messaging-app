/**
 * 
 */
package utc;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lounis
 *
 */
public class Serv {

	public static void main(String[] args) {


		try{
			ServerSocket sc=new ServerSocket(10030);
			Socket soc=sc.accept();
		}catch(Exception e) {

		}
	}
}
