//package server;
//import grep.SysGrep;
//Note above lines when upload to VM

import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import java.io.PrintStream;  
import java.net.Socket;

/** 
 * Multi-Thread Class for Server End
 */  
public class ServerThread implements Runnable {  

    private Socket client = null;  
    private int vmNum = -1;
    
    public ServerThread(Socket client, int vmNum){  
        this.client = client;  
        this.vmNum = vmNum;
    }  

   // @Override  
    public void run() {  
        try{  
        	//Get outputStream of Socket, use it to send data to Client
            PrintStream sender = new PrintStream(client.getOutputStream());  
            //Get inputStream of Socket, use it to receive data from Client 
            BufferedReader receiver = new BufferedReader(new InputStreamReader(client.getInputStream()));  
            boolean flag =true;  
            while(flag){  
                //Receive instructions from Client
                String pattern =  receiver.readLine();  
                if(pattern == null || "".equals(pattern)){  
                    flag = false;  
                }else{  
                    if("bye".equals(pattern)){  
                        flag = false;  
                    }else{  
                       
//                    	System.out.println(System.getProperty("user.dir"));
//                		DisGrep test = new DisGrep(pattern, null);
//                		test.grepLines();
//                        String count = test.grepCount();
//                        System.out.println(count);
//                        out.println("Total:" + count + " lines matched");  
                		SysGrep test = new SysGrep(pattern, null, "./vm" + vmNum +".log");
                        test.grepLines();
                        int count = test.grepCount();
                        System.out.println("There are "+ count +" lines matched your pattern on VM" + vmNum);
                        //System.out.println(lines);
                        sender.println("There are "+ count +" lines matched your pattern on VM" + vmNum);
                    }  
                }  
            }  
            sender.close();  
            client.close();  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  

}  
