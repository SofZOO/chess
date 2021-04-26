package échiquier;

import java.util.ArrayList;

public class Plateau {
    private final Case[][] echiquier;
    private final int HAUTEUR = 8, LONGUEUR = 8;
    private final ArrayList<IPiece> listePieces;

    public Plateau(IFabriquePiece fab){
        echiquier = new Case[LONGUEUR][HAUTEUR];
        for(int x = 0; x < this.LONGUEUR; x++){
            for(int y = 0; y <this.HAUTEUR; y++){
                echiquier[x][y] = new Case();
            }
        }
        listePieces=new ArrayList<>();
        this.initialiserEchiquier(fab);
    }

    public void initialiserEchiquier(IFabriquePiece fab){
        listePieces.add(fab.fabrique(0,new Coord(0,0),false));
        listePieces.add(fab.fabrique(0,new Coord(7,0),true));
        listePieces.add(fab.fabrique(1,new Coord(7,4),true));
        listePieces.add(fab.fabrique(1,new Coord(0,4),false));

        for(IPiece p : listePieces)
            echiquier[p.getLigne()][p.getColonne()].rajouterPiece(p);
    }

    private int intoInt(String coup,int position){
        return Integer.parseInt(String.valueOf(coup.charAt(position)));
    }

    public void placerNouvelleCoord(Coord coordIni , Coord coordFin){
        laCase(coordIni).getPieceActuelle().changeCoord(coordFin);
        laCase(coordFin).rajouterPiece(laCase(coordIni).getPieceActuelle());
        laCase(coordIni).retirerPiece();
    }

    public boolean estJouable(Coord caseSource, Coord caseDest) {
        IPiece p = echiquier[caseSource.getLigne()][caseSource.getColonne()].getPieceActuelle();
        if (p == null) {
            System.out.println("LA CASE SOURCE EST VIDE");
            return false;
        }
//		2- La destination est libre ou est occupée par une pièce adverse
        if (!(coupValableSurPiece(caseSource, caseDest))){
            System.out.println("coup PAS ValableSurPiece");
            return false;
        }

//		3- la pièce autorise ce déplacement
        if (!(p.peutJouer(caseDest, this.echiquier))) {
            System.out.println("coup pas valable pour la piece");
            return false;
        }

//      4- si c'est un roi alors la destination n'est pas attaquable par une pièce adverse
        if (p.craintEchec()){
            for (IPiece piece : listePieces){
                if (!(piece.getCouleur().equals(p.getCouleur()))){
                    if(piece.peutJouer(caseDest,echiquier)){
                        System.out.println("Le roi sera mis en echec");
                        return false;
                    }
                }
            }
        }

        return true;
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

        if (estJouable(coordIni,coordFin)) {
            System.out.println("METHODE VALIDE");
            placerNouvelleCoord(coordIni, coordFin);
        }
        else System.out.println("METHODE PAS VALID22");
    }

    private boolean coupValableSurPlateau(String coup){
        if (coup.length()!=4)
            return false;
        if(coup.charAt(0)<'a'||coup.charAt(2)<'a' || coup.charAt(0)>'h'|| coup.charAt(2)>'h')
            return false;
        if(intoInt(coup,1) <1 || intoInt(coup,1) >8 || intoInt(coup,3)<1 || intoInt(coup,3)>8)
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
        return echiquier[c.getLigne()][c.getColonne()];
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
        }
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

}
