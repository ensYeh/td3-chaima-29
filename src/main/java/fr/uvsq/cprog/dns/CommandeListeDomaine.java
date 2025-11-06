package fr.uvsq.cprog.dns;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Commande pour lister les machines d’un domaine.
 */
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
            return "Aucune machine trouvée pour le domaine " + domaine;
        }
        return items.stream()
                .map(DnsItem::toString)
                .collect(Collectors.joining("\n"));
    }
}
