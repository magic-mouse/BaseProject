package com.github.dronezcc.riser.gui.domain;

import javax.persistence.*;

@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    String loation;
    @Column
    String value;
    @Column
    int weight;



    public int getId() {
        return id;
    }


    public void setLocation(String location) {
        this.loation = loation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoation() {
        return loation;
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
}
