package ma.gestionnaire.DocGestionnaire.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.gestionnaire.DocGestionnaire.dto.ReferenceTypeDemandeDocumentRequisDto;
import ma.gestionnaire.DocGestionnaire.entities.Document;
import ma.gestionnaire.DocGestionnaire.entities.TypeDemande;
import ma.gestionnaire.DocGestionnaire.entities.TypeDocument;
import ma.gestionnaire.DocGestionnaire.repositories.DocumentRepository;
import ma.gestionnaire.DocGestionnaire.repositories.TypeDocumentRepository;

@Service
public class ReferencementService {

    @Autowired
    TypeDemandeService typeDemandeService;

    @Autowired
    TypeDocumentRepository typeDocumentRepository;

    public TypeDemande lieerTypeDemandeAvecDocument(
            ReferenceTypeDemandeDocumentRequisDto referenceTypeDemandeDocumentRequisDto) {
        TypeDemande typeDemande = typeDemandeService.getById(referenceTypeDemandeDocumentRequisDto.getIdTypeDemande());
        List<TypeDocument> documents = referenceTypeDemandeDocumentRequisDto.getIdDocumentsRequis().stream()
                .map(document -> typeDocumentRepository.findById(document).get()).collect(Collectors.toList());
        typeDemande.getDocumentsRequired().clear();
        typeDemande.getDocumentsRequired().addAll(documents);
        documents.forEach(document -> {
            document.addTypeDemande(typeDemande);
            typeDocumentRepository.save(document);
        });
        var tpDemande = typeDemandeService.save(typeDemande);
        return tpDemande;

    }

}
