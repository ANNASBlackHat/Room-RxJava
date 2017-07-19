package com.annasblackhat.roomrxjava.persistance;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Mia Khalifa on 19/07/2017.
 */

@Entity
public class User {

    @PrimaryKey
    private long id;
    private String email;
    @ColumnInfo(name = "name")
    private String sureName;

    @Ignore
    public User() {
    }

    public User(long id, String email, String sureName) {
        this.id = id;
        this.email = email;
        this.sureName = sureName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }
}
