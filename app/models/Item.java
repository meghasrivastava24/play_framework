package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "items")

public class Item extends Model {

    @Column(name = "user_uid")
    private int userUid;

    @Column(name = "item_id")
    @Id
    private int item_id;


    @Column(name = "subject")
    private String subject;


    @Column(name = "item_desc")
    private String item_desc;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "status")
    private String status;

    @ManyToOne
    public User user;

    public static Finder<Integer, Item> findItem = new Finder<>(Item.class);


    public Item(int userUid, String subject, String item_desc, Timestamp timestamp, String status) {
        this.userUid = userUid;
        this.subject = subject;
        this.item_desc = item_desc;
        this.timestamp = timestamp;
        this.status = status;
    }

    public int getUserUid() {
        return userUid;
    }

    public void setUserUid(int userUid) {
        this.userUid = userUid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getItemDesc() {
        return item_desc;
    }

    public void setItemDesc(String item_desc) {
        this.item_desc = item_desc;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
