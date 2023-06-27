package ma.gestionnaire.DocGestionnaire.service;

import ma.gestionnaire.DocGestionnaire.dto.DocumentDto;

import java.util.List;

public interface DocumentService {

    DocumentDto create(DocumentDto documentDto);

    List<DocumentDto> getAllDocument();

    DocumentDto getDocumentById(Long id);

    List<DocumentDto> getDocumentByTitre(String titre);

    void delete(Long id);

    DocumentDto update(Long id, DocumentDto documentDto);

}
