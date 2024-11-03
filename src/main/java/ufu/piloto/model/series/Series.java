package ufu.piloto.model.series;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class Series {
    private String id;
    private String name;
    private Image poster;
    private String originalName;
    private String synopsis;
    private List<Genre> genres;
    private Image backdrop;
    private boolean inProduction;
    private String status;
    private LocalDate lastAirDate;
    private List<ProductionCountry> productionCountries;
    private int numberOfSeasons;
    private int numberOfEpisodes;
    private List<Season> seasons;
    private List<StreamingProvider> streamingProviders;
    private double popularity;
    private double voteAverage;
    private long voteCount;

    public Series(String id, String name, Image poster, String originalName, String synopsis) {
        this.id = id;
        this.name = name;
        this.poster = poster;
        this.originalName = originalName;
        this.synopsis = synopsis;
    }

    public Series(String id, String name, Image poster, String originalName, String synopsis, double popularity) {
        this.id = id;
        this.name = name;
        this.poster = poster;
        this.originalName = originalName;
        this.synopsis = synopsis;
        this.popularity = popularity;
    }

    public Series(String id, String name, Image poster, String originalName, String synopsis, double voteAverage, long voteCount) {
        this.id = id;
        this.name = name;
        this.poster = poster;
        this.originalName = originalName;
        this.synopsis = synopsis;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }
}
