package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends Model {

    @Column(name = "username")
    private String userName = null;

    @Column(name = "password")
    private String hashPassword = null;

    @Column(name = "uid")
    @Id
    private int uid;

    @OneToMany(mappedBy = "user")
    public List<Item> item;

    public static Finder<Integer, User> find = new Finder<>(User.class);

    public User() {
    }

    public User(String userName, String hashPassword) {
        this.userName = userName;
        this.hashPassword = hashPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }
}
