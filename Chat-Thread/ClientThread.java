import java.io.*;
import java.net.Socket;
public class ClientThread extends Thread {
	private Socket scoket;
	private DataInputStream in;
	private String name,message;
	private boolean end;
	
	public ClientThread(Socket scoket,String name,boolean end ) {
		this.scoket=scoket;
		this.name=name;
		this.end=end;
	}
	
	public ClientThread() {
	}
	public void run() {
		try {
			end=false;
			in=new DataInputStream(scoket.getInputStream());
			while(!end) {
				message=in.readUTF();
				System.out.println(message);
				if(message.equalsIgnoreCase("- "+name+"disconected")) {
					end=true;
				}
			}
			scoket.close();
		}catch(IOException e) {
			e.printStackTrace();
			end=true;	
		}
	}

}
