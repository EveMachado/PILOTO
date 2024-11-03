package ufu.piloto.dto.series.detail.series;


import lombok.AllArgsConstructor;
import lombok.Getter;
import ufu.piloto.dto.series.genre.GenreDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class SeriesDetailDto {
    private final String id;
    private final String name;
    private final String poster_path;
    private final String original_name;
    private final String overview;
    private final List<GenreDto> genres;
    private final String backdrop_path;
    private final boolean in_production;
    private final String status;
    private final LocalDate last_air_date;
    private final List<ProductionCountryDto> production_countries;
    private final int number_of_seasons;
    private final int number_of_episodes;
    private final List<SeriesDetailSeasonDto> seasons;
    private final double popularity;
    private final double vote_average;
    private final long vote_count;
}
