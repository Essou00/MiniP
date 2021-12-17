package ModelTCP;


import java.util.ArrayList;
import java.util.List;

public class Membre {
	public static int cId=1;
	private int id;
	private String nom;

	public List<Offre> offres=new ArrayList<>();

	public Membre(String nom) {
		super();
		this.nom = nom;
		this.id=cId++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
	
}
