package ma.gestionnaire.DocGestionnaire.repositories;

import ma.gestionnaire.DocGestionnaire.entities.Document;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByTitre(String titre);
}
