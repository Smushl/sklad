package model;

/**
 * Created by roman on 20.03.16.
 */
public class FurnitureGroupDataSet {
    private int id;
    private String name;

    public FurnitureGroupDataSet(int id, String name) {
        this.id = id;
        this.name = name;
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
}
