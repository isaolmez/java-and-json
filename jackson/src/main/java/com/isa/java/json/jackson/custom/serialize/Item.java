package com.isa.java.json.jackson.custom.serialize;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ItemSerializer.class)
public class Item {

    private int id;

    private String itemName;

    private User owner;

    public Item(int id, String itemName, User owner) {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public User getOwner() {
        return owner;
    }
}
