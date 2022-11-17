package ru.stqa.learn.addressbook.model;

import java.util.Objects;

//object
public class GroupData {
    private final String id;
    private final String header;
    private final String footer;
    private final String name;

    //constructor
    public GroupData(String id,String header, String footer, String name) {
        this.id = id;
        this.header = header;
        this.footer = footer;
        this.name = name;
    }
    public GroupData(String header, String footer, String name) {
        this.id = null;
        this.header = header;
        this.footer = footer;
        this.name = name;
    }

    public String getId() {
        return id;
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

        if (!Objects.equals(id, groupData.id)) return false;
        return Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
