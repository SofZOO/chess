package echiquier;

import java.util.ArrayList;

public class Plateau {
    private static final int HAUTEUR = 8, LONGUEUR = 8;
    private final IPiece[][] echiquier;
    private final ArrayList<IPiece> listePieces;
    private boolean propositionNulle;
    private boolean matchNul;

    public Plateau(IJoueur j1, IJoueur j2) {
        echiquier = new IPiece[LONGUEUR][HAUTEUR];
        for (int x = 0; x < LONGUEUR; x++) {
            for (int y = 0; y < HAUTEUR; y++) {
                echiquier[x][y] = null;
            }
        }
        listePieces = new ArrayList<>(j1.getPieces());
        listePieces.addAll(j2.getPieces());
        for (IPiece p : listePieces)
            echiquier[p.getCoord().getLigne()][p.getCoord().getColonne()] = p;
        propositionNulle = false;
        matchNul = false;
    }

    public static int intoInt(String coup, int position) {
        return Integer.parseInt(String.valueOf(coup.charAt(position)));
    }

    private static String chaineCaracteres() {
        StringBuilder sb = new StringBuilder();
        char u = 'a';
        for (int i = 0; i < LONGUEUR; i++) {
            sb.append("     ").append(u);
            u++;
        }
        return sb.toString();
    }

    public void placerNouvelleCoord(Coord coordIni, Coord coordFin) {
        if (laPiece(coordFin) != null) {
            listePieces.remove(laPiece(coordFin));
            laPiece(coordFin).estMangé();
        }
        laPiece(coordIni).changeCoord(coordFin);
        echiquier[coordFin.getLigne()][coordFin.getColonne()] = laPiece(coordIni);
        echiquier[coordIni.getLigne()][coordIni.getColonne()] = null;
    }

    public boolean estJouable(Coord caseSource, Coord caseDest, IJoueur courant) {
        if ((caseDest.getLigne() > (LONGUEUR - 1) || caseDest.getLigne() < 0 || caseDest.getColonne() > (HAUTEUR - 1) || caseDest.getColonne() < 0)) {
            return false;
        }
        IPiece p = echiquier[caseSource.getLigne()][caseSource.getColonne()];
        if (p == null) {
            return false;
        }
//		2- La destination est libre ou est occupée par une pièce adverse
        if (!(coupValableSurPiece(caseSource, caseDest))) {
            return false;
        }

//		3- la pièce autorise ce déplacement
        if (!(p.peutJouer(caseDest, this))) {
            return false;
        }

//      4- si c'est un roi alors la destination n'est pas attaquable par une pièce adverse
        if (p.craintEchec()) {
            echiquier[p.getCoord().getLigne()][p.getCoord().getColonne()] = null;
            for (IPiece piece : listePieces) {
                if (!piece.getCoord().compare(caseDest)) {
                    if (!(piece.getCouleur().equals(p.getCouleur()))) {
                        if (piece.peutJouer(caseDest, this)) {
                            echiquier[p.getCoord().getLigne()][p.getCoord().getColonne()] = p;
                            return false;
                        }
                    }
                }
            }
            echiquier[p.getCoord().getLigne()][p.getCoord().getColonne()] = p;
            return true;
        } else {
//      5- si le joueur courant est echec

            IPiece pieceSrc = laPiece(caseSource);
            IPiece pieceDst = laPiece(caseDest);

            /*laPiece(caseSource).retirerPiece();*/
            echiquier[caseSource.getLigne()][caseSource.getColonne()] = null;
            /*laPiece(caseDest).rajouterPiece(pieceSrc);*/
            echiquier[caseDest.getLigne()][caseDest.getColonne()] = pieceSrc;
            ArrayList<IPiece> test2 = new ArrayList<>(listePieces);
            if (pieceDst != null)
                test2.remove(pieceDst);

            if (echec(courant, test2)) {//TODO a revoir
                /*laPiece(caseSource).rajouterPiece(pieceSrc);*/
                echiquier[caseSource.getLigne()][caseSource.getColonne()] = pieceSrc;
                /*laPiece(caseDest).rajouterPiece(pieceDst);*/
                echiquier[caseDest.getLigne()][caseDest.getColonne()] = pieceDst;

                if (pieceDst != null)
                    test2.add(pieceDst);
                return false;
            }
            /*laPiece(caseSource).rajouterPiece(pieceSrc);*/
            echiquier[caseSource.getLigne()][caseSource.getColonne()] = pieceSrc;
            /*laPiece(caseDest).rajouterPiece(pieceSr);*/
            echiquier[caseDest.getLigne()][caseDest.getColonne()] = pieceDst;
            if (pieceDst != null)
                test2.add(pieceDst);
            return true;
        }

    }

