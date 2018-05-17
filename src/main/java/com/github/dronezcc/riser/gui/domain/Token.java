package com.github.dronezcc.riser.gui.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;

@Entity
public class Token {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "identity", columnDefinition = "BINARY(16)")
    private UUID identity;
    @Column
    private String validTo;
    @Column
    private long userId;
    @Column(columnDefinition="binary default 0")
    private boolean used;

    public UUID getIdentity() {
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


    public String toString() {
        return "Token{" +
                "identity='" + identity + '\'' +
                ", validTo='" + validTo + '\'' +
                ", userId=" + userId +
                '}';
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
