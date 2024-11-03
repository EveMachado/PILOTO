package ufu.piloto.dto.series.detail.streaming;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class StreamingProviderResponseDto {
    private final Map<String, StreamingProviderCountryDto> results;
}
