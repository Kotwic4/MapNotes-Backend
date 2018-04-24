package agh.edu.pl.MapNotes.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository which connect to database with information about {@link Map maps}.
 */
public interface MapRepository extends JpaRepository<Map,Long> {
}
