package fr.uvsq.cprog.dns;

/**
 * Commande pour ajouter une nouvelle entrée dans la base DNS.
 */
public class CommandeAjout implements Commande {
    private final Dns dns;
    private final AdresseIP adresse;
    private final NomMachine nom;

    public CommandeAjout(Dns dns, AdresseIP adresse, NomMachine nom) {
        this.dns = dns;
        this.adresse = adresse;
        this.nom = nom;
    }

    @Override
    public String execute() {
        try {
            dns.addItem(adresse, nom);
            return "Ajout réussi : " + adresse + " → " + nom;
        } catch (IllegalArgumentException e) {
            return "ERREUR : " + e.getMessage();
        } catch (java.io.IOException e) {
            return "Erreur d'accès au fichier : " + e.getMessage();
        }
    }
}
