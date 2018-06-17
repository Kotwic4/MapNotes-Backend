package agh.edu.pl.MapNotes.model;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository which connect to database with information about {@link User maps}.
 */
public interface UserRepository extends JpaRepository<User,Long>  {

}

