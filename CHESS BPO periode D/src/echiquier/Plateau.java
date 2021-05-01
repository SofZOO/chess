package echiquier;

import appli.Joueur;

import java.util.ArrayList;

public class Plateau {
    private final Case[][] echiquier;
    private final int HAUTEUR = 8, LONGUEUR = 8;
    private final ArrayList<IPiece> listePieces;
    private boolean echecEtPat;

    public Plateau(Joueur j1, Joueur j2) {
        echiquier = new Case[LONGUEUR][HAUTEUR];
        for (int x = 0; x < this.LONGUEUR; x++) {
            for (int y = 0; y < this.HAUTEUR; y++) {
                echiquier[x][y] = new Case();
            }
        }
        listePieces = new ArrayList<>();
        listePieces.addAll(j1.getPieces());
        listePieces.addAll(j2.getPieces());
        for (IPiece p : listePieces)
            echiquier[p.getLigne()][p.getColonne()].rajouterPiece(p);
        this.echecEtPat = false;
    }

    public int intoInt(String coup, int position) {
        return Integer.parseInt(String.valueOf(coup.charAt(position)));
    }

    public void placerNouvelleCoord(Coord coordIni, Coord coordFin) {
        if (laCase(coordFin).isEstOccupé())
            listePieces.remove(laCase(coordFin).getPieceActuelle());
        laCase(coordIni).getPieceActuelle().changeCoord(coordFin);
        laCase(coordFin).rajouterPiece(laCase(coordIni).getPieceActuelle());
        laCase(coordIni).retirerPiece();
    }

    public boolean estJouable(Coord caseSource, Coord caseDest, IJoueur courant) {
        IPiece src;
        IPiece dst;
        if ((caseDest.getLigne() > 7 || caseDest.getLigne() < 0 || caseDest.getColonne() > 7 || caseDest.getColonne() < 0)) {
            return false;
        }
        IPiece p = echiquier[caseSource.getLigne()][caseSource.getColonne()].getPieceActuelle();
        if (p == null) {
            System.out.println("LA CASE SOURCE EST VIDE");
            return false;
        }
//		2- La destination est libre ou est occupée par une pièce adverse
        if (!(coupValableSurPiece(caseSource, caseDest))) {
            System.out.println("coup PAS ValableSurPiece");
            return false;
        }

//		3- la pièce autorise ce déplacement
        if (!(p.peutJouer(caseDest, this.echiquier))) {
            System.out.println("coup pas valable pour la piece");
            return false;
        }

//      4- si c'est un roi alors la destination n'est pas attaquable par une pièce adverse
        if (p.craintEchec()) {
            laCase(p.getCoord()).retirerPiece();
            for (IPiece piece : listePieces) {
                if (!(piece.getCouleur().equals(p.getCouleur()))) {
                    if (piece.peutJouer(caseDest, echiquier)) {
                        laCase(p.getCoord()).rajouterPiece(p);
                        System.out.println("Le roi sera mis en echec");
                        return false;
                    }
                }
            }
            laCase(p.getCoord()).rajouterPiece(p);
            return true;
        }
        else {
//      5- si le joueur courant est echec

         src = laCase(caseSource).getPieceActuelle();
         dst = laCase(caseDest).getPieceActuelle();

         laCase(caseSource).retirerPiece();
         laCase(caseDest).rajouterPiece(src);
         ArrayList <IPiece> test = new ArrayList<>(listePieces);
         if (dst != null)
             test.remove(dst);

         if (echec(courant,test)) {//TODO a revoir
             System.out.println("le coup ne peut pas etre joue car le roi est toujours en echec");
             laCase(caseSource).rajouterPiece(src);
             laCase(caseDest).rajouterPiece(dst);
             if (dst != null)
                 test.add(dst);
             return false;
         }
         laCase(caseSource).rajouterPiece(src);
         laCase(caseDest).rajouterPiece(dst);
         if (dst != null)
             test.add(dst);
         return true;
        }

    }

//    public boolean chesspat(Joueur joueur){
//
//    }

    public void déplacer(String coup, IJoueur courant, IJoueur pasCourant) {
        Coord coordIni, coordFin;
        if (!coupValableSurPlateau(coup)) {
            System.out.println("coup pas valable sur plateau");
        }
        char x = coup.charAt(0), x2 = coup.charAt(2);/*b7b8*/
        int y = intoInt(coup, 1),y2 =intoInt(coup,3);

        coordIni = getCoord(x, y);
        coordFin = getCoord(x2, y2);

        if (estJouable(coordIni, coordFin, courant)) {
            System.out.println("METHODE VALIDE");
            placerNouvelleCoord(coordIni, coordFin);
            if (echec(pasCourant,listePieces)) {
                System.out.println("le joueur " + pasCourant.getNom() + " est echec");
                if (chessmat(pasCourant)){
                    pasCourant.aPerdu();
                }

            }
        } else System.out.println("METHODE PAS VALID22");
    }

