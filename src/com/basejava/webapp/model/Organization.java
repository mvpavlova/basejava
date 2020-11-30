package com.basejava.webapp.model;

import java.time.LocalDate;

public class Organization {
    private final Link homePage;

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Organization(Link homePage, LocalDate startDate, LocalDate endDate, String title, String description) {
        this.homePage = homePage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Organization:" +
                "homePage=" + homePage +
                "start date=" + startDate +
                "end date=" + endDate +
                "title=" + title +
                "description=" + description;
    }

    @Override
    public boolean equals(Object o) {
        if( this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if( !homePage.equals(that.homePage)) return false;
        if( !startDate.equals(that.startDate)) return false;
        if( !endDate.equals(that.endDate)) return false;
        if( !title.equals(that.title)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
