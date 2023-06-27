package ma.gestionnaire.DocGestionnaire.service.imp;

import ma.gestionnaire.DocGestionnaire.dto.DocumentDto;
import ma.gestionnaire.DocGestionnaire.entities.Document;
import ma.gestionnaire.DocGestionnaire.exception.ResourceNotFoundException;
import ma.gestionnaire.DocGestionnaire.repositories.DocumentRepositorie;
import ma.gestionnaire.DocGestionnaire.service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImp implements DocumentService {

    @Autowired
    private DocumentRepositorie documentRepositorie;

    @Override
    public DocumentDto create(DocumentDto documentDto) {
        Document createdDocument = documentRepositorie.save(mapDTO_TO_entitie(documentDto));
        return map(createdDocument);
    }

    public DocumentDto map(Document document) {

        DocumentDto documentDto = new DocumentDto();

        documentDto.setId(document.getId());

        documentDto.setTitre(document.getTitre());
        documentDto.setLangue(document.getLangue());
        documentDto.setResume(document.getResume());
        documentDto.setPictureName(document.getPicture());
        documentDto.setFileName(document.getFile());

        return documentDto;
    }

    public Document mapDTO_TO_entitie(DocumentDto documentDto) {

        Document document = new Document();

        document.setId(documentDto.getId());

        document.setTitre(documentDto.getTitre());
        document.setLangue(documentDto.getLangue());
        document.setResume(documentDto.getResume());
        document.setPicture(documentDto.getPictureName());
        document.setFile(documentDto.getFileName());
        return document;
    }

    @Override
    public List<DocumentDto> getAllDocument() {
        return documentRepositorie.findAll()
                .stream()
                .map(document -> map(document))
                .collect(Collectors.toList());

    }

    @Override
    public DocumentDto getDocumentById(Long id) {
        Document document = documentRepositorie
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document"));
        return map(document);
    }

    @Override
    public List<DocumentDto> getDocumentByTitre(String titre) {
        return documentRepositorie
                .findByTitre(titre)
                .stream()
                .map(doc -> map(doc))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Document document = documentRepositorie
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document"));
        documentRepositorie.delete(document);
    }

    @Override
    public DocumentDto update(Long id, DocumentDto documentDto) {
        Document document = documentRepositorie
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document"));
        document.setId(id);
        Document created = documentRepositorie.save(document);
        return map(created);
    }
}
