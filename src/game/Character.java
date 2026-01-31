package game;

public class Character {
    private String id;
    private String name;
    private String description;
    private String location;
    private String quest;
    private String Dialogue;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getDialogue() {
        return Dialogue;
    }

    public void setDialogue(String dialogue) {
        Dialogue = dialogue;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
