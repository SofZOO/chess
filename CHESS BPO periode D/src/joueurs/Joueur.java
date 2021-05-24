package joueurs;

import echiquier.*;
import piece.CouleurPiece;

import java.util.ArrayList;


public abstract class Joueur implements IJoueur {
    private final String nom; // Le nom du Joueur
    private final ArrayList<IPiece> pieces;// La liste de pièce du joueur
    private boolean echecEtMat; // Boolean pour savoir si le joueur est echec et mat
    private CouleurPiece couleur;


    /**
     * Le constructeur de joueur
     *
     * @param blanc Boolean True si le joueur est le joueur blanc
     * @param fab   la fabrique de pieces du joueur
     */
    protected Joueur(boolean blanc, IFabriquePiece fab) {
        this.nom = blanc ? "BLANC" : "NOIR";
        this.couleur = blanc ? CouleurPiece.BLANC : CouleurPiece.NOIR;
        this.pieces = fab.fabrique(blanc);
        this.echecEtMat = false;
    }

    /**
     * Permet au joueur de déaplcer ses pièces, est appelée dans la méthode joueUnTour afin d'éxécuter un coup
     *
     * @param coup        le coup du joueur
     * @param autreJoueur le joueur adverse
     * @param p           le plateau de jeu
     */
    @Override
    public void déplacer(String coup, IJoueur autreJoueur, Plateau p) {
        Coord coordIni;
        Coord coordFin;
        char x = coup.charAt(0);
        char x2 = coup.charAt(2);
        int y = Plateau.intoInt(coup, 1);
        int y2 = Plateau.intoInt(coup, 3);
        coordIni = p.getCoord(x, y);
        coordFin = p.getCoord(x2, y2);

        p.placerNouvelleCoord(coordIni, coordFin);

        if (p.echec(autreJoueur, p.getListePieces())) {
            System.out.println("le joueur " + autreJoueur.getNom() + " est en position d'échec");
            if (p.chessmat(autreJoueur)) {
                autreJoueur.aPerdu();
            }
        }
    }

    /**
     * Permet de retourner la liste de piece du joueur
     *
     * @return la liste "pieces"
     */
    @Override
    public ArrayList<IPiece> getPieces() {
        return pieces;
    }

    /**
     * Permet de récupérer la pièce à l'indice 0 de la liste "pieces" " le roi a été initialisé dans la liste en premier pour le retrouver rapidement
     *
     * @return le Roi du joueur
     */
    @Override
    public IPiece leRoi() {
        return pieces.get(0);
    }

    /**
     * Permet de changer l'état du boolean "echecEtMat" en vrai, ainsi le joueur a perdu
     */
    @Override
    public void aPerdu() {
        this.echecEtMat = true;
    }

    /**
     * Permet de retourner le nom du joueur
     *
     * @return nom
     */
    @Override
    public String getNom() {
        return nom;
    }

    /**
     * Permet de savoir si le joueur est échec et mat
     *
     * @return la valeur de "echecEtMat"
     */
    public boolean isChessMat() {
        return this.echecEtMat;
    }

    /**
     * Permet de retourner la couleur du joueur
     *
     * @return la valeur de l'enum couleur
     */
    @Override
    public CouleurPiece getCouleur() {
        return couleur;
    }
}
