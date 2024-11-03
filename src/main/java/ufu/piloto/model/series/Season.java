package ufu.piloto.model.series;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class Season {
    private final String id;
    private final String seasonNumber;
    private final String name;
    private final String synopsis;
    private final LocalDate airDate;
    private final int episodeCount;
    private List<Episode> episodes;
    private final Image poster;
    private final double voteAverage;
}
