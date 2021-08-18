package domain;

import base.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task extends BaseEntity<Long> {

    private String title;

    private String content;

    private Date creationDate;

    private String status = "open";

    private Date lastUpdate;

    private String description;

    @ManyToOne
    private User user;

    public Task(){}

    public Task(String title, String content, Date creationDate, User user, Date lastUpdate, String description){
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.user = user;
        this.lastUpdate = lastUpdate;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
