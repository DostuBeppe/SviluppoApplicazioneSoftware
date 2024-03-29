package businesslogic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class User {


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static enum Role {Cuoco, Chef, Organizzatore, Servizio};

    private String name;
    private Set<Role> roles;
    private String role;
    private int userId;
    public User(String name) {
        this.name = name;
        this.roles = new HashSet<Role>();
    }
    public User(){
        this.roles = new HashSet<Role>();
    }
    public void addRole(Role role) {
        this.roles.add(role);
    }

    public boolean isChef() {
        return this.roles.contains(Role.Chef);
    }

    public String toString() {
        return this.name;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
