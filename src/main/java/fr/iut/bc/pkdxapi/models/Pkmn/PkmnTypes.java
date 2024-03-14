package fr.iut.bc.pkdxapi.models.Pkmn;

public enum PkmnTypes {
    NORMAL("#A8A878"),
    FIRE("#F08030"),
    FIGHTING("#C03028"),
    WATER("#6890F0"),
    FLYING("#A890F0"),
    GRASS("#78C850"),
    POISON("#A040A0"),
    ELECTRIC("#F8D030"),
    GROUND("#E0C068"),
    PSYCHIC("#F85888"),
    ROCK("#B8A038"),
    ICE("#98D8D8"),
    BUG("#A8B820"),
    DRAGON("#7038F8"),
    GHOST("#705898"),
    DARK("#705848"),
    STEEL("#B8B8D0"),
    FAIRY("#EE99AC");

    private String color;

    PkmnTypes(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
