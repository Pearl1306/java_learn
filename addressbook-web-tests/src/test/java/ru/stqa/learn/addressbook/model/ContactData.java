package ru.stqa.learn.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String homephone;
    private final String email;



    public ContactData(int id,String firstname, String lastname,
                       String address, String homephone, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.homephone = homephone;
        this.email = email;

    }
    public ContactData(String firstname, String lastname,
                       String address, String homephone, String email) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.homephone = homephone;
        this.email = email;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", homephone='" + homephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (!Objects.equals(firstname, that.firstname)) return false;
        return Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
