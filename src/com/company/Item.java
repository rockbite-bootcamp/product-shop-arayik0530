package com.company;

import java.io.*;
import java.util.Objects;

/**
 * Item class is to describe items in the shop
 * It implements Serializable to make deep copy
 */
public class Item implements Serializable {

    //unique identifier
    private int id;

    //Type of the item
    private Type type;

    //count of things of the same type in the item
    private int count;

    public Item(final int id, final Type type) {
        this.id = id;
        this.type = type;
        this.count = 0;
    }

    public Type getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type=" + type +
                ", count=" + count +
                '}';
    }

    /**
     * This method is custom implementation to make a deep copy, not an overridden version of the Object class's method
     *
     * @return Item
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected Item deepClone() throws IOException, ClassNotFoundException {
        //Serialization of object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);

        //De-serialization of object
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);

        return (Item) in.readObject();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        final Item item = (Item) o;
        return getId() == item.getId() &&
                getCount() == item.getCount() &&
                Objects.equals(getType(), item.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getCount());
    }
}


