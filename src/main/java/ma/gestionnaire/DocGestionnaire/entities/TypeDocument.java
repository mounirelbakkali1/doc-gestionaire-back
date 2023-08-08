package ma.gestionnaire.DocGestionnaire.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typedocument;

    @ManyToMany(mappedBy = "documentsRequired")
    @JsonIgnore
    private List<TypeDemande> typeDemande;

    public void addTypeDemande(TypeDemande typeDemande2) {
        this.typeDemande.add(typeDemande2);
    }
}
