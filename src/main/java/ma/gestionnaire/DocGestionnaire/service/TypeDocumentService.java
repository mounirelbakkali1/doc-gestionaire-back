package ma.gestionnaire.DocGestionnaire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.gestionnaire.DocGestionnaire.entities.TypeDocument;
import ma.gestionnaire.DocGestionnaire.repositories.TypeDocumentRepository;
import java.util.List;

@Service
public class TypeDocumentService {

    @Autowired
    TypeDocumentRepository typeDocumentRepository;

    public void save(TypeDocument typeDemande) {
        typeDocumentRepository.save(typeDemande);
    }

    public List<TypeDocument> getAll() {
        return typeDocumentRepository.findAll();
    }

    public void delete(Long id) {
        typeDocumentRepository.deleteById(id);
    }

    public TypeDocument getById(Long id) {
        return typeDocumentRepository.findById(id).get();
    }

}
