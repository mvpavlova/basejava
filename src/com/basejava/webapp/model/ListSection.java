package com.basejava.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection<S> extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final List<S> items;

    public ListSection(S...items) {
        this(Arrays.asList(items));
    }

    public ListSection(List<S> items) {
        Objects.requireNonNull(items, "field can not be null");
        this.items = items;
    }

    public List<S> getItems() {
        return items;
    }

    public String toString() {
        return items.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection<?> that = (ListSection<?>) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
