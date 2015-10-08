package platzi.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Course {

    @JsonIgnore
    @ManyToOne
    private Instructor instructor;

    @Id
    @GeneratedValue
    private Long id;

    Course() { // jpa only
    }

    public Course(Instructor instructor, String description) {
        this.description = description;
        this.instructor = instructor;
    }

    public String description;

    public Instructor getInstructor() {
        return instructor;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
