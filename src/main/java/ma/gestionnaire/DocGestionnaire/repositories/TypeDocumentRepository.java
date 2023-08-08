package ma.gestionnaire.DocGestionnaire.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.gestionnaire.DocGestionnaire.entities.TypeDocument;

@Repository
public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {
}
