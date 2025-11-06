package fr.uvsq.cprog.dns;

import java.util.Scanner;

/**
 * Classe principale de l'application DNS.
 */
public class DnsApp {

    public static void main(String[] args) {
        Dns dns = new Dns("dns.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.println("=======================");
        System.out.println("     DNS Console");
        System.out.println("=======================");

        while (true) {
            System.out.print("> ");
            String ligne = scanner.nextLine().trim();

            if (ligne.isEmpty()) {
                System.out.println("Commande inconnue !");
                continue;
            }

            // Commande pour quitter
            if (ligne.equalsIgnoreCase("quit") || ligne.equalsIgnoreCase("exit")) {
                new CommandeQuitter().execute();
            }

            // Commande pour ajouter une machine
            if (ligne.startsWith("add ")) {
                String[] parties = ligne.split("\\s+");
                if (parties.length != 3) {
                    System.out.println("Usage : add <adresseIP> <nomMachine>");
                    continue;
                }
                try {
                    AdresseIP ip = new AdresseIP(parties[1]);
                    NomMachine nom = new NomMachine(parties[2]);
                    new CommandeAjout(dns, ip, nom).execute();
                } catch (IllegalArgumentException e) {
                    System.out.println("Erreur : " + e.getMessage());
                }
                continue;
            }

            // Commande pour lister les domaines
            if (ligne.startsWith("ls ")) {
                String[] parties = ligne.split("\\s+");
                if (parties.length != 3) {
                    System.out.println("Usage : ls <-a|-n> <domaine>");
                    continue;
                }
                boolean parAdresse = parties[1].equals("-a");
                new CommandeListeDomaine(dns, parties[2], parAdresse).execute();
                continue;
            }

            // Commande de recherche
            try {
                if (ligne.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
                    // Si c’est une adresse IP
                    AdresseIP ip = new AdresseIP(ligne);
                    new CommandeRechercheNom(dns, ip).execute();
                } else {
                    // Sinon c’est un nom de machine
                    NomMachine nom = new NomMachine(ligne);
                    new CommandeRechercheIP(dns, nom).execute();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Commande inconnue !");
            }
        }
    }
}

