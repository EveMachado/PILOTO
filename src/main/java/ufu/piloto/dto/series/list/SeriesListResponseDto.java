package ufu.piloto.dto.series.list;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SeriesListResponseDto {
    private int page;
    private List<SeriesFoundDto> results;
    private int total_pages;
    private int total_results;
}
