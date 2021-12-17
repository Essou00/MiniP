package ServerTCP;
import ModelTCP.Enchere;

public class DureeEnchere extends Thread{
    private Enchere e;

    public DureeEnchere(Enchere e) {
        this.e = e;
    }

    @Override
    public void run() {
        try {
            sleep(e.getTemps()*(long)60000);
        }catch (Exception e){

        }
        this.e.close();
    }
}
