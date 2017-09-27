//package mp1_grep;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;  
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.*;  

public class Client {  
    /*
     * Member Variables of Client
     */
    private ArrayList<ServerInfo> vmList = new ArrayList<>();
    private ServerInfo info;
    private String localIP;
    private int vmNum, port;
    /*
     * Constructor
     */
    public Client() {
        /*
         * Get Local IP address
         */
        try 
        {
            this.localIP = InetAddress.getLocalHost().getHostAddress();
        } 
        catch(UnknownHostException e) {
            e.printStackTrace();
        }
        /*
         * Read servers info from Config.data
         */
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader("Config.data");
            br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                String[] split = line.split(" ");
                this.vmNum = Integer.parseInt(split[0]);
                ServerInfo si = new ServerInfo(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]));
                vmList.add(si);
                //System.out.println(vmList.size() + " servers in config");
                line = br.readLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error! Didn't find IP address in the Config file.");
        }
    }
    
    public void excute() throws IOException{
        System.out.print("Please input your query here: ");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String input = buffer.readLine();
        try{
        	buildConnection(input);
        } catch (Exception e){
        	e.printStackTrace();
        }
    }
    
    /*
     *Initial 10 Client Threads Talking to 10 different Server
     */
    public void buildConnection(String pattern) throws IOException {
        for(int i = 0; i < vmList.size(); i++) { 
            ServerInfo currVM = vmList.get(i);
            //System.out.println(currVM.getIp() + "; " + currVM.getPort());
            try{
            	Socket client_thread = new Socket(currVM.getIp(), currVM.getPort()); 
            	client_thread.setSoTimeout(10000);
            	System.out.println("Connection with VM" + currVM.getNum() + " is build!");
            	new Thread(new ClientThread(client_thread, pattern, currVM.getNum())).start(); 
            } catch(ConnectException e){
            	System.out.println("There is no one listen at VM " + currVM.getNum());
            	//.getStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException{
    	Client client = new Client();
    	client.excute();
    }
    
}
