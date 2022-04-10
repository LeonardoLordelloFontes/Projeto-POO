package com.grupoxx.smarthouse;

public class Owner {
    private String nif;
    private String name;

    public Owner() {
        this.nif = "000000000";
        this.name = "FIRSTNAME LASTNAME";
    }

    public Owner(String nif, String nome) {
        this.nif = nif;
        this.name = nome;
    }

    public Owner(Owner owner) {
        this.nif = owner.getNif();
        this.name = owner.getNome();
    }

    public String getNif() {
        return nif;
    }

    public String getNome() {
        return name;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setNome(String nome) {
        this.name = nome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Nome: ");
        sb.append(this.name).append("\n").append("NIF: ").append(this.nif);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return owner.getNome().equals(this.name) && owner.getNif().equals(this.nif);
    }

    @Override
    public Owner clone() {
        return new Owner(this);
    }
}