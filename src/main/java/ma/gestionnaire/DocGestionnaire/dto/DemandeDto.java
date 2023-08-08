package ma.gestionnaire.DocGestionnaire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.gestionnaire.DocGestionnaire.entities.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeDto {

    private Long id;
    private String titre;
    private String typeDemande;
    private String description;

    private File documentAttache;
}
