package platzi.app;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
	 Optional<Instructor> findByUsername(String username);
}
