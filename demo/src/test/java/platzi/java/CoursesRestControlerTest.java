package platzi.java;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import platzi.app.Course;
import platzi.app.CourseRepository;
import platzi.app.Instructor;
import platzi.app.InstructorRepository;
import platzi.async.LogAsync;
import platzi.async.LogAsyncImpl;
import platzi.rest.PlatziCoursesRestController;

@RunWith(MockitoJUnitRunner.class)
public class CoursesRestControlerTest {
	
	@Mock
	LogAsync logAsync;// = new LogAsyncImpl();
	
	@Mock
	CourseRepository courseRepository;
	
	@Mock
	InstructorRepository instructorRepository;
	
	@InjectMocks
	PlatziCoursesRestController controller;
	
	@Test
	public void testReadCourse() {		
		//Datos de prueba
		String username = "obernal";
		Long courseId = new Long(12345);
		Instructor instructor = new Instructor(username,"verybadpassword");
		Optional<Instructor> instructorOptional = Optional.of(instructor);
		Course course = new Course(instructor,
				"curso de java avanzado");
		
		//Stubbing del comportamiento de findOne 
		when(courseRepository.findOne(courseId))
				.thenReturn(course);
		
		//Stubbing del comportamiento de findByUsername
		when(instructorRepository.findByUsername(username))
		.thenReturn(instructorOptional);
		
		//seteando manualmente el logAsync - mala idea!
		//controller.setLogAsync(logAsync);
		
		// el método que estamos probando
		Course returnCourse = controller.readCourse("obernal", courseId);
		
		// comprobamos que los datos coincidan
		assertEquals("curso de java avanzado", returnCourse.getDescription());
	}

}
