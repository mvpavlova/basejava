package com.basejava.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final List<String> items;

    public ListSection(String...items) {
        this(Arrays.asList(items));
    }

    public ListSection(List<String> items) {
        Objects.requireNonNull(items, "items can not be null");
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    public String toString() {
        return items.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
