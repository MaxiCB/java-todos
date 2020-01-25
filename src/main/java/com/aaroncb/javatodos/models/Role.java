/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

package com.aaroncb.javatodos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "role",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserRoles> userroles = new ArrayList<>();

    public Role(){}

    public Role(String name) {this.name = name.toUpperCase();}

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    public String getName() {
        if(name == null){
            return null;
        } else {
            return name.toUpperCase();
        }
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public List<UserRoles> getUserroles() {
        return userroles;
    }

    public void setUserroles(List<UserRoles> userroles) {
        this.userroles = userroles;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleid=" + roleid +
                ", name='" + name + '\'' +
                ", userroles=" + userroles +
                ", createdby='" + createdby + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedData=" + lastModifiedData +
                '}';
    }
}
