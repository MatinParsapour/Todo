package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserTable {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name",length = 100)
    private String name;

    @Column(name = "username" ,length = 20)
    private String username;

    @Column(name = "password",length = 10)
    private String password;

    public UserTable(){
    }

    public UserTable(String name, String username , String password){
        this.name = name;
        this.username = username;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
