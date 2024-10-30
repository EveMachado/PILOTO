package ufu.piloto.model.series;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ufu.piloto.model.settings.ImageSettings;

@Getter
@AllArgsConstructor
public class Image {
    private final ImageSettings settings;
    private final String imagePath;
    private final String imageUrl;
}
