package fr.uvsq.cprog.dns;

/**
 * Interface représentant une commande DNS.
 */
public interface Commande {
    /**
     * Exécute la commande et retourne un texte à afficher.
     *
     * @return le résultat de la commande
     */
    String execute();
}
