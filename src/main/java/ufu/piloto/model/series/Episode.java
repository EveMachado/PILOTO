package ufu.piloto.model.series;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Episode {
    private final String id;
    private final String number;
    private final String name;
    private final String synopsis;
    private final Date airDate;
}
