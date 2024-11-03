package ufu.piloto.dto.settings.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class ImagesDto {
    private String base_url;
    private String secure_base_url;
    private List<String> backdrop_sizes;
    private List<String> logo_sizes;
    private List<String> poster_sizes;
    private List<String> profile_sizes;
    private List<String> still_sizes;
}
