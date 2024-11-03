package ufu.piloto.dto.series.list;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ufu.piloto.dto.series.genre.GenreDto;

import java.util.List;

@Getter
@AllArgsConstructor
public class SeriesFoundDto {
    private String id;
    private String name;
    private String original_name;
    private String overview;
    private String poster_path;
    private List<String> genre_ids;
    private double popularity;
    private double vote_average;
    private long vote_count;
}
