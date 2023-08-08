package ma.gestionnaire.DocGestionnaire.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeDemande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typedemande;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "type_demande_type_document_requis", joinColumns = @JoinColumn(name = "type_demande_id"), inverseJoinColumns = @JoinColumn(name = "type_document_id"))
    private List<TypeDocument> documentsRequired;
}
