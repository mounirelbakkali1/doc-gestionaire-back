package ma.gestionnaire.DocGestionnaire.entities;

import java.util.List;

import org.hibernate.annotations.ManyToAny;
import org.modelmapper.internal.bytebuddy.agent.builder.AgentBuilder.PoolStrategy.Eager;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String title;
    @OneToOne
    private TypeDemande typeDemande;
    @ManyToMany
    @JoinTable(name = "demande_documents_attached", joinColumns = @JoinColumn(name = "demande_id"), inverseJoinColumns = @JoinColumn(name = "document_id"))
    private List<Document> documentsAttache;
}
