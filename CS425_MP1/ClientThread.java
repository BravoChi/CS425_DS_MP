//package mp1_grep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  
import java.io.PrintStream;  
import java.net.Socket; 

public class ClientThread implements Runnable {
	private Socket client = null;
	private BufferedReader opSet = null;
	private String operation = null;
	//private ServerInfo targetServer;
	public boolean flag = true;
	private int vmNum;
	private String grepResult;
	public ClientThread(Socket client, String br, int vmNum){
		this.client = client;
		this.operation = br;
		this.vmNum = vmNum;
		//this.operation = op;
		//this.targetServer = info;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread is running");
		try {
			//System.out.println(client.equals(null));
			PrintStream out = new PrintStream(client.getOutputStream());
			BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
			long startTime, endTime;
			//while(flag){

				//operation = opSet.readLine();
				System.out.println("Operation: " + operation );
				startTime = System.currentTimeMillis();
				out.println(operation);
				if("bye".equals(operation)){
					flag = false;
				} else {
					try{
						grepResult = buf.readLine();
					} catch(Exception e){
						System.out.println("The vm" + vmNum + " is time out!");
					}
					if(grepResult != null){
						endTime = System.currentTimeMillis();
						//More operations can be added here
						System.out.println("Recive:" + grepResult);
						System.out.println("Used " + (endTime - startTime) + " ms");
					}
				}
			//}
			out.close();
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(client != null)
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
