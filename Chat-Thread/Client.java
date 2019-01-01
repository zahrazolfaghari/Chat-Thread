import java.io.*;
import java.net.*;

public class Client {
	private static  Socket scoket;
	private static DataOutputStream  ou;
	private static BufferedReader in;
	private static String name,message;
	private static boolean end;
	private static ClientThread c1;
	
	public static void main(String args[])
	{ 
		initialize();
		try {
			System.out.println("Enter your name:");
			name=in.readLine();
			c1=new ClientThread(scoket,name,end);
			c1.start();
			ou.writeUTF(name);
		}catch(IOException e) {
			e.printStackTrace();
		}
		while(!end) {
			try {
				message=in.readLine();
				ou.writeUTF(message);
				if(message.equalsIgnoreCase("exit")) {
					end=true;
				}
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println("error loop");
			}
		}
	}
	private static void initialize()
	{
		try {
			scoket=new Socket("127.0.0.1",7778);
			ou=new  DataOutputStream(scoket.getOutputStream());
			in=new  BufferedReader(new InputStreamReader(System.in));
			end=false;
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
}