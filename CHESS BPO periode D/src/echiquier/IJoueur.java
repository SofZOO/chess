package echiquier;

import java.util.ArrayList;

public interface IJoueur {

    /**
     * Permet de retourner la liste de pièces du joueur
     *
     * @return la liste de pièces
     */
    ArrayList<IPiece> getPieces();

    /**
     * Permet à un joueur de déplacer ses pièces
     *
     * @param coup        le coup du joueur
     * @param autreJoueur le joueur adverse
     * @param p           le plateau de jeu
     */
    void déplacer(String coup, IJoueur autreJoueur, Plateau p);

    /**
     * Permet au joueur de jouer son tour
     *
     * @param autreJoueur le joueur adverse
     * @param p           le plateau de jeu
     */
    void joueUnTour(IJoueur autreJoueur, Plateau p);

    /**
     * Permet de récupérer la pièce à l'indice 0 de la liste "pièces" " le roi a été initialisé dans la liste en premier pour le retrouver rapidement
     *
     * @return le Roi du joueur
     */
    IPiece leRoi();

    /**
     * Permet de changer l'état du boolean "echecEtMat" à True, ainsi le joueur a perdu
     */
    void aPerdu();

    /**
     * Permet de retourner le nom du joueur
     *
     * @return nom
     */
    String getNom();

    /**
     * Permet de savoir si le joueur est échec et mat
     *
     * @return la valeur de "echecEtMat"
     */
    boolean isChessMat();

    /**
     * Permet de savoir si le IJoueur est un humain ou un bot
     *
     * @return True ou false
     */
    boolean estHumain();

    /**
     * Permet de savoir si le joueur abandonne ou non
     *
     * @return la valeur du boolean abandonne
     */
    boolean abandonne();

}
