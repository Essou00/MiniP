package ServerTCP;


import java.io.*;
import java.net.*;

import ModelTCP.Enchere;
import ModelTCP.Membre;
import ModelTCP.Offre;


public class Gestion extends Thread {
	private Socket s;
	private boolean adm=false;
	private Membre mb= null;


	public Gestion(Socket s) {
		super();
		this.s = s;
	}
	String [] indicateur= {};

	@Override
	public void run() {
		try {
			InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader in_sc = new BufferedReader(in);

            OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
            PrintWriter out_sc = new PrintWriter(new BufferedWriter(out), true);
            
            while(true) {
                String m = in_sc.readLine();
                if(m.startsWith("CONNECTION ") && m.length()>11) {
                	String nom=m.substring(11);
                	if(Server.verifieMembre(nom)==null) {
            			this.mb=new Membre(nom);
            			Server.listM.add(mb);
                		out_sc.println("Un client viens de rejoindre avec l'id: "+mb.getId());
                		break;
	            	}else {
	            		out_sc.println("C'est nom ("+nom+") existe deja");
	            	}
                }else if(m.equals("ADMINISTRATEUR")){
                	if(Server.admin==false) {
                		Server.admin=true;
                		out_sc.println("L'Administrateur est connceté ");
                		this.adm=true;
                		break;
                	}else {
                		out_sc.println("Administrateur est déja connecté");
                	}
                }else {
            		out_sc.println("Saisir CONNECTION (suivi de votre nom) ou ADMINISTRATEUR");
                }
            }
            if(this.adm) {
            	while(true) {
	            	String m = in_sc.readLine();
	            	if(m.startsWith("AJOUTEN ")) {
	            		String c=m.substring(8);
	            		String t[]=c.split("##");
						Enchere e = new Enchere(t[0], Float.parseFloat(t[1]), Integer.parseInt(t[2]));
	            		Server.listE.add(e);
	            		DureeEnchere d = new DureeEnchere(e);
						d.start();
	     				out_sc.println("Une nouvelle enchere est ajoutée ");

	            	}else {
						out_sc.println("Svp votre commande n'existe ");
						
						
						
					}
	            }
            	
            }else {
	            while(true) {
	            	String m = in_sc.readLine();
	            	if(m.equals("ENCHERES")) {
	         			String s="";
	         			for(Enchere e: Server.listE) {
	         				s+=e.getId()+"#"+e.getDesc()+"#"+e.getPrix()+"#"+e.getEtat()+"#"+e.mPrix()+"///";
	         			}
	         			if(s.length()>0) {
	         				out_sc.println(s);
	         			}
	         			else {
	         				out_sc.println("Il n'a pas d'encheres disponible");
	         			}
	            		
	            	}else if (m.startsWith("OFFRE ")) {
						String cd=m.substring(6);
						String t[]=cd.split("##");
						Enchere e=null;
						for (Enchere e1:Server.listE) {
							if(e1.getId()==Integer.parseInt(t[0])){
								e=e1;
								break;
							}
						}
						if(e!=null || e.getEtat()==false){
							float p=e.mPrix();
							if(Float.parseFloat(t[1])>p){
								Offre o = new Offre(this.mb, e, Float.parseFloat(t[1]));
								Server.listO.add(o);
								this.mb.offres.add(o);
								o.getE().offres.add(o);
								out_sc.println("Nouvelle Offre ajoutée ");
							}else {
								out_sc.println("Offre invalide ");
							}
						}else {
							out_sc.println("Enchere introuvable ou déja fermée");
						}
					}else if(m.equals("LIST")) {
						String s="";
						for(Enchere e: Server.listE) {
							if(e.getEtat()==true) {
								s += e.getId() + "#" + e.getDesc() + "#" + e.getPrix() + "#" + e.getEtat() + "#" + e.mPrix() + "///";
							}
						}
						if(s.length()>0) {
							out_sc.println(s);
						}
						else {
							out_sc.println("Aucune Enchere en cours");
						}
					}else if(m.equals("OFFREGAGNER")) {
						String s="";
						for(Offre o: this.mb.offres) {
							if(o.isIbest()==true) {
								System.out.println("Voici vos offres gagnées :");
								s += o.getId() + "#" +o.getE().getId()+"#"+o.getPrix()+"///";
							}
						}
						if(s.length()>0) {
							out_sc.println(s);
						}
						else {
							out_sc.println("Vous avez gagné aucune offre . Resté connecter des surprises vs attend !");
						}
					}else if(m.equals("OFFRESDIS")) {
						String s="";
						for(Offre of: this.mb.offres) {
							s += of.getId() + "#" +of.getE().getId()+"#"+of.getPrix()+"///";
						}
						if(s.length()>0) {
							out_sc.println(s);
						}
						else {
							out_sc.println("Aucune offre disponible");
						}
					}else {
						out_sc.println("Svp votre commande n'est pas reconnue ");
						
						
					}
							          	
	            }
            }
			
			
			
			
		}catch(

	Exception e)
	{

	}
}

}
