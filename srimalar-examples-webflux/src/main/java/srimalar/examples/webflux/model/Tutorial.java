package srimalar.examples.webflux.model;

import java.util.UUID;

public class Tutorial {
    private String id;

    private String title;

    private String description;

    private boolean published;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public static Tutorial create(String tutorialId) {
        Tutorial tutorial = new Tutorial();
        tutorial.setDescription(UUID.randomUUID().toString());
        tutorial.setTitle(UUID.randomUUID().toString().substring(6));
        tutorial.setId(tutorialId);
        tutorial.setPublished(true);
        return tutorial;
    }
}
