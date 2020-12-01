package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) &&
                title.equals(that.title) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, startDate, endDate, title, description);
    }
}
