import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	private static  ServerSocket ss;
	private static boolean  salir;
	private static int cont;
	private static Users list;
	
	public static void main(String args[])
	{ 
		try {
			cont=0;
			list=new Users();
			ss=new ServerSocket(7777);
			System.out.println("server");
			while(!salir) {
				Socket s=ss.accept();
				list.addUser(cont,s);
				ServerTheard st=new ServerTheard (s,list,cont);
				st.start();
				cont++;
				
			}
			
		}catch(IOException e) {
			System.out.println("error in server");
		}
	}
	
}