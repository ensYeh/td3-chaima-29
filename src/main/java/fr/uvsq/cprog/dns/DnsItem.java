package fr.uvsq.cprog.dns;

import java.util.Objects;

/**
 * Classe représentant une entrée dans le DNS.
 * Elle associe un nom de machine à une adresse IP.
 */
public class DnsItem {
    private final AdresseIP adresse;
    private final NomMachine nom;

    public DnsItem(AdresseIP adresse, NomMachine nom) {
        this.adresse = Objects.requireNonNull(adresse, "Adresse IP ne peut pas être nulle");
        this.nom = Objects.requireNonNull(nom, "Nom de machine ne peut pas être nul");
    }

    public AdresseIP getAdresse() {
        return adresse;
    }

    public NomMachine getNom() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DnsItem)) return false;
        DnsItem item = (DnsItem) o;
        return Objects.equals(adresse, item.adresse) && Objects.equals(nom, item.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresse, nom);
    }

    @Override
    public String toString() {
        return adresse + " " + nom;
    }
}
