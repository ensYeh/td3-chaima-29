package fr.uvsq.cprog.dns;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Classe principale représentant la base de données DNS.
 */
public class Dns {
    private final List<DnsItem> items;
    private final Path cheminFichier;

    /**
     * Constructeur qui charge la base depuis un fichier texte.
     *
     * @param cheminFichier le chemin du fichier de base DNS
     * @throws IOException si le fichier ne peut pas être lu
     */
    public Dns(String cheminFichier) throws IOException {
        this.cheminFichier = Path.of(Objects.requireNonNull(cheminFichier));
        this.items = new ArrayList<>();

        if (Files.exists(this.cheminFichier)) {
            List<String> lignes = Files.readAllLines(this.cheminFichier);
            for (String ligne : lignes) {
                String[] parties = ligne.trim().split("\\s+");
                if (parties.length == 2) {
                    NomMachine nom = new NomMachine(parties[0]);
                    AdresseIP adr = new AdresseIP(parties[1]);
                    items.add(new DnsItem(adr, nom));
                }
            }
        }
    }

    /** Recherche par adresse IP. */
    public DnsItem getItem(AdresseIP adr) {
        for (DnsItem item : items) {
            if (item.getAdresse().equals(adr)) {
                return item;
            }
        }
        return null;
    }

    /** Recherche par nom de machine. */
    public DnsItem getItem(NomMachine nom) {
        for (DnsItem item : items) {
            if (item.getNom().equals(nom)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Retourne la liste des entrées DNS correspondant à un domaine donné,
     * triée selon le mode demandé.
     *
     * @param domaine domaine à rechercher
     * @param parAdresse true si tri par adresse IP, false si tri par nom de machine
     * @return liste triée d’items
     */
    public List<DnsItem> getItems(String domaine, boolean parAdresse) {
        Comparator<DnsItem> comparateur = parAdresse
                ? Comparator.comparing(i -> i.getAdresse().toString())
                : Comparator.comparing(i -> i.getNom().getMachine());

        return items.stream()
                .filter(i -> i.getNom().getDomaine().equals(domaine))
                .sorted(comparateur)
                .collect(Collectors.toList());
    }

    /**
     * Ajoute une nouvelle entrée au DNS et met à jour le fichier.
     */
    public void addItem(AdresseIP adr, NomMachine nom) throws IOException {
        if (getItem(adr) != null) {
            throw new IllegalArgumentException("L’adresse IP existe déjà !");
        }
        if (getItem(nom) != null) {
            throw new IllegalArgumentException("Le nom de machine existe déjà !");
        }
        DnsItem item = new DnsItem(adr, nom);
        items.add(item);
        save();
    }

    /** Sauvegarde la base DNS dans le fichier texte. */
    private void save() throws IOException {
        List<String> lignes = items.stream()
                .map(i -> i.getNom().toString() + " " + i.getAdresse().toString())
                .collect(Collectors.toList());
        Files.write(cheminFichier, lignes);
    }

    public List<DnsItem> getAllItems() {
        return Collections.unmodifiableList(items);
    }
}
