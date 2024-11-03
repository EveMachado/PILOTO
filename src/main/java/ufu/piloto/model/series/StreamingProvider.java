package ufu.piloto.model.series;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class StreamingProvider {
    private final String provider_id;
    private final String provider_name;
}
