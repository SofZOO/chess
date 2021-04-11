package appli;

import échiquier.Plateau;

public class Main {

    public static void main(String[] args) {
        Plateau p = new Plateau();
        p.initialiserEchiquier();
        System.out.println(p);

        p.déplacer("e1e2");

        System.out.println(p);




    }
}
