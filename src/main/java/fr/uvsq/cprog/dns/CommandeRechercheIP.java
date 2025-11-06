package fr.uvsq.cprog.dns;

/**
 * Commande pour rechercher une adresse IP à partir d’un nom de machine.
 */
public class CommandeRechercheIP implements Commande {
    private final Dns dns;
    private final NomMachine nom;

    public CommandeRechercheIP(Dns dns, NomMachine nom) {
        this.dns = dns;
        this.nom = nom;
    }

    @Override
    public String execute() {
        DnsItem item = dns.getItem(nom);
        return (item != null) ? item.getAdresse().toString()
                : "Nom de machine inconnu : " + nom;
    }
}
