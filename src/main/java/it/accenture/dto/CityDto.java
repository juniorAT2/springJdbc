package it.accenture.dto;

import it.accenture.model.abstraction.WithId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDto implements WithId<Integer> {

    private Integer id;
    private String Name;
    private String cap;
}
