package tests;

import appli.Application;
import echiquier.Coord;
import echiquier.IJoueur;
import echiquier.IPiece;
import echiquier.Plateau;
import joueurs.Humain;
import org.junit.Test;
import piece.FabriquePiece;
import static org.junit.Assert.*;

public class TestPlateau {


    /**
     * On test ici le nombre de cases que contient notre échiquier.
     */
    @Test
    public void vérificationNombreCases() {
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false, new FabriquePiece());
        Plateau p = new Plateau(jBlanc, jNoir);
        IPiece[][] echiquier = p.getEchiquier().clone();
        int nbCases = 0;
        for (IPiece[] iPieces : echiquier) {
            for (int j = 0; j < iPieces.length; j++) {
                nbCases++;
            }
        }
        assertEquals(64, nbCases);
    }


    /**
     * On test la situation d'échecs et pat
     */
    @Test
    public void vérificationMéthodeEchecPat() {
        Application.setChoixPartieFinale(true);
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false, new FabriquePiece());
        Plateau p = new Plateau(jBlanc, jNoir);

        jNoir.déplacer("e8f8", jBlanc, p);
        jNoir.déplacer("f8g8", jBlanc, p);
        jNoir.déplacer("g8h8", jBlanc, p);

        jBlanc.déplacer("e6f6", jNoir, p);
        jBlanc.déplacer("f6g6", jNoir, p);
        jBlanc.déplacer("g6h6", jNoir, p);

        jBlanc.déplacer("c7g7", jNoir, p);

        // on imagine une finale ou le roi noir a réussi à faire un échec et pat
        //     d     e     f     g     h
        //    ---   ---   ---   ---   ---
        // 8 |     |     |     |     |  r  |
        //     ---   ---   ---   ---   ---
        // 7 |     |     |     |  T  |     |
        //     ---   ---   ---   ---   ---
        // 6 |     |     |     |     |  R  |
        //     ---   ---   ---   ---   ---

        assertTrue(p.partieNulle(jBlanc, jNoir));
    }

    /**
     * Méthode qui permet de compter le nombre de pièces d'un échiquier
     *
     * @param tabPièces l'échiquier
     * @return le nombre de pièces
     */
    private int nbPièces(IPiece[][] tabPièces) {
        int nbPièces = 0;
        for (IPiece[] tabPiece : tabPièces) {
            for (IPiece piece : tabPiece) {
                if (piece != null)
                    nbPièces++;
            }
        }
        return nbPièces;
    }

    /**
     * On vérifie le nombre de pièces sur l'échiquier
     */
    @Test
    public void vérificationNombrePièces() {
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false, new FabriquePiece());
        Plateau p = new Plateau(jBlanc, jNoir);
        IPiece[][] echiquier = p.getEchiquier().clone();
        // on veut savoir combien on a de pièces sur une partie normale, on compte donc le nombre de pièces sur l'échiquier
        assertEquals(14, nbPièces(echiquier));
        // on vérifie qu'on a le même nombre dans la liste de pièces
        assertEquals(14, p.getListePieces().size());
        // on imagine la situation de la finale
        Application.setChoixPartieFinale(true);
        jBlanc = new Humain(true, new FabriquePiece());
        jNoir = new Humain(false, new FabriquePiece());
        Plateau plat = new Plateau(jBlanc, jNoir);
        echiquier = plat.getEchiquier().clone();
        // on veut savoir combien on a de pièces sur une partie de finale, on compte donc le nombre de pièces sur l'échiquier
        assertEquals(3, nbPièces(echiquier));
        // on vérifie qu'on a le même nombre dans la liste de pièces
        assertEquals(3, plat.getListePieces().size());

    }


    /**
     * On
     */
    @Test
    public void testDéplacementPièce() {
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false, new FabriquePiece());
        Plateau p = new Plateau(jBlanc, jNoir);

        // Je fais un dessin pour illustrer la situation
        //
        // 4 |     |     |
        //     ---   ---   ---
        // 3 |     |     |
        //     ---   ---   ---
        // 2 |     |     |
        //     ---   ---   ---
        // 1 |  T  |     |
        //     ---   ---   ---
        //      a     b     c
        // On a la tour en a1 et on va tester ici de changer ses coordonnées

        IPiece pi = p.laPiece(new Coord(7, 0));

        assertTrue(pi.getCoord().compare(new Coord(7, 0)));

        assertEquals(pi.getCoord().toString(), new Coord(7, 0).toString());

        // on effectue le coup a1a4
        p.placerNouvelleCoord(pi.getCoord(), new Coord(4, 0));

        //
        // 4 |  T  |     |
        //     ---   ---   ---
        // 3 |     |     |
        //     ---   ---   ---
        // 2 |     |     |
        //     ---   ---   ---
        // 1 |     |     |
        //     ---   ---   ---
        //      a     b     c

        assertEquals(pi.getCoord().toString(), new Coord(4, 0).toString());
        assertTrue(pi.getCoord().compare(new Coord(4, 0)));

    }

    /**
     * On vérifie si notre conversion de string vers entier fonctionne
     */
    @Test
    public void vérificationMethodeConversion() {
        String coup = "a8a4";
        assertEquals(Plateau.intoInt(coup, 1), 8);
        assertEquals(Plateau.intoInt(coup, 3), 4);
    }

    /**
     * On vérifie si un coup est jouable ou non
     */
    @Test
    public void vérificationMéthodeEstJouable() {
        Application.setChoixPartieFinale(false);
        //		1- La destination est libre ou est occupée par une pièce adverse
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false, new FabriquePiece());
        Plateau p = new Plateau(jBlanc, jNoir);


        jNoir.déplacer("a8a3", jBlanc, p);

        // Modelisation du plateau
        //
        // 4 |     |     |
        //     ---   ---   ---
        // 3 |  t  |     |
        //     ---   ---   ---
        // 2 |     |     |
        //     ---   ---   ---
        // 1 |  T  |  C  |  F
        //     ---   ---   ---
        //      a     b     c


        // on test le déplacement de la tour T sur la tour t (a1a3) --> coup possible
        assertTrue(p.estJouable(new Coord(7, 0), new Coord(5, 0), jBlanc));

        // on test le déplacement de la tour T sur le cavalier C (a1b1) --> coup impossible car pièces de meme couleur
        assertFalse(p.estJouable(new Coord(7, 0), new Coord(7, 1), jBlanc));

        jNoir.déplacer("a3d3", jBlanc, p);

        // ReModelisation du plateau
        //
        // 4 |     |     |     |     |     |
        //     ---   ---   ---   ---   ---
        // 3 |     |  t  |     |     |     |
        //     ---   ---   ---   ---   ---
        // 2 |     |     |     |     |     |
        //     ---   ---   ---   ---   ---
        // 1 |  F  |     |  R  |  F  |  T  |
        //     ---   ---   ---   ---   ---
        //      c     d     e     f     g


        // on test le déplacement du roi R sur la case du haut (e1e2) --> coup possible
        assertTrue(p.estJouable(new Coord(7, 4), new Coord(6, 4), jBlanc));

        // on test le déplacement du roi R sur la case de gauche (e1d1) --> coup impossible car la tour t menace la case
        assertFalse(p.estJouable(new Coord(7, 4), new Coord(7, 3), jBlanc));


        //      4- si le joueur courant est echec

        jBlanc.déplacer("f1e2", jNoir, p);
        jNoir.déplacer("d3e3", jBlanc, p);

        // ReModelisation du plateau
        //
        // 4 |     |     |     |     |     |
        //     ---   ---   ---   ---   ---
        // 3 |     |     |  t  |     |     |
        //     ---   ---   ---   ---   ---
        // 2 |     |     |  F  |     |     |
        //     ---   ---   ---   ---   ---
        // 1 |  F  |     |  R  |     |  T  |
        //     ---   ---   ---   ---   ---
        //      c     d     e     f     g


        // on test le déplacement du fou F sur la case d3 (e2d3) --> coup impossible car la tour menacerait le Roi R
        assertFalse(p.estJouable(new Coord(6, 4), new Coord(5, 3), jBlanc));

    }

    /**
     * On vérifie la situation d'échecs et mat
     */
    @Test
    public void vérificationMéthodeEchecMat() {
        Application.setChoixPartieFinale(true);
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false, new FabriquePiece());
        Plateau p = new Plateau(jBlanc, jNoir);

        // on imagine une finale ainsi avec la tour en c7 en sachant que c'est le tour du joueur BLANC
        //     c     d     e     f     g
        //    ---   ---   ---   ---   ---
        // 8 |     |     |  r  |     |     |
        //     ---   ---   ---   ---   ---
        // 7 |  T  |     |     |     |     |
        //     ---   ---   ---   ---   ---
        // 6 |     |     |  R  |     |     |
        //     ---   ---   ---   ---   ---
        // s'il fait le coup c7c8, le joueur adverse est en situation d'échecs et mat et ne peut plus rien faire car son seul
        // matériel est son Roi

        jBlanc.déplacer("c7c8", jNoir, p);
        assertTrue(p.chessmat(jNoir));
    }

    /**
     * On vérifie si le joueur s'est trompé sur son coup.
     */
    @Test
    public void vérificationMéthodeDoitRejouer() {

        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false, new FabriquePiece());
        Plateau p = new Plateau(jBlanc, jNoir);

        // on test un coup hors du plateau
        assertTrue(p.doitRejouer("a1a9", jBlanc));

        // on test une chaine de caractere erronée
        assertTrue(p.doitRejouer("ifzjfipzijz", jBlanc));

        // on test un coup du joueur noir avec une piece du joueur blanc
        assertTrue(p.doitRejouer("a1a2", jNoir));

    }

}




