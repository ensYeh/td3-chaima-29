package fr.uvsq.cprog.dns;

import java.util.Objects;

/**
 * Classe représentant une adresse IP.
 */
public class AdresseIP {
    private final String ip;

    public AdresseIP(String ip) {
        if (!estValide(ip)) {
            throw new IllegalArgumentException("Adresse IP invalide : " + ip);
        }
        this.ip = ip;
    }

    private boolean estValide(String ip) {
        String[] parties = ip.split("\\.");
        if (parties.length != 4) return false;
        try {
            for (String part : parties) {
                int n = Integer.parseInt(part);
                if (n < 0 || n > 255) return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdresseIP)) return false;
        AdresseIP that = (AdresseIP) o;
        return Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }

    @Override
    public String toString() {
        return ip;
    }
}
