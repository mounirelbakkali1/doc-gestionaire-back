package ma.gestionnaire.DocGestionnaire.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.gestionnaire.DocGestionnaire.entities.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

}
