import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class Rfc865UdpServer {
	public static void main(String[] args) {
		//Open UDP socket at well-known port
		DatagramSocket socket = null;
		
		try{
			socket = new DatagramSocket(17);
		}catch (SocketException e){
			e.printStackTrace();
			System.exit(-1);
		}
		
		while(true){
			try{
				//listen for UDP request from client
				byte[] buf = new byte[512];
				DatagramPacket request = new DatagramPacket(buf, buf.length);
				socket.receive(request);
				
				String s = new String(buf);
				System.out.println(s);
				
				InetAddress IPAddress = request.getAddress();
				System.out.println(IPAddress);
				int port = request.getPort();
				
				//send UDP reply to client
				byte[] replyBuf = "Hello World!".getBytes("UTF-8");
				DatagramPacket reply = new DatagramPacket(replyBuf, replyBuf.length,IPAddress,port);
				socket.send(reply);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
