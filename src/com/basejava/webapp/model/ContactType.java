package com.basejava.webapp.model;

public enum ContactType {
    PHONE("Tel."),
    SKYPE("Skype"),
    MAIL("Mail"),
    LINKEDIN("Linkedin"),
    GITHUB("GitHub"),
    STACKOVERFLOW("StackOverFlow"),
    HOMEPAGE("Home Page");

    private final String title;
    ContactType(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}
