package fr.uvsq.cprog.dns;

import java.util.List;

public class CommandeListeDomaine implements Commande {
    private final Dns dns;
    private final String domaine;
    private final boolean parAdresse;

    public CommandeListeDomaine(Dns dns, String domaine, boolean parAdresse) {
        this.dns = dns;
        this.domaine = domaine;
        this.parAdresse = parAdresse;
    }

    @Override
    public String execute() {
        List<DnsItem> items = dns.getItems(domaine, parAdresse);
        if (items.isEmpty()) {
            String msg = "Aucune machine trouvée pour le domaine : " + domaine;
            System.out.println(msg);
            return msg;
        } else {
            StringBuilder sb = new StringBuilder();
            for (DnsItem item : items) {
                sb.append(item.getAdresse()).append(" ").append(item.getNom()).append("\n");
            }
            String result = sb.toString().trim();
            System.out.println(result);
            return result;
        }
    }
}
