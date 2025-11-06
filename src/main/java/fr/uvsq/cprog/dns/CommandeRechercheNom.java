package fr.uvsq.cprog.dns;

/**
 * Commande pour rechercher un nom à partir d’une adresse IP.
 */
public class CommandeRechercheNom implements Commande {
    private final Dns dns;
    private final AdresseIP adresse;

    public CommandeRechercheNom(Dns dns, AdresseIP adresse) {
        this.dns = dns;
        this.adresse = adresse;
    }

    @Override
    public String execute() {
        DnsItem item = dns.getItem(adresse);
        return (item != null) ? item.getNom().toString()
                : "Adresse IP inconnue : " + adresse;
    }
}
