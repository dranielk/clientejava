package daniel.mtz.cliente.java;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class clientejava {
    public static void main(String[] args){
        Socket socket;
    	final BufferedReader in;
    	final PrintWriter out;
    	final PrintStream salida;
    	final Scanner sc = new Scanner(System.in);
    	
    	try {
    		
    		socket = new Socket("192.168.1.78",7070);
    		out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    		
    		Thread recibir;
                recibir = new Thread(new Runnable() {
                String mensaje;
                String thisLine = null;
                @Override
                public void run() {
                    while (true) {
    					try {
    						mensaje = in.readLine();
    						System.out.println("Server: " + mensaje);
    					}catch(IOException e) {
    						e.printStackTrace();
    					}
    					
    				}
                }
            });
    		recibir.start();
    		
    		Thread enviar;
            enviar = new Thread(new Runnable() {
    			String msg;
    			@Override
    			public void run() {
    				while(true) {
    					msg = sc.nextLine();
    					out.print(msg);
    					out.flush();
    					}
    			}
    		});
    		enviar.start();
                
    	}catch(Exception e) {
    		
    	}
    
    }
       
}
