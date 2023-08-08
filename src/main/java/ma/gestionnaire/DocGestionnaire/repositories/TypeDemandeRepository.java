package ma.gestionnaire.DocGestionnaire.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.gestionnaire.DocGestionnaire.entities.TypeDemande;

public interface TypeDemandeRepository extends JpaRepository<TypeDemande, Long> {

}
