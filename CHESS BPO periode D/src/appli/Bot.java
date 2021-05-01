package appli;
import echiquier.IFabriquePiece;
import echiquier.IJoueur;
import echiquier.Plateau;

import java.util.Random;

public class Bot extends Joueur{

    public Bot(String nom, boolean blanc, IFabriquePiece fab) {
        super(nom, blanc, fab);
    }

    public int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb = borneInf+random.nextInt(borneSup-borneInf);
        return nb;
    }

    @Override
    public void joue(IJoueur autreJoueur, Plateau p){
        Random r = new Random();

        char char1, char2;
        int num1,num2;

        while(true){
            StringBuilder coupBot = new StringBuilder();
            char1 = (char)(r.nextInt(8)+97);
            num1 = genererInt(1,8);
            char2 = (char)(r.nextInt(8)+97);
            num2 = genererInt(1,8);
            coupBot.append(char1).append(num1).append(char2).append(num2);
            System.out.println(coupBot);
            if(p.estJouable(p.getCoord(char1,num1),p.getCoord(char2,num2),this)){
                if(p.laCase(p.getCoord(char1,num1)).getPieceActuelle().compareCouleur(leRoi())){
                    déplacer(coupBot.toString(), autreJoueur , p);
                                    break;
                }
            }
        }
    }

}
