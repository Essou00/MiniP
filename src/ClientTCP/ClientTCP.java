package ClientTCP;


import java.io.*;
import java.net.*;

public class ClientTCP {
    public static void main(String[] args) throws IOException{
        Socket s=new Socket("127.0.0.1" ,4000);
        //System.out.println(s.getInetAddress()+"/"+s.getPort());

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader in_sc = new BufferedReader(in);

        OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out_sc = new PrintWriter(new BufferedWriter(out),true);

        BufferedReader in_cl= new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String msgSend=in_cl.readLine();
            out_sc.println(msgSend);
            String msgRcv= in_sc.readLine();
            System.out.println(msgRcv);
        }
    }
}
