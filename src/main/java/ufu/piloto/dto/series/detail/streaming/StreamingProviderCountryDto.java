package ufu.piloto.dto.series.detail.streaming;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StreamingProviderCountryDto {
    private final List<StreamingProviderDto> flatrate;
    private final List<StreamingProviderDto> buy;
    private final List<StreamingProviderDto> ads;
}
