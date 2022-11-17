package ru.stqa.learn.addressbook.model;

import java.util.Objects;

//object
public class GroupData {
    private final String header;
    private final String footer;
    private final String name;

    //constructor
    public GroupData(String header, String footer, String name) {
        this.header = header;
        this.footer = footer;
        this.name = name;
    }

    public String getHeader() {
        return header;
    } //method return attribute

    public String getFooter() {
        return footer;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        return Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "name='" + name + '\'' +
                '}';
    }
}
