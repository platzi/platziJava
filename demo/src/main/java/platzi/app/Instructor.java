package platzi.app;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Instructor {
    @OneToMany(mappedBy = "instructor")
    private Set<Course> courses = new HashSet<>();

    @Id
    @GeneratedValue
    private Long id;

    public Set<Course> getCourses() {
        return courses;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @JsonIgnore
    public String password;
    public String username;

    public Instructor(String name, String password) {
        this.username = name;
        this.password = password;
    }

    Instructor() { // jpa only
    }
}
