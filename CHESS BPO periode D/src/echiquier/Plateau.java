package echiquier;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private static final int HAUTEUR = 8; // Constante définissant la hauteur du plateau
    private static final int LONGUEUR = 8;// Constante définissant la longueur du plateau
    private final IPiece[][] echiquier; // Tableau de IPieces : l'échiquier
    private final ArrayList<IPiece> listePieces;// Liste contenant toute les pieces encore jouables sur le plateau
    private boolean propositionNulle; // Boolean, vrai si un joueur propose de finir en match nul
    private boolean matchNul; // Boolean, vrai si la partie se conclut sur un match nul

    /**
     * Constructeur du plateau
     *
     * @param j1 Le premier joueur
     * @param j2 le deuxième joueur
     */
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

    /**
     * Fonction permettant la conversion d'un caractere d'une chaine de caractere en entier
     *
     * @param coup     le coup du joueur
     * @param position la position du caractere que l'on veux convertir en entier
     * @return le char convertit en int
     */
    public static int intoInt(String coup, int position) {
        return Integer.parseInt(String.valueOf(coup.charAt(position)));
    }

    /**
     * Permet de renvoyer une chaine de caractère allant de A à H pour les contours de l'échiquier
     *
     * @return la chaine de caratères
     */
    private static String chaineCaracteres() {
        StringBuilder sb = new StringBuilder();
        char u = 'a';
        for (int i = 0; i < LONGUEUR; i++) {
            sb.append("     ").append(u);
            u++;
        }
        return sb.toString();
    }

    /**
     * Permet de retourner toutes les pièces mangées d'un joueur
     *
     * @param j le joueur
     * @return une chaine de caractères contenant les pieces mangées
     */
    private static String affichagePiecesMangees(IJoueur j) {
        StringBuilder sb = new StringBuilder();
        for (IPiece p : j.getPieces()) {
            if (p.isMangé()) {
                sb.append(p.toChar()).append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * Permet de :
     * --> déplacer une piece dans le tableau de pièces
     * --> changer les coordonnées de cette pièce
     * --> et si une piece est mangée, de la retirer de la liste listePieces
     *
     * @param coordIni les coordonnées de la pièce que l'on va déplacer
     * @param coordFin les coordonnes de destination de cette pièce
     */
    public void placerNouvelleCoord(Coord coordIni, Coord coordFin) {
        if (laPiece(coordFin) != null) {
            listePieces.remove(laPiece(coordFin));
            laPiece(coordFin).seFaitManger();
        }
        laPiece(coordIni).changeCoord(coordFin);
        echiquier[coordFin.getLigne()][coordFin.getColonne()] = laPiece(coordIni);
        echiquier[coordIni.getLigne()][coordIni.getColonne()] = null;
    }

    /**
     * Permet de savoir si un coup est possible en faisant passer ce dernier dans toutes les conditions
     *
     * @param caseSource la case de départ de la piece à déplacer
     * @param caseDest   la case de destination de la pièce à déplacer
     * @param courant    Le joueur qui propose le coup
     * @return vrai si le coup est possible
     */
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
                if (!piece.getCoord().compare(caseDest)
                        && !(piece.getCouleur().equals(p.getCouleur()))
                        && piece.peutJouer(caseDest, this)) {
                    echiquier[p.getCoord().getLigne()][p.getCoord().getColonne()] = p;
                    return false;
                }
            }
            echiquier[p.getCoord().getLigne()][p.getCoord().getColonne()] = p;
            return true;
        } else {
//      5- si le joueur courant est echec

            IPiece pieceSrc = laPiece(caseSource);
            IPiece pieceDst = laPiece(caseDest);
            echiquier[caseSource.getLigne()][caseSource.getColonne()] = null;
            echiquier[caseDest.getLigne()][caseDest.getColonne()] = pieceSrc;
            ArrayList<IPiece> listeCopie = new ArrayList<>(listePieces);
            if (pieceDst != null)
                listeCopie.remove(pieceDst);

            if (echec(courant, listeCopie)) {
                echiquier[caseSource.getLigne()][caseSource.getColonne()] = pieceSrc;
                echiquier[caseDest.getLigne()][caseDest.getColonne()] = pieceDst;

                if (pieceDst != null)
                    listeCopie.add(pieceDst);
                return false;
            }
            echiquier[caseSource.getLigne()][caseSource.getColonne()] = pieceSrc;
            echiquier[caseDest.getLigne()][caseDest.getColonne()] = pieceDst;
            if (pieceDst != null)
                listeCopie.add(pieceDst);
            return true;
        }

    }

    /**
     * Permet de savoir si un joueur est en echec et Mat ou non
     *
     * @param joueur le joueur
     * @return vrai si le joueur est echec et Mat
     */
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

    /**
     * Permet de savoir si un joueur est en échec et pat ou non
     *
     * @param joueur le joueur courant
     * @return vrai si le joueur est echec et Pat
     */
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

    /**
     * Permet de retourner une chaine de caractères définie en fonction de la valeur du paramètre index
     *
     * @param index    le choix de la chaine de caractères
     * @param nom      Le nom d'un joueur
     * @param autreNom Le nom d'un joueur
     * @return la chaine de caractere
     */
    public String partieFinie(int index, String nom, String autreNom) {
        switch (index) {
            case (1): {
                return "La partie est nulle : situation d'échecs et pat pour le joueur " + nom + ".";
            }
            case (2): {
                return "Le joueur " + nom + " est vaincu par échecs et mat. Le joueur " + autreNom + " a gagné.";
            }
            case (3): {
                return "La partie est nulle : il ne reste que 2 rois sur le plateau.";
            }
            default: {
                return "";
            }
        }
    }

    /**
     * Permet de savoir si la partie se conclut par un match nul
     *
     * @param blanc le joueur BLANC
     * @param noir  le joueur NOIR
     * @return vrai si la partie est finie sur un match nul
     */
    public boolean partieNulle(IJoueur blanc, IJoueur noir) {
        if (matchNul)
            return true;
        if (chesspat(blanc) || chesspat(noir))
            return true;
        return listePieces.size() == 2;
    }

    /**
     * Permet de savoir si un joueur est échec
     *
     * @param courant le joueur courant
     * @param list    une liste de pièces
     * @return vrai si le joueur est échec
     */
    public boolean echec(IJoueur courant, List<IPiece> list) {
        for (IPiece piece : list) {
            if (!(piece.getCouleur().equals(courant.leRoi().getCouleur())) && piece.peutJouer(courant.leRoi().getCoord(), this)) {
                return true;
            }
        }
        return false;
    }

    /**
     * permet de savoir si une piece mange une pièce alliée
     *
     * @param coordIni la coordonnées de départ de la pièce
     * @param coordFin la coordonnées de destination de la pièce
     * @return faux si une pièce mange son allié
     */
    private boolean coupValableSurPiece(Coord coordIni, Coord coordFin) {
        if (laPiece(coordFin) != null)
            return !(laPiece(coordIni).getCouleur().equals(laPiece(coordFin).getCouleur()));
        /*si le roi est en echec, il est obligé de déplacer son roi*/
        return true;
    }

    /**
     * Permet de retourner la pièce avec ses coordonées
     *
     * @param c les coordonnées de la pièces
     * @return la pièce
     */
    public IPiece laPiece(Coord c) {
        return echiquier[c.getLigne()][c.getColonne()];
    }

    /**
     * Permet de récupérer les coordonnées de la pièce
     *
     * @param x2 la lettre sur le plateau
     * @param y2 le chiffre sur le plateau
     * @return la position de la pièce sur l'échiquier
     */
    public Coord getCoord(char x2, int y2) {
        return new Coord(8 - y2, x2 - 'a');
    }

    /**
     * permet de savoir si le coup est valable sur le plateau
     *
     * @param coup le coup du joueur
     * @return vrai si le coup est valable dans le plateau
     */
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

    /**
     * permet de savoir si le joueur a fait une erreur de frappe dans son coup
     *
     * @param coup   le coup du joueur
     * @param joueur le joueur
     * @return vrai si le joueur a fait une faute et doit rejouer
     */
    public boolean doitRejouer(String coup, IJoueur joueur) {
        Coord coordIni;
        Coord coordFin;
        if (!coupValableSurPlateau(coup)) {
            return true;
        }
        char x = coup.charAt(0);
        char x2 = coup.charAt(2);/*b7b8*/
        int y = intoInt(coup, 1);
        int y2 = intoInt(coup, 3);
        coordIni = getCoord(x, y);
        coordFin = getCoord(x2, y2);
        if (!estJouable(coordIni, coordFin, joueur)) {
            return true;
        }

        return !laPiece(coordIni).getCouleur().equals(joueur.leRoi().getCouleur());
    }

    /**
     * permet de retourner en String l'echiquier contenant les pieces
     *
     * @param joueurBlanc le joueur BLANC
     * @param joueurNoir  le joueur NOIR
     * @return l'echiquier
     */
    public String affichePlateau(IJoueur joueurBlanc, IJoueur joueurNoir) {
        StringBuilder sb = new StringBuilder();
        sb.append(chaineCaracteres()).append("         Pièces mangées par le joueur ").append(joueurNoir.getNom()).append(" : ");
        sb.append(affichagePiecesMangees(joueurBlanc)).append(System.lineSeparator());
        for (int cmpHauteur = 0, cmp = 8; cmpHauteur < HAUTEUR; cmpHauteur++, cmp--) {
            sb.append("    ---   ---   ---   ---   ---   ---   ---   ---\n").append(cmp).append(" | ");
            for (int cmpLongueur = 0; cmpLongueur < LONGUEUR; cmpLongueur++) {
                sb.append(" ");
                if (laPiece(new Coord(cmpHauteur, cmpLongueur)) == null)
                    sb.append(" ");
                else {
                    sb.append(echiquier[cmpHauteur][cmpLongueur].toChar());
                }
                sb.append("  | ");
            }
            if (cmp == 5) {
                sb.append(cmp).append("    Si vous souhaitez abandonner veuillez écrire \"abandon\" à la place d'un coup\n");
            } else if (cmp == 4) {
                sb.append(cmp).append("    Pour la propostion de la nulle veuillez écrire \"nulle\" à la place d'un coup que vous souhaitez entrer\n");
            } else {
                sb.append(cmp).append("\n");
            }
        }
        sb.append("    ---   ---   ---   ---   ---   ---   ---   ---\n");
        sb.append(chaineCaracteres()).append("         Pièces mangées par le joueur ").append(joueurBlanc.getNom()).append(" : ");
        sb.append(affichagePiecesMangees(joueurNoir)).append(System.lineSeparator());
        return sb.toString();
    }


    public List<IPiece> getListePieces() {
        return listePieces;
    }

    public boolean getPropositionNulle() {
        return this.propositionNulle;
    }

    public void setPropositionNulle(boolean change) {
        this.propositionNulle = change;
    }

    public void setMAtchNul(boolean change) {
        this.matchNul = change;
    }
}
