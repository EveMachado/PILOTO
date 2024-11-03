package ufu.piloto.model.series;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class Episode {
    private final String id;
    private final String number;
    private final String name;
    private final String synopsis;
    private final LocalDate airDate;
}
