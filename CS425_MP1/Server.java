//package server;
//Note above lines when upload to VM
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;  
import java.io.PrintStream;  
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.ServerSocket;  
import java.net.Socket;  

public class Server {  

	
	private Socket client = null;
	private ServerSocket server = null;
	
	/*
	 * Info of server itself
	 */
	private String localIP;
	private int vmNum = -1, port = -1;
	private boolean serverRunning = true;
		
		
	public Server() throws IOException{
		try {
            this.localIP = InetAddress.getLocalHost().getHostAddress();
        } catch(UnknownHostException e) {
            e.printStackTrace();
        }
		BufferedReader br = null;
		FileReader fr = null;
		try {
            fr = new FileReader("Config.data");
            br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                String[] split = line.split(" ");
                if(localIP.equals(split[1])){
                	this.vmNum = Integer.parseInt(split[0]);
                	this.port = Integer.parseInt(split[2]);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	public void excute() throws IOException{
		if(this.port != -1 && this.vmNum != -1){
			this.server = new ServerSocket(port);
			while(serverRunning){
				client = server.accept();
				System.out.println("Connect Succeefully With " + client.getLocalAddress());  
				new Thread(new ServerThread(client,vmNum)).start();
			}
			server.close();
		}
	}
	
	public void setServerFlag(boolean f){
		this.serverRunning = f;
	}
	public boolean getServerFlag(){
		return this.serverRunning;
	}
	public static void main(String[] args) throws IOException{
		Server server = new Server();
		server.excute();
	}

} 
