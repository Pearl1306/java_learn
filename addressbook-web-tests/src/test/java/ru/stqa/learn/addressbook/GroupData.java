package ru.stqa.learn.addressbook;
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
}
