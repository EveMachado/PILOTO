package ufu.piloto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ufu.piloto.adapter.LocalDateAdapter;
import ufu.piloto.error.RequestException;
import ufu.piloto.model.series.Genre;
import ufu.piloto.model.series.Series;
import ufu.piloto.model.settings.ImageSettings;
import ufu.piloto.service.api.tmdb.TmdbService;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws RequestException {
        System.out.println("Hello, World!");

        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

        TmdbService tmdbService = TmdbService.getInstance();

        ImageSettings imageSettings = tmdbService.getConfiguration();
        System.out.println(imageSettings);

        List<Genre> genres = tmdbService.getGenres();
        System.out.println(genres);

        List<Series> searchResult = tmdbService.searchSeries("How I Met Your");
        System.out.println(gson.toJson(searchResult));

        Series serie = tmdbService.getSeriesDetail("1100");
        System.out.println(gson.toJson(serie));

        List<Series> discoverResult = tmdbService.discoverSeries(genres.get(1));
        System.out.println(gson.toJson(discoverResult));

        List<Series> popularSeriesResult = tmdbService.getPopularSeries();
        System.out.println(gson.toJson(popularSeriesResult));

        List<Series> topRatedSeriesResult = tmdbService.getTopRatedSeries();
        System.out.println(gson.toJson(topRatedSeriesResult));

    }
}
