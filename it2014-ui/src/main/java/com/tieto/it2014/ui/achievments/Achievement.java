package com.tieto.it2014.ui.achievments;

public interface Achievement {

    public boolean evaluate();

    public String getTitle();

    public String getDescription();

    public String getImagePath();

    public boolean getStatus();
}
