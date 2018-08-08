package com.github.dronezcc.riser.gui.domain;

import javax.persistence.*;

@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    String location;
    @Column
    String value;
    @Column
    int weight;
    @Column
    String justNote;

    public MenuItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String loation) {
        this.location = loation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getJustNote() {
        return justNote;
    }

    public void setJustNote(String justNote) {
        this.justNote = justNote;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", value='" + value + '\'' +
                ", weight=" + weight +
                ", justNote='" + justNote + '\'' +
                '}';
    }
}
