package fr.uvsq.cprog.dns;

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
        String msg;
        if (item != null) {
            msg = item.getNom().toString();
        } else {
            msg = "Adresse IP inconnue : " + adresse;
        }
        System.out.println(msg);
        return msg;
    }
}
