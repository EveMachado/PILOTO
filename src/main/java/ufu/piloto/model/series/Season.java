package ufu.piloto.model.series;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Season {
    private final String id;
    private final String seasonNumber;
    private final String name;
    private final String overview;
    private final String airDate;
    private final int episodeCount;
    private final Image poster;
    private final double voteAverage;
}