    public boolean chessmat(IJoueur joueur) {
        IPiece roiDuJou = joueur.leRoi();
        for (IPiece piece : listePieces) {
            if (piece.getCouleur().equals(roiDuJou.getCouleur())) {
                for (int cmp1 = 0; cmp1 < LONGUEUR; cmp1++) {
                    for (int cmp2 = 0; cmp2 < HAUTEUR; cmp2++) {
                        if (estJouable(piece.getCoord(), new Coord(cmp1, cmp2), joueur)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean chesspat(IJoueur joueur) {
        IPiece roi = joueur.leRoi();
        for (IPiece piece : listePieces) {
            if (piece.getCouleur().equals(roi.getCouleur())) {
                for (int i = 0; i < LONGUEUR; i++) {
                    for (int j = 0; j < HAUTEUR; j++) {
                        if (piece.getCoord().compare(new Coord(i, j))) {
                            continue;
                        }
                        if (estJouable(piece.getCoord(), new Coord(i, j), joueur)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public String partieFinie(int index, String nom, String autreNom){
      switch (index){
          case (1) : {
              return "La partie est nulle : situation d'échecs et pat pour le joueur " + nom +".";
          }
          case (2) : {
              return "Le joueur " + nom + " est vaincu par échecs et mat. Le joueur " + autreNom + " a gagné.";
          }
          case(3):{
              return  "La partie est nulle : il ne reste que 2 rois sur le plateau.";
          }
          default:{
              return "arvindo";
          }
      }
    }


    public boolean partieNulle(IJoueur blanc,IJoueur noir){
        if (matchNul)
            return true;
        if (chesspat(blanc) || chesspat(noir))
            return true;
        return listePieces.size() == 2;
    }

    public boolean echec(IJoueur bangbang, ArrayList<IPiece> list) {
        for (IPiece piece : list) {
            if (!(piece.getCouleur().equals(bangbang.leRoi().getCouleur()))) {
                if (piece.peutJouer(bangbang.leRoi().getCoord(), this))
                    return true;
            }
        }
        return false;
    }

    private boolean coupValableSurPiece(Coord coordIni, Coord coordFin) {
        if (laPiece(coordFin) != null)
            return !(laPiece(coordIni).getCouleur().equals(laPiece(coordFin).getCouleur()));
        /*si le roi est en echec, il est obligé de déplacer son roi*/
        return true;
    }

    public IPiece laPiece(Coord c) {
        return echiquier[c.getLigne()][c.getColonne()];
    }

    public Coord getCoord(char x2, int y2) {
        return new Coord(8 - y2, x2 - 'a');
    }

    private boolean coupValableSurPlateau(String coup) {
        if (coup.length() != 4)
            return false;
        if (!Character.isDigit(coup.charAt(1))) {
            return false;
        }
        if (!Character.isDigit(coup.charAt(3))) {
            return false;
        }
        if (coup.charAt(0) < 'a' || coup.charAt(2) < 'a' || coup.charAt(0) > 'h' || coup.charAt(2) > 'h')
            return false;
        return intoInt(coup, 1) >= 1 && intoInt(coup, 1) <= 8 && intoInt(coup, 3) >= 1 && intoInt(coup, 3) <= 8;
    }

    public boolean doitRejouer(String coup, IJoueur joueur) {
        Coord coordIni, coordFin;
        if (!coupValableSurPlateau(coup)) {
            return true;
        }
        char x = coup.charAt(0), x2 = coup.charAt(2);/*b7b8*/
        int y = intoInt(coup, 1), y2 = intoInt(coup, 3);
        coordIni = getCoord(x, y);
        coordFin = getCoord(x2, y2);
        if (!estJouable(coordIni, coordFin, joueur)) {
            return true;
        }

        return !laPiece(coordIni).getCouleur().equals(joueur.leRoi().getCouleur());
    }

    private static String affichagePiecesMangees(IJoueur j) {
        StringBuilder sb = new StringBuilder();
        for (IPiece p : j.getPieces()) {
            if (p.isMangé()) {
                sb.append(p.toChar()).append(" ");
            }
        }
        return sb.toString();
    }


    public String affichePlateau(IJoueur joueurBlanc, IJoueur joueurNoir) {
        StringBuilder sb = new StringBuilder();
        sb.append(chaineCaracteres()).append("         Pièces mangées par le joueur ").append(joueurNoir.getNom()).append(" : ");
        sb.append(affichagePiecesMangees(joueurBlanc));
        sb.append("\n");
        for (int cmpHauteur = 0, cmp = 8; cmpHauteur < HAUTEUR; cmpHauteur++, cmp--) {
            sb.append("    ---   ---   ---   ---   ---   ---   ---   ---\n");
            sb.append(cmp).append(" | ");
            for (int cmpLongueur = 0; cmpLongueur < LONGUEUR; cmpLongueur++) {
                sb.append(" ");
                if (laPiece(new Coord(cmpHauteur, cmpLongueur)) == null)
                    sb.append(" ");
                else {
                    sb.append(echiquier[cmpHauteur][cmpLongueur].toChar());
                }
                sb.append("  | ");
            }
            if(cmp == 5 ){
                sb.append(cmp).append("    Si vous souhaitez abandonner veuillez écrire \"abandon\" à la place d'un coup\n");
            }
            else if(cmp == 4){
                sb.append(cmp).append("    Pour la propostion de la nulle veuillez écrire \"nulle\" à la place d'un coup que vous souhaitez entrer\n");
            }
            else{
                sb.append(cmp).append("\n");
            }
        }
        sb.append("    ---   ---   ---   ---   ---   ---   ---   ---\n");
        sb.append(chaineCaracteres()).append("         Pièces mangées par le joueur ").append(joueurBlanc.getNom()).append(" : ");
        sb.append(affichagePiecesMangees(joueurNoir));
        sb.append("\n");
        return sb.toString();
    }

    public ArrayList<IPiece> getListePieces() {
        return listePieces;
    }

    public boolean getPropositionNulle(){return this.propositionNulle;}
    public void setPropositionNulle(boolean change){this.propositionNulle = change;}
    public void setMAtchNul(boolean change){this.matchNul=change;}
}
