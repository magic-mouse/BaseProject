package com.github.dronezcc.riser.gui.domain;

import javax.persistence.*;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    Long identity;
    @Column
    private
    String validTo;
    @Column
    private
    long userId;

    public Token() {
    }

    public Long getIdentity() {
        return identity;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Token{" +
                "identity='" + identity + '\'' +
                ", validTo='" + validTo + '\'' +
                ", userId=" + userId +
                '}';
    }
}
