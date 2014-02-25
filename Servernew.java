
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servernew {   
    public static void main(String args[])throws IOException
    { 
        ServerSocket ss=null;
	int port = 15138;
        try
        {  
            ss=new ServerSocket(port);
        }
        catch(IOException e)
        { 
            System.out.println("couldn't listen");
            System.exit(0);
        }
        Socket cs=null;
        try
        { 
            cs=ss.accept();
            System.out.println("Connection established"+cs);
        }
        catch(Exception e)
        { 
            System.out.println("Accept failed");
            System.exit(1);
        } 
        PrintWriter put=new PrintWriter(cs.getOutputStream(),true);
        BufferedReader st=new BufferedReader(new InputStreamReader(cs.getInputStream()));
        String s=st.readLine();
        System.out.println("The requested file is : "+s);
        File f=new File(s);
        if(f.exists())
        { 
            BufferedInputStream d=new BufferedInputStream(new FileInputStream(s));
            BufferedOutputStream outStream = new BufferedOutputStream(cs.getOutputStream());
            byte buffer[] = new byte[1024];
            int read;
	    long start = System.currentTimeMillis();
	    java.lang.Runtime rt = java.lang.Runtime.getRuntime();
	    String host_add = cs.getInetAddress().getHostAddress();
	    String cmd = "/usr/bin/sudo /usr/sbin/tcpdump -i eth0 src port "+port+" and (dst host "+host_add+" or src host "+host_add+") -w capture.pcap";
	    System.out.println(cmd);
	    java.lang.Process p = rt.exec(cmd);
	 //   BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
	 //   BufferedReader eb = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	    
            while((read = d.read(buffer))!=-1)
            {
                outStream.write(buffer, 0, read);
                outStream.flush();
            }
            d.close();
	    String line = "";
	    p.destroy();
	   // while((line = b.readLine()) != null) System.out.println(line);
	   // while((line = eb.readLine()) != null) System.out.println(line);
	    
	    long end = System.currentTimeMillis() - start;
            System.out.println("File transfered");
	    System.out.println("Transfer time in ms: " + end);	
            cs.close();
            ss.close();
        }  
    }  
}

