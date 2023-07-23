package ma.gestionnaire.DocGestionnaire.controllers;

import ma.gestionnaire.DocGestionnaire.dto.DocumentDto;
import ma.gestionnaire.DocGestionnaire.service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/parametrages/documents")
public class DocumentController {

        @Autowired
        private DocumentService documentService;

        // post mapping
        @PostMapping
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity saveDocument(
                        @RequestBody DocumentDto documentDto) {
                return ResponseEntity.ok().body(documentService.create(documentDto));
        }

        @GetMapping
        public ResponseEntity getAllDocuments() {
                return ResponseEntity.ok().body(documentService.getAllDocument());
        }

        @DeleteMapping(path = "/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity deleteDocument(
                        @PathParam("id") Long id) {
                documentService.delete(id);
                return ResponseEntity.ok().body("Document deleted successfully");
        }

        @PutMapping(path = "/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity updateDocument(
                        @RequestParam(name = "id") Long id) {
                DocumentDto documentDto = documentService.getDocumentById(id);
                return ResponseEntity.ok().body(documentDto);
        }

}
