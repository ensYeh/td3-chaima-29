package fr.uvsq.cprog.dns;

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
        String msg;
        if (item != null) {
            msg = item.getAdresse().toString();
        } else {
            msg = "Nom de machine inconnu : " + nom;
        }
        System.out.println(msg);
        return msg;
    }
}

