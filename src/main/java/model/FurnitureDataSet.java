package model;

/**
 * Created by roman on 27.02.16.
 */
public class FurnitureDataSet implements java.io.Serializable{
    private int id;
    private String name;
    private int inventory;
    private int group;

    public FurnitureDataSet(int id, String name, int inventory, int group) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
        this.group = group;
    }

    public FurnitureDataSet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
