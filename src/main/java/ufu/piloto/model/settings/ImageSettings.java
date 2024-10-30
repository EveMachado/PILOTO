package ufu.piloto.model.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImageSettings {
    private final String backdropSize = "w780";
    private final String logoSize = "w300";
    private final String posterSize = "w500";
    private final String baseUrl;
}
