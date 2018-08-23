import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication {
	
	private ServerSocket ss;
	private Socket s;
	
	public ServerApplication() {
		try {
			while(true) {
				ss=new ServerSocket(6666);
				s=ss.accept();//establishes connection
				DataInputStream dis=new DataInputStream(s.getInputStream());
				String  str=(String)dis.readUTF();
				
				ss.close();
			}
		}catch(Exception e){System.out.println(e);}
		System.out.println("Full message recieved...");
	}
	
	public static void main(String [] args) {
		new ServerApplication();
	}
}
