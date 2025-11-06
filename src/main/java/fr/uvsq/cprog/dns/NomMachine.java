package fr.uvsq.cprog.dns;

import java.util.Objects;

/**
 * Classe représentant un nom de machine qualifié.
 */
public class NomMachine {
    private final String nomComplet;
    private final String machine;
    private final String domaine;

    public NomMachine(String nomComplet) {
        if (!estValide(nomComplet)) {
            throw new IllegalArgumentException("Nom de machine invalide : " + nomComplet);
        }
        this.nomComplet = nomComplet;
        String[] parties = nomComplet.split("\\.", 2);
        this.machine = parties[0];
        this.domaine = parties[1];
    }

    private boolean estValide(String nom) {
        return nom != null && nom.contains(".") && nom.indexOf('.') != 0 && nom.indexOf('.') != nom.length() - 1;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public String getMachine() {
        return machine;
    }

    public String getDomaine() {
        return domaine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NomMachine)) return false;
        NomMachine that = (NomMachine) o;
        return Objects.equals(nomComplet, that.nomComplet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomComplet);
    }

    @Override
    public String toString() {
        return nomComplet;
    }
}
