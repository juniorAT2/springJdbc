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
@Table(name="PERSON")
public class Person implements WithId<Integer> {

    @Id @Column(value="ID")
    private Integer id;

    @Column(value="FIRSTNAME")
    private String firstName;

    @Column(value="LASTNAME")
    private String lastName;

}
