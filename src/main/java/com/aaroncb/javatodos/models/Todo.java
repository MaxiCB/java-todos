package com.aaroncb.javatodos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="todos")
public class Todo
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date datestarted;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name="userid", nullable = false)
    @JsonIgnoreProperties("todos")
    private User user;

    public Todo() {
    }

    public Todo(User user,
                String description,
                Date datestarted,
                boolean completed) {
        this.user = user;
        this.description = description;
        this.datestarted = datestarted;
        this.completed = completed;
    }

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatestarted() {
        return datestarted;
    }

    public void setDatestarted(Date datestarted) {
        this.datestarted = datestarted;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
