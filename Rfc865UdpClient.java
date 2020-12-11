import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/*
Name: Park Kunyoung
Group: SSP3
IP Address: 172.21.150.230
*/

public class Rfc865UdpClient {
	public static void main(String[] args) {
		//Open UDP Socket
		DatagramSocket socket = null;
		try{
			socket = new DatagramSocket();
			InetAddress IpAddress = InetAddress.getByName("172.21.150.53");
			System.out.println(IpAddress);
			socket.connect(IpAddress, 17);
			
		}catch(SocketException e){
			e.printStackTrace();
			System.exit(-1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		try {
			//send UDP request to server
			byte[] buf = "Park Kunyoung, SSP3, 172.21.150.230".getBytes("UTF-8");
			DatagramPacket request = new DatagramPacket(buf, buf.length);       
			socket.send(request);
			
			//receive UDP reply from server
			byte[] replyBuf = new byte[512];
			DatagramPacket reply = new DatagramPacket(replyBuf, replyBuf.length);
			socket.receive(reply);
			
			String quote = new String(replyBuf);
			System.out.println(quote);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			socket.close();
		}
	}
}
