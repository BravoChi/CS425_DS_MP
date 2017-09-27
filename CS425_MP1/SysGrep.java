//package grep;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SysGrep {

    /*
     * Member Variables
     */
	
    private String m_pattern = "";
    private String m_flag = "";
    private String m_file_path = "";
 
    /*
     * Constructor
     */
    public  SysGrep(String pattern, String flag, String file_path){
    	this.m_pattern = pattern;
    	this.m_flag = flag;
    	this.m_file_path = file_path;
    }
    
    /*
     * Print Matched Lines
     */
    public void grepLines() {
        String s = "";
        String lines = "";
        try {
     	   
     	   String command = "grep" +" " + m_pattern + " " + m_file_path;
     	   String command_test = "cat" + " ./vm1.log";
     	   System.out.println(command);
            Process p = Runtime.getRuntime().exec(command);
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            // read the output from the command
            while ((s = stdInput.readLine()) != null) {
               System.out.println(s);
                //lines += s + "\n";
            }
            
            // read any errors from the attempted command
//            System.out.println("Here is the standard error of the command (if any):\n");
//            while ((s = stdError.readLine()) != null) {
//                System.out.println(s);
//            }
//           
//            System.exit(0);
            //PrintWriter writer =  new PrintWriter(m_pattern +"-result.txt");
            //writer.println(lines);
            //writer.close();
            //return lines;
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
     //       return null;
        }
    }
 

    /*
     * Print Line Count
     */
    public int grepCount() {
      
       String s = "";
       int count = 0;

       try {
    	   String command = "grep -c " + m_pattern + " " + m_file_path;
    	   System.out.println(command);
           Process p = Runtime.getRuntime().exec(command);
           
           BufferedReader stdInput = new BufferedReader(new 
                InputStreamReader(p.getInputStream()));

           BufferedReader stdError = new BufferedReader(new 
                InputStreamReader(p.getErrorStream()));

           // read the output from the command
           while ((s = stdInput.readLine()) != null) {
        	   count += Integer.parseInt(s);
               //System.out.println(s);
           }
           
           // read any errors from the attempted command
//           System.out.println("Here is the standard error of the command (if any):\n");
//           while ((s = stdError.readLine()) != null) {
//               System.out.println(s);
//           }
//          
//           System.exit(0);
           return count;
       }
       catch (IOException e) {
           System.out.println("exception happened - here's what I know: ");
           e.printStackTrace();
           System.exit(-1);
           return 0;
       }
       
    }
    
}
