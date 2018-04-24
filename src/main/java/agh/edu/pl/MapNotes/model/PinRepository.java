package agh.edu.pl.MapNotes.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository which connect to database with information about {@link Pin pins}.
 */
public interface PinRepository extends JpaRepository<Pin,Long>{
}
