package ma.gestionnaire.DocGestionnaire.controllers;

import java.net.URI;
import ma.gestionnaire.DocGestionnaire.entities.HttpResponse;

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

import ma.gestionnaire.DocGestionnaire.entities.TypeDemande;
import ma.gestionnaire.DocGestionnaire.service.TypeDemandeService;

@RestController
@RequestMapping("/api/v1/parametrages/typeDemandes")
public class TypeDemandeController {

    @Autowired
    TypeDemandeService typeDemandeService;

    @GetMapping
    public ResponseEntity getAllTypeDemande() {
        return ResponseEntity.ok().body(typeDemandeService.getAll());
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteTypeDemande(
            @PathVariable("id") Long id) {
        typeDemandeService.delete(id);
        return ResponseEntity.ok().body(new HttpResponse("TypeDemande deleted successfully", null));
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity saveTypeDemande(@RequestBody TypeDemande typeDemande) {
        typeDemandeService.save(typeDemande);
        return ResponseEntity.created(URI.create("/api/v1/parametrages/typeDemandes/" + typeDemande.getId()))
                .body(typeDemande);
    }

}
