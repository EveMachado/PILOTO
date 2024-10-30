package ufu.piloto.model.series;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Series {
    private final String id;
    private final String name;
    private final Image poster;
    private final String originalName;
    private final String synopsis;
    private final Genre[] genres;
    private final Image backdrop;
    private final boolean inProduction;
    private final String status;
    private final String lastAirDate;
    private final ProductionCountry[] productionCountries;
    private final int numberOfSeasons;
    private final int numberOfEpisodes;
    private final Season[] seasons;
    private final double popularity;
    private final double voteAverage;
    private final long voteCount;
}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
