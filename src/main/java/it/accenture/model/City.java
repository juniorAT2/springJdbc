package it.accenture.model;
import it.accenture.model.abstraction.WithId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="CITY")
public class City implements WithId<Integer> {

    @Id @Column(value="ID")
    private Integer id;

    @Column(value="NAME")
    private String Name;

    @Column(value="CAP")
    private String cap;

}