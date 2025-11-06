package fr.uvsq.cprog.dns;

import java.util.Scanner;

/**
 * Classe gérant les interactions avec l'utilisateur (interface texte).
 */
public class DnsTUI {
    private final Scanner scanner;
    private final Dns dns;

    /**
     * Constructeur.
     * @param dns l'objet DNS gérant la base de données
     */
    public DnsTUI(Dns dns) {
        this.dns = dns;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Lit la prochaine commande utilisateur et retourne l'objet Commande correspondant.
     */
    public Commande nextCommande() {
        System.out.print("> ");
        String ligne = scanner.nextLine().trim();
        if (ligne.equalsIgnoreCase("quit")) {
            return new CommandeQuitter();
        }

        // Commande d’ajout
        if (ligne.startsWith("add ")) {
            String[] p = ligne.split("\\s+");
            if (p.length == 3) {
                return new CommandeAjout(dns, new AdresseIP(p[1]), new NomMachine(p[2]));
            }
            System.out.println("Syntaxe : add adr.es.se.ip nom.qualifie");
            return null;
        }

        // Commande de liste
        if (ligne.startsWith("ls")) {
            boolean parAdresse = ligne.contains("-a");
            String[] p = ligne.split("\\s+");
            String domaine = p[p.length - 1];
            return new CommandeListeDomaine(dns, domaine, parAdresse);
        }

        // Recherche par IP ou par nom
        if (ligne.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
            return new CommandeRechercheNom(dns, new AdresseIP(ligne));
        } else if (ligne.contains(".")) {
            return new CommandeRechercheIP(dns, new NomMachine(ligne));
        }

        System.out.println("Commande inconnue !");
        return null;
    }

    /**
     * Affiche un message à l'utilisateur.
     */
    public void affiche(String message) {
        System.out.println(message);
    }
}
