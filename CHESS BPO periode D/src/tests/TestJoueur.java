package tests;

import echiquier.IJoueur;
import joueurs.Bot;
import joueurs.Humain;
import org.junit.Test;
import piece.FabriquePiece;

import static org.junit.Assert.*;

public class TestJoueur {

    /**
     * On vérifie si le joueur Humain est bien un humain et si le bot n'en est pas un.
     */
    @Test
    public void vérificationHumain(){
        IJoueur jBlanc = new Humain(true,new FabriquePiece());
        IJoueur jNoir = new Bot(false, new FabriquePiece());
        // le joueur humain est un humain
        assertTrue(jBlanc.estHumain());
        // le bot n'est pas un humain
        assertFalse(jNoir.estHumain());
    }

    @Test
    public void vérificationDéfaiteJoueur(){
        IJoueur jBlanc = new Humain(true,new FabriquePiece());
        jBlanc.aPerdu();
        // on vérifie si le joueur est bien échec s'il a perdu. Ici c'est le cas puisque c'est son seul moyen de perdre
        // en sachant qu'il n'y a pas de chronomètre pour les joueurs
        assertTrue(jBlanc.isChessMat());
    }

    @Test
    public void dd(){

    }
}
