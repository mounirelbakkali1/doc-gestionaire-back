package ma.gestionnaire.DocGestionnaire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aj.org.objectweb.asm.Type;
import ma.gestionnaire.DocGestionnaire.entities.TypeDemande;
import ma.gestionnaire.DocGestionnaire.repositories.TypeDemandeRepository;
import java.util.List;

@Service
public class TypeDemandeService {

    @Autowired
    TypeDemandeRepository typeDemandeRepository;

    public TypeDemande save(TypeDemande typeDemande) {
        return typeDemandeRepository.save(typeDemande);
    }

    public List<TypeDemande> getAll() {
        return typeDemandeRepository.findAll();
    }

    public void delete(Long id) {
        typeDemandeRepository.deleteById(id);
    }

    public TypeDemande getById(Long id) {
        return typeDemandeRepository.findById(id).get();
    }

}
