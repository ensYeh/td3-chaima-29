package fr.uvsq.cprog.dns;

public class CommandeAjout implements Commande {
    private final Dns dns;
    private final AdresseIP adresse;
    private final NomMachine nom;

    public CommandeAjout(Dns dns, AdresseIP adresse, NomMachine nom) {
        this.dns = dns;
        this.adresse = adresse;
        this.nom = nom;
    }

    @Override
    public String execute() {
        try {
            dns.addItem(adresse, nom);
            String msg = "Ajout réussi : " + adresse + " → " + nom;
            System.out.println(msg);
            return msg;
        } catch (IllegalArgumentException e) {
            String msg = "Erreur : " + e.getMessage();
            System.out.println(msg);
            return msg;
        }
    }
}
