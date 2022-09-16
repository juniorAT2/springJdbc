package it.accenture.model;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "Person ID", example = "1", required = true)
    @Id @Column(value="ID")
    private Integer id;

    @ApiModelProperty(notes = "Person FirstName", example = "Mario")
    @Column(value="FIRSTNAME")
    private String firstName;

    @ApiModelProperty(notes = "Person LastName", example = "Rossi")
    @Column(value="LASTNAME")
    private String lastName;

}
