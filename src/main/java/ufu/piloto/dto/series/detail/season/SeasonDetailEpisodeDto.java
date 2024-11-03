package ufu.piloto.dto.series.detail.season;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SeasonDetailEpisodeDto {
    private final String id;
    private final String episode_number;
    private final String name;
    private final String overview;
    private final LocalDate air_date;
}
