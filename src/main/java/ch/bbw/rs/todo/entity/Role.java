package ch.bbw.rs.todo.entity;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private boolean admin;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public boolean setRole() {
        return admin;
    }
}
