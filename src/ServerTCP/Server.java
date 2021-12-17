package ServerTCP;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import ModelTCP.Enchere;
import ModelTCP.Membre;
import ModelTCP.Offre;

public class Server{
	public static boolean admin=false;
	public static ArrayList<Membre> listM=new ArrayList<>();
    public static ArrayList<Offre> listO=new ArrayList<>();
    public static ArrayList<Enchere> listE=new ArrayList<>();
    
    public static Membre verifieMembre(String nom){
        for(Membre mbr:listM){
            if(mbr.getNom().equals(nom)){
                return mbr;
            }
        }
        return null;
    }
    public static Enchere verifeE(int id){
        for(Enchere e:listE){
            if(e.getId()==id){
                return e;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException{
        ServerSocket sc =new ServerSocket(4000);
    	System.out.println("j'attends la demande");
        while(true) {
        
            Socket s= sc.accept();
            System.out.println("un client connecté");
            Gestion t=new Gestion(s);
            t.start();
        }
    }
}


