package ModelTCP;

public class Offre {

	private static int cId=1;
	private int id;
	private Membre m;
	private Enchere e;
	private float prix;
	private boolean Ibest=false;
	public Offre(Membre m, Enchere e, float prix) {
		super();
		this.id=cId++;
		this.m = m;
		this.e = e;
		this.prix = prix;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Membre getM() {
		return m;
	}
	public void setM(Membre m) {
		this.m = m;
	}
	public Enchere getE() {
		return e;
	}
	public void setE(Enchere e) {
		this.e = e;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public boolean isIbest() {
		return Ibest;
	}
	public void setIbest(boolean best) {
		this.Ibest = best;
	}
	
	
	

}
