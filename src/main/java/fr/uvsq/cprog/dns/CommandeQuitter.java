package fr.uvsq.cprog.dns;

/**
 * Commande pour quitter l'application.
 */
public class CommandeQuitter implements Commande {
    @Override
    public String execute() {
        System.out.println("Au revoir !");
        System.exit(0);
        return "Fin du programme";
    }
}
