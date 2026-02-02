package game;

public class Character {
    private String id;
    private String name;
    private String description;
    private String location;
    private String quest;
    private String dialogue;
    private String questCompleteDialogue;
    private String riddleAnswer;
    private boolean hostile;

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
        return dialogue;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public String getQuestCompleteDialogue() {
        return questCompleteDialogue;
    }

    public void setQuestCompleteDialogue(String questCompleteDialogue) {
        this.questCompleteDialogue = questCompleteDialogue;
    }

    public String getRiddleAnswer() {
        return riddleAnswer;
    }

    public void setRiddleAnswer(String riddleAnswer) {
        this.riddleAnswer = riddleAnswer;
    }

    public boolean isHostile() {
        return hostile;
    }

    public void setHostile(boolean hostile) {
        this.hostile = hostile;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
