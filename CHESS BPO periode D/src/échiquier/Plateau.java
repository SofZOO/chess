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
    private final ArrayList<Piece> listePieces;

    public Plateau(){
        echiquier = new Case[LONGUEUR][HAUTEUR];
        for(int x = 0; x < this.LONGUEUR; x++){
            for(int y = 0; y <this.HAUTEUR; y++){
                echiquier[x][y] = new Case();
            }
        }
        listePieces=new ArrayList<>();
    }

    private int intoInt(String coup,int position){
        return Integer.parseInt(String.valueOf(coup.charAt(position)));
    }

    public void placerNouvelleCoord(Coord coordIni , Coord coordFin){

        laCase(coordIni).getPieceActuelle().changeCoord(coordFin);
        laCase(coordFin).rajouterPiece(laCase(coordIni).getPieceActuelle());
        laCase(coordIni).retirerPiece();
    }
    public void déplacer(String coup){
        Coord coordIni,coordFin;
        if (!coupValableSurPlateau(coup)) {
            System.out.println("coup pas valable sur plateau");
        }
        char x = coup.charAt(0), x2 = coup.charAt(2);/*b7b8*/
        int y = intoInt(coup,1),y2 = intoInt(coup,3);
        coordIni = getCoord(x, y);
        coordFin = getCoord(x2, y2);
        if (laCase(coordIni).getPieceActuelle().peutJouer(coordFin)){
            System.out.println("Le coup est jouable");
            if (coupValableSurPiece(coordIni,coordFin)) {
                placerNouvelleCoord(coordIni, coordFin);
                System.out.println("Normalement y'a un changement ici");
            }
            else System.out.println("bassem VENISSIEUX 69200 en esperant que les choses se passent");
        }
        else System.out.println("nike ta soeur tu peux pas faire ");
    }

    private boolean coupValableSurPlateau(String coup){
        if (coup.length()!=4)
            return false;
        if(coup.charAt(0)<'a'||coup.charAt(2)<'a' || coup.charAt(0)>'h'|| coup.charAt(2)>'h')
            return false;
        if(intoInt(coup,1) <1 || intoInt(coup,1) >7 || intoInt(coup,3)<1 || intoInt(coup,3)>7)
            return false;
        return true;
    }

    private boolean coupValableSurPiece(Coord coordIni, Coord coordFin){
        if (laCase(coordFin).isEstOccupé())
            return !(laCase(coordIni).getPieceActuelle().getCouleur().
                    equals(laCase(coordFin).getPieceActuelle().getCouleur()));

        /*si le roi est en echec, il est obligé de déplacer son roi*/
        return true;
    }

    private Case laCase(Coord c){
        return echiquier[c.getX()][c.getY()];
    }

    private Coord getCoord(char x2, int y2) {
        Coord coordIni;
        switch(x2){
            case 'a' : {
                coordIni = new Coord(8 - y2, 0);
                break;
            }
            case 'b' : {
                coordIni = new Coord(8 - y2, 1);
                break;
            }
            case 'c' : {
                coordIni = new Coord(8 - y2, 2);
                break;
            }
            case 'd' : {
                coordIni = new Coord(8 - y2, 3);
                break;
            }
            case 'e' : {
                coordIni = new Coord(8 - y2, 4);
                break;
            }
            case 'f' : {
                coordIni = new Coord(8 - y2, 5);
                break;
            }
            case 'g' : {
                coordIni = new Coord(8 - y2, 6);
                break;
            }
            case 'h' : {
                coordIni = new Coord(8 - y2, 7);
                break;
            }
            default : coordIni = new Coord(0, 0);// TODO: DINGUERIE A CHANGER
        };
        return coordIni;
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
