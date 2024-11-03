package ufu.piloto.dto.series.detail.season;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class SeasonDetailDto {
    private final String id;
    private final String season_number;
    private final String name;
    private final String overview;
    private final LocalDate air_date;
    private final List<SeasonDetailEpisodeDto> episodes;
    private final String poster_path;
    private final double vote_average;
}
