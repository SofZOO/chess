package échiquier;

import appli.Coord;
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

    public void déplacer(String coup){
        char x = coup.charAt(0), x2 = coup.charAt(2);            /*b7b8*/
        int y = Integer.parseInt(String.valueOf(coup.charAt(1))),y2 = Integer.parseInt(String.valueOf(coup.charAt(3)));
        Coord coordIni,coordFin;

        coordIni = getCoord(x, y);
        coordFin = getCoord(x2, y2);
        if (echiquier[coordIni.getX()][coordIni.getY()].getPieceActuelle().peutJouer(coordFin.getX(),coordFin.getY())){
            System.out.println("YOUPI");
            echiquier[coordIni.getX()][coordIni.getY()].getPieceActuelle().changeCoord(coordFin.getX(),coordFin.getY());
            echiquier[(coordFin.getX())][coordFin.getY()].rajouterPiece(echiquier[coordIni.getX()][coordIni.getY()].getPieceActuelle());
            echiquier[coordIni.getX()][coordIni.getY()].retirerPiece();
            System.out.println("Normalement y'a un changement ici");
        }
        else System.out.println("nike ta soeur tu peux pas faire ");
    }

    private Coord getCoord(char x2, int y2) {
        Coord coordFin;
        coordFin = switch (x2) {
            case 'a' -> coordFin = new Coord(8 - y2, 0);
            case 'b' -> coordFin = new Coord(8 - y2, 1);
            case 'c' -> coordFin = new Coord(8 - y2, 2);
            case 'd' -> coordFin = new Coord(8 - y2, 3);
            case 'e' -> coordFin = new Coord(8 - y2, 4);
            case 'f' -> coordFin = new Coord(8 - y2, 5);
            case 'g' -> coordFin = new Coord(8 - y2, 6);
            case 'h' -> coordFin = new Coord(8 - y2, 7);
            default -> new Coord(0, 0);// TODO: DINGUERIE A CHANGER
        };
        return coordFin;
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
