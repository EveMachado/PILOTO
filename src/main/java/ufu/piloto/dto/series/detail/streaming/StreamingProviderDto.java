package ufu.piloto.dto.series.detail.streaming;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StreamingProviderDto {
    private final String provider_id;
    private final String provider_name;
}
