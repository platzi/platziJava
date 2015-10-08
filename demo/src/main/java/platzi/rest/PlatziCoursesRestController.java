package platzi.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import platzi.app.Course;
import platzi.app.CourseRepository;
import platzi.app.Instructor;
import platzi.app.InstructorRepository;
import platzi.async.LogAsync;

@RestController
@RequestMapping("/instructors")
public class PlatziCoursesRestController {

	private CourseRepository courseRepository;

	private InstructorRepository instructorRepository;
	
	private LogAsync logAsync;
	
	@RequestMapping
	String simpleGet(){
		return "hello";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> addUser(@RequestBody Instructor instructor) {
		instructorRepository.save(instructor);
		return new ResponseEntity<>(null, null, HttpStatus.CREATED);
	}
	

	@RequestMapping(value = "/{userId}/courses", method = RequestMethod.POST)
	ResponseEntity<?> addCourse(@PathVariable String userId, @RequestBody Course input) {
		this.validateUser(userId);
		return this.instructorRepository
				.findByUsername(userId)
				.map(account -> {
					Course result = courseRepository.save(new Course(account,
							 input.description));

					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setLocation(ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getId()).toUri());
					return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
				}).get();

	}

	@RequestMapping(value = "/{userId}/courses/{courseId}", method = RequestMethod.GET)
	public Course readCourse(@PathVariable String userId, @PathVariable Long courseId) {
		this.validateUser(userId);
		logAsync.log("leyendo el curso");
		return this.courseRepository.findOne(courseId);
	}

	@RequestMapping(value = "/{userId}/courses", method = RequestMethod.GET)
	Collection<Course> readCourses(@PathVariable String userId) {
		System.out.println("platzi");
		this.validateUser(userId);
		return this.courseRepository.findByInstructorUsername(userId);
	}

	@Autowired
	public PlatziCoursesRestController(CourseRepository courseRepository,
			InstructorRepository instructorRepository,
			LogAsync logAsync) {
		this.courseRepository = courseRepository;
		this.instructorRepository = instructorRepository;
		this.logAsync = logAsync;
	}

	private void validateUser(String userId) {
		this.instructorRepository.findByUsername(userId).orElseThrow(
				() -> new UserNotFoundException(userId));
	}

	public LogAsync getLogAsync() {
		return logAsync;
	}

	public void setLogAsync(LogAsync logAsync) {
		this.logAsync = logAsync;
	}
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String userId) {
		super("could not find user '" + userId + "'.");
	}
}
