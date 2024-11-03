package ufu.piloto.model.series;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import ufu.piloto.model.settings.ImageSettings;

@Getter
@ToString
public class Image {
    private final ImageSettings settings;
    private final String imagePath;
    private final String imageUrl;

    public Image(ImageSettings settings, String imagePath) {
        this.settings = settings;
        this.imagePath = imagePath;
        this.imageUrl = this.settings.builderUrl(imagePath);
    }
}
