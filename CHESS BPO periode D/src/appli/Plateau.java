package appli;

public class Plateau {
    private final Case[][] echiquier;
    private final int HAUTEUR = 8, LONGUEUR = 8;

    public Plateau(){
        echiquier = new Case[LONGUEUR][HAUTEUR];
        for(int x = 0; x < this.LONGUEUR; x++){
            for(int y = 0; y <this.HAUTEUR; y++){
                echiquier[x][y] = new Case();
            }
        }

    }



}
