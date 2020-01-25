/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

package com.aaroncb.javatodos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User extends Auditable
{
        /*
        | UserID | Username | Password  | PrimaryEmail  | UserRoles | UserTodos |
        |--------|----------|-----------|---------------|-----------|-----------|
        | 1      | Aaron    | Password1 | test@test.com | Role      | Task      |
        */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable = false,
            unique = true)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false,
            unique = true)
    @Email
    private String email;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userroles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Todo> userTodos = new ArrayList<>();

    public User() { }

    public User(String username,
                String password,
                String email) {
        setUsername(username);
        setPassword(password);
        this.email = email;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        if (username == null) // this is possible when updating a user
        {
            return null;
        } else {
            return username.toLowerCase();
        }
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getEmail() {
        if (email == null) // this is possible when updating a user
        {
            return null;
        } else {
            return email.toLowerCase();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRoles> getUserroles() {
        return userroles;
    }

    public void setUserroles(List<UserRoles> userroles) {
        this.userroles = userroles;
    }

    public List<Todo> getUserTodos() {
        return userTodos;
    }

    public void setUserTodos(List<Todo> userTodos) {
        this.userTodos = userTodos;
    }
}
