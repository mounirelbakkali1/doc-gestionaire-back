package ma.gestionnaire.DocGestionnaire.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.gestionnaire.DocGestionnaire.entities.HttpResponse;
import ma.gestionnaire.DocGestionnaire.entities.TypeDocument;
import ma.gestionnaire.DocGestionnaire.service.TypeDocumentService;

@RestController
@RequestMapping("/api/v1/parametrages/typeDocuments")
public class TypeDocumentController {

    @Autowired
    TypeDocumentService typeDocumentService;

    @GetMapping
    public ResponseEntity getAllTypeDocument() {
        return ResponseEntity.ok().body(typeDocumentService.getAll());
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteTypeDocument(
            @PathVariable("id") Long id) {
        typeDocumentService.delete(id);
        return ResponseEntity.ok().body(new HttpResponse("TypeDocument deleted successfully", null));
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity saveTypeDocument(@RequestBody TypeDocument typeDocument) {
        typeDocumentService.save(typeDocument);
        return ResponseEntity.created(URI.create("/api/v1/parametrages/typeDocuments/" + typeDocument.getId()))
                .body(typeDocument);
    }
}
