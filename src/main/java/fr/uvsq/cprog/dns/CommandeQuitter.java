package fr.uvsq.cprog.dns;

public class CommandeQuitter implements Commande {
    @Override
    public String execute() {
        String msg = "Au revoir !";
        System.out.println(msg);
        System.exit(0);
        return msg;
    }
}
