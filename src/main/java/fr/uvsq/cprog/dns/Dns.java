package fr.uvsq.cprog.dns;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principale pour gérer les enregistrements DNS.
 * Elle permet de charger, rechercher et ajouter des correspondances
 * entre adresses IP et noms de machines.
 */
public class Dns {
    private final List<DnsItem> items;

    /**
     * Construit un objet Dns à partir d'un fichier texte contenant des paires IP et nom.
     *
     * @param cheminFichier le chemin vers le fichier dns.txt
     */
    public Dns(String cheminFichier) {
        this.items = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                ligne = ligne.trim();
                if (ligne.isEmpty()) continue; // ignorer les lignes vides
                String[] parts = ligne.split("\\s+");
                if (parts.length != 2) {
                    System.err.println("Ligne ignorée (format invalide) : " + ligne);
                    continue;
                }

                // ✅ ordre corrigé : d’abord l’adresse IP, ensuite le nom de machine
                AdresseIP adresse = new AdresseIP(parts[0]);
                NomMachine nom = new NomMachine(parts[1]);
                DnsItem item = new DnsItem(adresse, nom);
                items.add(item);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier DNS : " + e.getMessage());
        }
    }

    /**
     * Ajoute une nouvelle correspondance adresse IP / nom de machine.
     *
     * @param adresse l'adresse IP
     * @param nom le nom de machine
     */
    public void addItem(AdresseIP adresse, NomMachine nom) {
        for (DnsItem item : items) {
            if (item.getAdresse().equals(adresse)) {
                throw new IllegalArgumentException("L’adresse IP existe déjà !");
            }
        }
        items.add(new DnsItem(adresse, nom));
    }

    /**
     * Retourne l'élément associé à un nom de machine.
     *
     * @param nom le nom de machine
     * @return l'objet DnsItem correspondant ou null si inexistant
     */
    public DnsItem getItem(NomMachine nom) {
        for (DnsItem item : items) {
            if (item.getNom().equals(nom)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Retourne l'élément associé à une adresse IP.
     *
     * @param adresse l'adresse IP
     * @return l'objet DnsItem correspondant ou null si inexistant
     */
    public DnsItem getItem(AdresseIP adresse) {
        for (DnsItem item : items) {
            if (item.getAdresse().equals(adresse)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Retourne les enregistrements filtrés par domaine.
     *
     * @param domaine le domaine à filtrer (ex: "uvsq.fr")
     * @param parAdresse si true, trie par adresse IP
     * @return liste filtrée d'éléments DNS
     */
    public List<DnsItem> getItems(String domaine, boolean parAdresse) {
        List<DnsItem> resultat = new ArrayList<>();
        for (DnsItem item : items) {
            if (item.getNom().getDomaine().equals(domaine)) {
                resultat.add(item);
            }
        }
        return resultat;
    }

    /**
     * Retourne tous les enregistrements DNS.
     *
     * @return la liste des enregistrements
     */
    public List<DnsItem> getAllItems() {
        return items;
    }
}
