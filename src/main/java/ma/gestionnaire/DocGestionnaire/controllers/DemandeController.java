package ma.gestionnaire.DocGestionnaire.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import ma.gestionnaire.DocGestionnaire.entities.HttpResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.websocket.server.PathParam;
import ma.gestionnaire.DocGestionnaire.entities.Demande;
import ma.gestionnaire.DocGestionnaire.entities.File;
import ma.gestionnaire.DocGestionnaire.service.DemandeService;

@RestController
@RequestMapping("/api/v1/referencement/demandes")
public class DemandeController {
    @Autowired
    DemandeService demandeService;

    @GetMapping
    @PreAuthorize("hasRole('REFERENT')")
    public ResponseEntity getAllDemandes() {
        return ResponseEntity.ok().body(demandeService.getAll());
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('REFERENT')")
    public ResponseEntity deleteDemande(
            @PathParam("id") Long id) {
        demandeService.delete(id);
        return ResponseEntity.ok().body(new HttpResponse("Demande deleted successfully", null));
    }

    @PostMapping
    @PreAuthorize("hasRole('REFERENT')")
    public ResponseEntity saveDemande(
            // @RequestParam("documents") List<MultipartFile> documents,
            @RequestBody Demande demande)
            throws IOException, URISyntaxException {

        // String timeUpload = String.valueOf((new
        // Timestamp(System.currentTimeMillis())).getTime());

        // if (documents.size() > 0) {
        // for (var file : documents) {
        // String fileName = timeUpload + "-" + file.getOriginalFilename();
        // Path filePath = Paths.get("./src/main/resources/static/uploads/docs/" +
        // fileName);
        // Files.createDirectories(filePath.getParent());
        // Files.copy(file.getInputStream(), filePath,
        // StandardCopyOption.REPLACE_EXISTING);
        // demande.getDocumentsAttache().add(new File(fileName));
        // }
        // }

        var doc = demandeService.save(demande);

        return ResponseEntity
                .created(new URI("/api/v1/demandes/" + doc.getId()))
                .eTag(doc.getId().toString())
                .body(doc);
    }
}
