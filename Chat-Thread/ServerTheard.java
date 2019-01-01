import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

public class ServerTheard extends Thread {
	private Socket scoket;
	private int  id;
	private String name;
	private boolean exit;
	private String massege;
	private Users list;
	private DataInputStream in;
	private DataOutputStream  ou;

	public ServerTheard ( Socket s,Users list,int  id) {
		this.scoket=s;
		this.id=id;
		this.list=list;
	}
	public ServerTheard () {

	}
	public void run(){
		try {
			in =new DataInputStream(scoket.getInputStream());
			ou= new DataOutputStream(scoket.getOutputStream());
			exit=false;
			name=in.readUTF();
			sendMassege(name+"connected");	
		}catch(IOException e) {
			e.printStackTrace();
		}
		while(!exit) {
			try {
				massege=in.readUTF();
				if(massege.equalsIgnoreCase("exit")) {
					exit=true;
					sendMassege("-"+name+"disconnected");
					removeClient();
					}
				else {
					sendMassege("-"+name+"write"+massege);
				}
			}catch(IOException e) {
				e.printStackTrace();	
			}
		}
	}
	private void sendMassege(String massege) {
		try {
			Iterator it=list.getUsers().entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry pair=(Map.Entry)it.next();
				ou=new DataOutputStream(((Socket) pair.getValue()).getOutputStream());
				ou.writeUTF( massege);
			}

		}catch(IOException e) {
			e.printStackTrace();
		}

	}
	private void removeClient() {
		list.removeUser(id);
	}
}