    public boolean chessmat(IJoueur joueur) {
        IPiece roiDuJou = joueur.leRoi();
        //todo : big dinguerie
        /*une piece alliée peut couvrir le roi en allant sur le chemin ou la piece ennemie met en echec le roi qui met en echec*/
        for (IPiece piece : listePieces){
            if (piece.compareCouleur(roiDuJou)){
                for(int cmp1 = 0; cmp1 < 8; cmp1++){
                    for(int cmp2 = 0; cmp2 < 8 ; cmp2++) {
                        System.out.println("["+cmp1 + ";" + cmp2 + "]");
                        if (piece.getCoord().compare(new Coord(cmp1, cmp2))) {
                            continue;
                        }
                        else if (estJouable(piece.getCoord(), new Coord(cmp1, cmp2), joueur)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    public boolean echec(IJoueur bangbang, ArrayList<IPiece> list) {
        for (IPiece piece : list) {
            if (!(piece.getCouleur().equals(bangbang.leRoi().getCouleur()))) {
                if (piece.peutJouer(bangbang.leRoi().getCoord(), echiquier))
                    return true;
            }
        }
        return false;
    }

    private boolean coupValableSurPlateau(String coup) {
        if (coup.length() != 4)
            return false;
        if(!Character.isDigit(coup.charAt(1))){
            return false;
        }
        if(!Character.isDigit(coup.charAt(3))){
            return false;
        }
        if (coup.charAt(0) < 'a' || coup.charAt(2) < 'a' || coup.charAt(0) > 'h' || coup.charAt(2) > 'h')
            return false;
        if (intoInt(coup, 1) < 1 || intoInt(coup, 1) > 8 || intoInt(coup, 3) < 1 || intoInt(coup, 3) > 8)
            return false;
        return true;
    }

    private boolean coupValableSurPiece(Coord coordIni, Coord coordFin) {
        if (laCase(coordFin).isEstOccupé())
            return !(laCase(coordIni).getPieceActuelle().getCouleur().
                    equals(laCase(coordFin).getPieceActuelle().getCouleur()));

        /*si le roi est en echec, il est obligé de déplacer son roi*/
        return true;
    }

    private Case laCase(Coord c) {
        return echiquier[c.getLigne()][c.getColonne()];
    }

    public Coord getCoord(char x2, int y2) {
        Coord coordIni;
        switch (x2) {
            case 'a': { coordIni = new Coord(8 - y2, 0); break; }
            case 'b': { coordIni = new Coord(8 - y2, 1); break; }
            case 'c': { coordIni = new Coord(8 - y2, 2); break; }
            case 'd': { coordIni = new Coord(8 - y2, 3); break; }
            case 'e': { coordIni = new Coord(8 - y2, 4); break; }
            case 'f': { coordIni = new Coord(8 - y2, 5); break; }
            case 'g': { coordIni = new Coord(8 - y2, 6); break; }
            case 'h': { coordIni = new Coord(8 - y2, 7); break; }
            default: coordIni = new Coord(0, 0);// TODO: DINGUERIE A CHANGER
        }
        return coordIni;
    }

    public boolean doitRejouer(String coup, IJoueur joueur){
        Coord coordIni, coordFin;
        if(!coupValableSurPlateau(coup)){
            System.out.println("test : methode doitRejouer coup en dehors du plateau");
            return true;
        }
        char x = coup.charAt(0), x2 = coup.charAt(2);/*b7b8*/
        int y = intoInt(coup,1),y2 = intoInt(coup,3);
        coordIni = getCoord(x, y);
        coordFin = getCoord(x2, y2);



        if(!estJouable(coordIni,coordFin,joueur)){
            System.out.println("test : methode doitRejouer pas un bon coup (estJouable)");
            return true;
        }

        if(!laCase(coordIni).getPieceActuelle().compareCouleur(joueur.leRoi())) {
            System.out.println("test : methode doitRejouer pas la bonne couleur");
            return true;
        }

        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("     a     b     c     d     e     f     g     h \n");
        for (int cmpHauteur = 0, cmp = 8; cmpHauteur < HAUTEUR; cmpHauteur++, cmp--) {
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

    public ArrayList<IPiece> getListePieces() {
        return listePieces;
    }

    public void setEchecEtPat(boolean echecEtPat) {
        this.echecEtPat = echecEtPat;
    }
}
