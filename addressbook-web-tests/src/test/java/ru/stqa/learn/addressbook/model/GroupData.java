package ru.stqa.learn.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
@Entity
@Table(name = "group_list")
@XStreamAlias("group")
public class GroupData {
    @XStreamOmitField
    @Id
    @Column(name = "group_id")
    private  int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "group_header")
    @Type(type = "text")
    private  String header;
    @Expose
    @Column(name = "group_footer")
    @Type(type = "text")
    private  String footer;
    @Expose
    @Column(name = "group_name")
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
    public String toString() {
        return "GroupData{" +
              "id=" + id +
              ", header='" + header + '\'' +
              ", footer='" + footer + '\'' +
              ", name='" + name + '\'' +
              '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (id != groupData.id) return false;
        if (!Objects.equals(header, groupData.header)) return false;
        if (!Objects.equals(footer, groupData.footer)) return false;
        return Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (footer != null ? footer.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
