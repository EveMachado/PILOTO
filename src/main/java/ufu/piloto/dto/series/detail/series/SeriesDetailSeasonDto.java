package ufu.piloto.dto.series.detail.series;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SeriesDetailSeasonDto {
    private final String id;
    private final String season_number;
    private final String name;
    private final String overview;
    private final LocalDate air_date;
    private final int episode_count;
    private final String poster_path;
    private final double vote_average;
}
