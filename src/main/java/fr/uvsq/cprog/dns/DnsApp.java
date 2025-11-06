package fr.uvsq.cprog.dns;

import java.io.IOException;

/**
 * Classe principale de l'application DNS.
 */
public class DnsApp {

    public static void main(String[] args) {
        try {
            // Chemin du fichier contenant la base de données DNS
            String fichierDns = "dns.txt";

            // Chargement de la base DNS
            Dns dns = new Dns(fichierDns);

            // Interface utilisateur
            DnsTUI tui = new DnsTUI(dns);

            System.out.println("=== Simulation DNS ===");
            System.out.println("Commandes disponibles :");
            System.out.println("  [nom.qualifie] → affiche l'adresse IP");
            System.out.println("  [adr.es.se.ip] → affiche le nom de la machine");
            System.out.println("  ls [-a] domaine → liste les machines du domaine");
            System.out.println("  add ip nom → ajoute une machine");
            System.out.println("  quit → quitte le programme");
            System.out.println("=======================");

            // Boucle principale
            while (true) {
                Commande cmd = tui.nextCommande();
                if (cmd != null) {
                    String resultat = cmd.execute();
                    if (resultat != null && !resultat.isBlank()) {
                        tui.affiche(resultat);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la base DNS : " + e.getMessage());
        }
    }
}
