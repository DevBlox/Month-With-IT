package com.tieto.it2014.domain.achievment.entity;

import com.tieto.it2014.domain.achievment.Entity;

public class Achievment extends Entity {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String description;
    private String completeMessage;

    public Achievment() {
        super(0);
    }

    public Achievment(String name, String description, String completeMessage, int id) {
        super(id);
        this.id = id;
        this.name = name;
        this.description = description;
        this.completeMessage = completeMessage;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the completeMessage
     */
    public String getCompleteMessage() {
        return completeMessage;
    }

    /**
     * @param completeMessage the completeMessage to set
     */
    public void setCompleteMessage(String completeMessage) {
        this.completeMessage = completeMessage;
    }

}
