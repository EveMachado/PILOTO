package ufu.piloto.model.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ImageSettings {
    private final String backdropSize = "w780";
    private final String logoSize = "w300";
    private final String posterSize = "w500";
    private final String baseUrl;

    public String builderUrl(String imagePath) {
        return this.baseUrl + posterSize + imagePath;
    }
}
