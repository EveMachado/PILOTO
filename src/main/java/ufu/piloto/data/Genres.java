package ufu.piloto.data;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Genres {
    private final List<Genres> genres = new ArrayList<>();
}
