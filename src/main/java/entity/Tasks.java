package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Tasks {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "creationdate")
    private LocalDate creationDate;

    @Column(name = "status")
    private String status;

    @ManyToOne
    private User user;

    public Tasks(){
    }

    public Tasks(String title, String body, LocalDate creationDate){
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
