package échiquier;

import piece.Piece;
import piece.Roi;
import piece.Tour;
import piece.couleurPiece;

import java.util.ArrayList;

public class Plateau {
    private final Case[][] echiquier;
    private final int HAUTEUR = 8, LONGUEUR = 8;
    private ArrayList<Piece> listePieces;

    public Plateau(){
        echiquier = new Case[LONGUEUR][HAUTEUR];
        for(int x = 0; x < this.LONGUEUR; x++){
            for(int y = 0; y <this.HAUTEUR; y++){
                echiquier[x][y] = new Case();
            }
        }
        listePieces=new ArrayList<>();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("     a     b     c     d     e     f     g     h \n");
        for (int cmpHauteur = 0,cmp = 8; cmpHauteur < HAUTEUR; cmpHauteur++,cmp--) {
            sb.append("    ---   ---   ---   ---   ---   ---   ---   ---\n");
            sb.append(cmp).append(" | ");
            for (int cmpLongueur = 0; cmpLongueur < LONGUEUR; cmpLongueur++) {
                sb.append(" ").append(echiquier[cmpHauteur][cmpLongueur]).append("  | ");
            }
            sb.append(cmp).append("\n");
        }
        sb.append("    ---   ---   ---   ---   ---   ---   ---   ---\n");
        sb.append("     a     b     c     d     e     f     g     h \n");
        return sb.toString();
    }

    public void initialiserEchiquier(){
        Tour t1= new Tour(couleurPiece.NOIR ,0,0);
        Tour t2 = new Tour(couleurPiece.BLANC, 7,0);
        Roi r1 = new Roi(couleurPiece.BLANC,7,4);
        Roi r2 = new Roi(couleurPiece.NOIR,0,4);
        listePieces.add(t1);
        listePieces.add(t2);
        listePieces.add(r1);
        listePieces.add(r2);

        for(Piece p : listePieces)
            echiquier[p.getPosX()][p.getPosY()].rajouterPiece(p);
    }
}
