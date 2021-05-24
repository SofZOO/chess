package tests;

import appli.Application;
import echiquier.Coord;
import echiquier.IJoueur;
import echiquier.IPiece;
import echiquier.Plateau;
import joueurs.Bot;
import joueurs.Humain;
import org.junit.Test;
import piece.FabriquePiece;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestJoueur {

    /**
     * Test du déplacement d'un joueur
     */
    @Test
    public void vérificationMéthodeDéplacer() {
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false, new FabriquePiece());
        Plateau p = new Plateau(jBlanc, jNoir);

        //On verifie que l'emplacement ou se trouve la tour T en a1 n'est pas vide dans le tableau
        assertTrue(p.laPiece(new Coord(7, 0)) != null);
        // On vérifie que la case juste au dessus de ce tour est vide
        assertTrue(p.laPiece(new Coord(6, 0)) == null);

        //On déplace la tour sur la case du haut
        jBlanc.déplacer("a1a2", jNoir, p);

        // On verifie si le deplacement a bien eu lieu
        assertTrue(p.laPiece(new Coord(7, 0)) == null);
        assertTrue(p.laPiece(new Coord(6, 0)) != null);
    }

    /**
     * On vérifie si le joueur Humain est bien un humain et si le bot n'en est pas un.
     */
    @Test
    public void vérificationHumain() {
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Bot(false, new FabriquePiece());
        // le joueur humain est un humain
        assertTrue(jBlanc.estHumain());
        // le bot n'est pas un humain
        assertFalse(jNoir.estHumain());
    }

    /**
     * Test de la défaite d'un joueur
     */
    @Test
    public void vérificationDéfaiteJoueur() {
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        jBlanc.aPerdu();
        // on vérifie si le joueur est bien échec s'il a perdu. Ici c'est le cas puisque c'est son seul moyen de perdre
        // en sachant qu'il n'y a pas de chronomètre pour les joueurs
        assertTrue(jBlanc.isChessMat());
    }

    /*------------------------------------------------------- TEST BOT --------------------------------------------------------*/

    /**
     * On test si le bot joue vraiment sur le plateau
     */
    @Test
    public void vérificationMéthodeJoueUnTourDuBot() {
        Application.setChoixPartieFinale(true);
        IJoueur jBlanc = new Bot(true, new FabriquePiece());
        IJoueur jNoir = new Bot(false, new FabriquePiece());
        Plateau p = new Plateau(jBlanc, jNoir);

        IPiece[][] echiquier = new IPiece[p.getEchiquier().length][p.getEchiquier().length];

        for (int cmp1 = 0; cmp1 < p.getEchiquier().length; cmp1++) {
            for (int cmp2 = 0; cmp2 < p.getEchiquier().length; cmp2++) {
                echiquier[cmp1][cmp2] = p.laPiece(new Coord(cmp1, cmp2));
            }
        }

        // on vérifie si les 2 échiquiers sont bien les mêmes avant que le bot joue un tour
        assertTrue(Arrays.deepEquals(echiquier, p.getEchiquier()));

        jBlanc.joueUnTour(jNoir, p);

        // après avoir fait son coup, l'échiquier ne devrait pas être le même, donc les 2 échiquiers doivent être différents.
        assertFalse(Arrays.deepEquals(echiquier, p.getEchiquier()));

    }


}
