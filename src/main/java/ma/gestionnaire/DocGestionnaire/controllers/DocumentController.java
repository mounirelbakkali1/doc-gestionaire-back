package ma.gestionnaire.DocGestionnaire.controllers;

import ma.gestionnaire.DocGestionnaire.dto.DocumentDto;
import ma.gestionnaire.DocGestionnaire.service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.websocket.server.PathParam;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

        @Autowired
        private DocumentService documentService;

        @GetMapping
        public ResponseEntity getAllDocuments() {
                return ResponseEntity.ok().body(documentService.getAllDocument());
        }

        @DeleteMapping(path = "/{id}")
        public ResponseEntity deleteDocument(
                        @PathParam("id") Long id) {
                documentService.delete(id);
                return ResponseEntity.ok().body("Document deleted successfully");
        }

        @PutMapping(path = "/{id}")
        public ResponseEntity updateDocument(
                        Model model,
                        @RequestParam(name = "id") Long id) {
                DocumentDto documentDto = documentService.getDocumentById(id);
                return ResponseEntity.ok().body(documentDto);
        }

        @PostMapping()
        public ResponseEntity saveDocument(@RequestParam("file") MultipartFile file,
                        @RequestParam("picture") MultipartFile img,
                        @ModelAttribute("documentDto") DocumentDto documentDto, Model model)
                        throws IOException, URISyntaxException {

                // TODO: time of upload
                String timeUpload = String.valueOf((new Timestamp(System.currentTimeMillis())).getTime());

                // TODO: upload file
                if (!file.isEmpty()) {
                        String fileName = timeUpload + "-" + file.getOriginalFilename();
                        Path filePath = Paths.get("./src/main/resources/static/uploads/docs/" + fileName);
                        Files.createDirectories(filePath.getParent());
                        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                        documentDto.setFileName(fileName);
                }

                // TODO : upload image
                if (!img.isEmpty()) {
                        String imageName = timeUpload + "-" + img.getOriginalFilename();
                        Path imagePath = Paths.get("./src/main/resources/static/uploads/images/" + imageName);
                        Files.createDirectories(imagePath.getParent());
                        Files.copy(img.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
                        documentDto.setPictureName(imageName);
                }
                var doc = documentService.create(documentDto);

                return ResponseEntity
                                .created(new URI("/api/v1/documents/" + doc.getId()))
                                .eTag(doc.getId().toString())
                                .body(doc);
        }

}
