package fr.iut.bc.pkdxapi.models.Pkmn;

public class PkmnTypeDto {
    private String name;
    private String color;

    public PkmnTypeDto(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }
}