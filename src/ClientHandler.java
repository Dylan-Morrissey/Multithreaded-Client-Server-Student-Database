import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientHandler extends Thread {
	
	private Socket socket;
	private InetAddress address;
	private DataInputStream inputFromClient;
	private DataOutputStream outputToClient;
	private JDBC jdbc = new JDBC();
	
	public ClientHandler (Socket s ) throws IOException {	
		
		this.socket = s;
		this.address = socket.getInetAddress();
        this.inputFromClient = new DataInputStream(socket.getInputStream());
        this.outputToClient = new DataOutputStream(socket.getOutputStream());   
        
	
	}
	
	public void run() {
		jdbc.run();
		
		while (true) {
		    int uid;
			try {
				uid = inputFromClient.readInt();
			    if (jdbc.findUserById(uid) != "") {
			    	outputToClient.writeBoolean(true);
				  	outputToClient.writeUTF(jdbc.findUserById(uid));
			    } else {
			    	outputToClient.writeBoolean(false);
				  	outputToClient.writeUTF(jdbc.findUserById(uid));
			    }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		    
	}
}

