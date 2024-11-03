package ufu.piloto.dto.series.genre;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GenresResponseDto {
    private List<GenreDto> genres;
}
