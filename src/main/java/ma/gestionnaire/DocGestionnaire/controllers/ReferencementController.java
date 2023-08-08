package ma.gestionnaire.DocGestionnaire.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.type.ReferenceType;

import ma.gestionnaire.DocGestionnaire.dto.ReferenceTypeDemandeDocumentRequisDto;
import ma.gestionnaire.DocGestionnaire.service.ReferencementService;

@RestController
@RequestMapping("/api/v1/referencement")
@PreAuthorize("hasRole('REFERENT')")
public class ReferencementController {

    @Autowired
    ReferencementService referencementService;

    @PostMapping(path = "/references")
    public ResponseEntity saveReferenceTypeDemandeDocumentRequis(
            @RequestBody ReferenceTypeDemandeDocumentRequisDto referenceTypeDemandeDocumentRequisDto) {
        return ResponseEntity.created(URI.create("/api/v1/referencement/"))
                .body(referencementService.lieerTypeDemandeAvecDocument(referenceTypeDemandeDocumentRequisDto));
    }
}
