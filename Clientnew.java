
import java.io.*;
import java.net.Socket;

public class Clientnew {
    public static void main(String args[])throws IOException
    {
    Socket s=null;
    BufferedReader get=null;
    PrintWriter put=null;
    try
    { 
        s=new Socket("192.168.1.1",15138);
        get=new BufferedReader(new InputStreamReader(s.getInputStream()));
        put=new PrintWriter(s.getOutputStream(),true);        
    }  
    catch(Exception e)
    {
        System.exit(0);
    }
    String u,f;
    System.out.println("Enter the file name to transfer from server:");
    DataInputStream dis=new DataInputStream(System.in);
    f=dis.readLine();
    put.println(f);
    File f1=new File(f);
    FileOutputStream  fs=new FileOutputStream(f1);

    BufferedInputStream d=new BufferedInputStream(s.getInputStream());
    BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(f1));
    byte buffer[] = new byte[1024];
    int read;
    while((read = d.read(buffer))!=-1)
    {
        outStream.write(buffer, 0, read);
        outStream.flush();
    }

    fs.close();
    System.out.println("File received");
    s.close();
    }
}


