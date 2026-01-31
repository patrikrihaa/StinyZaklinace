package game;

import java.util.ArrayList;

public class Location {
    private String id;
    private String name;
    private String description;
    private ArrayList<String> nextLocations;
    private ArrayList<String> Items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getNextLocations() {
        return nextLocations;
    }

    public void setNextLocations(ArrayList<String> nextLocations) {
        this.nextLocations = nextLocations;
    }

    public ArrayList<String> getItems() {
        return Items;
    }

    public void setItems(ArrayList<String> items) {
        Items = items;
    }

    @Override
    public String toString() {
        return  name + ": " + description;
    }
}

