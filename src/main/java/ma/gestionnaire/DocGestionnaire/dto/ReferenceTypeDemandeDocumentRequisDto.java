package ma.gestionnaire.DocGestionnaire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceTypeDemandeDocumentRequisDto {
    private Long idTypeDemande;
    private Set<Long> idDocumentsRequis;
}
