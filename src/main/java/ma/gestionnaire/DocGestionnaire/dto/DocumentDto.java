package ma.gestionnaire.DocGestionnaire.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DocumentDto {

    private Long id;
    private String titre;
    private String langue;
    private String resume;
    private String pictureName;
    private String fileName;

}
