package ru.stqa.learn.addressbook.model;

import java.util.Objects;

//object
public class GroupData {
    private  int id = Integer.MAX_VALUE;;
    private  String header;
    private  String footer;
    private  String name;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getHeader() {
        return header;
    } //method return attribute
    public String getFooter() {
        return footer;
    }


    public GroupData withId(int id) {
        this.id = id;
        return this;
    }
    public GroupData withName(String name) {
        this.name = name;
        return this;
    }

    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData withFooter(String footer) {
        this.footer = footer;
        return this;
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
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
