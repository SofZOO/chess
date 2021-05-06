package echiquier;

import piece.Piece;

import java.util.ArrayList;

public class Plateau {
    private final IPiece[][] echiquier;
    private static final int HAUTEUR = 8, LONGUEUR = 8;
    private final ArrayList<IPiece> listePieces;
    private final ArrayList<IPiece> piecesMangées;
    private boolean echecEtPat;

    public Plateau(IJoueur j1, IJoueur j2) {
        echiquier = new IPiece[LONGUEUR][HAUTEUR];
        for (int x = 0; x < LONGUEUR; x++) {
            for (int y = 0; y < HAUTEUR; y++) {
                echiquier[x][y] = null;
            }
        }
        listePieces = new ArrayList<>();
        listePieces.addAll(j1.getPieces());
        listePieces.addAll(j2.getPieces());
        for (IPiece p : listePieces)
            echiquier[p.getCoord().getLigne()][p.getCoord().getColonne()] = p;
        piecesMangées = new ArrayList<>();
        this.echecEtPat = false;
    }

    public static int intoInt(String coup, int position) {
        return Integer.parseInt(String.valueOf(coup.charAt(position)));
    }

    public void placerNouvelleCoord(Coord coordIni, Coord coordFin) {
        if (laPiece(coordFin) != null) {
            listePieces.remove(laPiece(coordFin));
            piecesMangées.add(laPiece(coordFin));
        }
        laPiece(coordIni).changeCoord(coordFin);
        echiquier[coordFin.getLigne()][coordFin.getColonne()] = laPiece(coordIni);
        echiquier[coordIni.getLigne()][coordIni.getColonne()] = null;
    }

    public boolean estJouable(Coord caseSource, Coord caseDest, IJoueur courant) {
        IPiece pieceSrc;
        IPiece pieceDst;
        if ((caseDest.getLigne() > 7 || caseDest.getLigne() < 0 || caseDest.getColonne() > 7 || caseDest.getColonne() < 0)) {
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

            /*laPiece(p.getCoord()).retirerPiece();*/

            echiquier[p.getCoord().getLigne()][p.getCoord().getColonne()] = null;

            for (IPiece piece : listePieces) {
                if (!piece.getCoord().compare(caseDest)) {
                    if (!(piece.getCouleur().equals(p.getCouleur()))) {
                        if (piece.peutJouer(caseDest, this)) {
                            /*laPiece(p.getCoord()).rajouterPiece(p);*/
                            echiquier[p.getCoord().getLigne()][p.getCoord().getColonne()] = p;
                            return false;
                        }
                    }
                }

            }
            echiquier[p.getCoord().getLigne()][p.getCoord().getColonne()] = p;
            /*laPiece(p.getCoord()).rajouterPiece(p);*/
            return true;
        } else {
//      5- si le joueur courant est echec

            pieceSrc = laPiece(caseSource);
            pieceDst = laPiece(caseDest);

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
                for (int cmp1 = 0; cmp1 < 8; cmp1++) {
                    for (int cmp2 = 0; cmp2 < 8; cmp2++) {
                        if (piece.getCoord().compare(new Coord(cmp1, cmp2))) {
                            continue;
                        } else if (estJouable(piece.getCoord(), new Coord(cmp1, cmp2), joueur)) {
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
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
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
        return new Coord(8-y2,x2-'a');
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
        if (intoInt(coup, 1) < 1 || intoInt(coup, 1) > 8 || intoInt(coup, 3) < 1 || intoInt(coup, 3) > 8)
            return false;
        return true;
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

        if (!laPiece(coordIni).getCouleur().equals(joueur.leRoi().getCouleur())) {
            return true;
        }

        return false;
    }


    public String affichePlateau(IJoueur joueurBlanc, IJoueur joueurNoir) {
        StringBuilder sb = new StringBuilder();
        sb.append("     a     b     c     d     e     f     g     h         Pièces gray par le joueur Noir : ");
        for (IPiece pi : piecesMangées) {
            if (pi.getCouleur().equals(joueurBlanc.leRoi().getCouleur()))
                sb.append(pi.toChar()).append(" ");
        }
        sb.append("\n");
        for (int cmpHauteur = 0, cmp = 8; cmpHauteur < HAUTEUR; cmpHauteur++, cmp--) {
            sb.append("    ---   ---   ---   ---   ---   ---   ---   ---\n");
            sb.append(cmp).append(" | ");
            for (int cmpLongueur = 0; cmpLongueur < LONGUEUR; cmpLongueur++) {
                sb.append(" ");

                if (laPiece(new Coord(cmpHauteur,cmpLongueur)) == null)
                    sb.append(" ");
                else {
                    sb.append(echiquier[cmpHauteur][cmpLongueur].toChar());
                }
                    sb.append("  | ");
            }
            sb.append(cmp).append("\n");
        }
        sb.append("    ---   ---   ---   ---   ---   ---   ---   ---\n");
        sb.append("     a     b     c     d     e     f     g     h         Pièces gray par le joueur Blanc : ");
        for (IPiece pi : piecesMangées) {
            if (pi.getCouleur().equals(joueurNoir.leRoi().getCouleur()))
                sb.append(pi.toChar()).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }

    public boolean getEchecEtPat() {
        return echecEtPat;
    }

    public ArrayList<IPiece> getListePieces() {
        return listePieces;
    }

    public void setEchecEtPat(boolean echecEtPat) {
        this.echecEtPat = echecEtPat;
    }
}
