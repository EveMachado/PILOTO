package ufu.piloto.service.api.tmdb;

import lombok.SneakyThrows;
import retrofit2.Response;
import ufu.piloto.dto.series.detail.season.SeasonDetailDto;
import ufu.piloto.dto.series.detail.season.SeasonDetailEpisodeDto;
import ufu.piloto.dto.series.detail.series.SeriesDetailDto;
import ufu.piloto.dto.series.detail.series.SeriesDetailSeasonDto;
import ufu.piloto.dto.series.detail.streaming.StreamingProviderDto;
import ufu.piloto.dto.series.detail.streaming.StreamingProviderResponseDto;
import ufu.piloto.dto.series.genre.GenreDto;
import ufu.piloto.dto.series.genre.GenresResponseDto;
import ufu.piloto.dto.series.list.SeriesListResponseDto;
import ufu.piloto.dto.series.list.SeriesFoundDto;
import ufu.piloto.dto.settings.configuration.ConfigurationDto;
import ufu.piloto.error.RequestException;
import ufu.piloto.model.series.*;
import ufu.piloto.model.settings.ImageSettings;
import ufu.piloto.service.api.ApiService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TmdbService extends ApiService {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjMzc4OWFhZDc0NDVjZGI4YjRjNzFmYmY3ZWQwZjlhMCIsIm5iZiI6MTczMDIyMDUyOC44MjU5NDMsInN1YiI6IjY3MjExMGZkMzhiYjIwMzM3OTUzZWY0MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.87pA2a889onOGH4257rGomcOy53A_XSReS4OuDKP8aY";

    private TmdbApi tmdbApi;

    private ImageSettings imageSettings;
    private List<Genre> tmdbGenres;

    protected TmdbService() {
        super();
    }

    public static TmdbService getInstance() {
        return (TmdbService) getInstance(TmdbService.class);
    }

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }

    @Override
    protected Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + TOKEN);
        headers.put("accept", "application/json");
        return headers;
    }

    @Override
    @SneakyThrows
    protected void apiCreate() {
        this.tmdbApi = this.retrofit.create(TmdbApi.class);
        this.getConfiguration();
        this.getGenres();
    }

    public ImageSettings getConfiguration() throws RequestException {
        try {
            Response<ConfigurationDto> configureResponse = this.tmdbApi.getConfiguration().execute();
            if (!configureResponse.isSuccessful())
                throw new RequestException("Falha no carregamento das definições da API");

            ConfigurationDto data = configureResponse.body();

            this.imageSettings = new ImageSettings(
                Objects.requireNonNull(data).getImages().getSecure_base_url()
            );

            return this.imageSettings;

        } catch (IOException e) {
            throw new RequestException(e.getMessage(), e);
        }
    }

    public List<Genre> getGenres() throws RequestException {
        try {
            Response<GenresResponseDto> genresResponse = this.tmdbApi.getGenres("pt-BR").execute();
            if (!genresResponse.isSuccessful())
                throw new RequestException("Falha no carregamento da lista de gêneros");

            GenresResponseDto data = genresResponse.body();

            this.tmdbGenres = new ArrayList<>();

            for (GenreDto genre : Objects.requireNonNull(data).getGenres())
                this.tmdbGenres.add(new Genre(genre.getId(), genre.getName()));

            return this.tmdbGenres;

        } catch (IOException e) {
            throw new RequestException(e.getMessage(), e);
        }
    }

    public List<Series> searchSeries(String seriesName) throws RequestException {
        try {
            Response<SeriesListResponseDto> searchResponse = this.tmdbApi.searchSeries(seriesName, "pt-BR", 1, true).execute();
            if (!searchResponse.isSuccessful())
                throw new RequestException("Falha no processo de pesquisa de séries por nome");

            SeriesListResponseDto data = searchResponse.body();

            List<Series> searchResult = new ArrayList<>();

            for (SeriesFoundDto series : Objects.requireNonNull(data).getResults())
                searchResult.add(
                    new Series(
                        series.getId(),
                        series.getName(),
                        new Image(
                            this.imageSettings,
                            series.getPoster_path()
                        ),
                        series.getOriginal_name(),
                        series.getOverview()
                    )
                );

            return searchResult;

        } catch (IOException e) {
            throw new RequestException(e.getMessage(), e);
        }
    }

    public Series getSeriesDetail(String seriesId) throws RequestException {
        try {
            Response<SeriesDetailDto> seriesDetailResponse = this.tmdbApi.getSeriesDetails(seriesId, "pt-BR").execute();
            if (!seriesDetailResponse.isSuccessful())
                throw new RequestException("Falha no carregamento das informações da série");

            SeriesDetailDto seriesData = seriesDetailResponse.body();

            List<Season> seasonList = new ArrayList<>();
            for (SeriesDetailSeasonDto season : Objects.requireNonNull(seriesData).getSeasons())
                seasonList.add(this.getSeasonDetail(seriesId, season.getSeason_number()));

            return new Series(
                seriesData.getId(),
                seriesData.getName(),
                new Image(
                    this.imageSettings,
                    seriesData.getPoster_path()
                ),
                seriesData.getOriginal_name(),
                seriesData.getOverview(),
                seriesData.getGenres().stream()
                    .map(dto -> new Genre(dto.getId(), dto.getName()))
                    .collect(Collectors.toList()),
                new Image(
                    this.imageSettings,
                    seriesData.getBackdrop_path()
                ),
                seriesData.isIn_production(),
                seriesData.getStatus(),
                seriesData.getLast_air_date(),
                seriesData.getProduction_countries().stream()
                    .map(dto -> new ProductionCountry(dto.getIsoCode(), dto.getName()))
                    .collect(Collectors.toList()),
                seriesData.getNumber_of_seasons(),
                seriesData.getNumber_of_episodes(),
                seasonList,
                this.getStreamingProviders(seriesId),
                seriesData.getPopularity(),
                seriesData.getVote_average(),
                seriesData.getVote_count()
            );

        } catch (IOException e) {
            throw new RequestException(e.getMessage(), e);
        }
    }

    public Season getSeasonDetail(String seriesId, String seasonNumber) throws RequestException {
        try {
            Response<SeasonDetailDto> seasonDetailResponse = this.tmdbApi.getSeasonDetails(seriesId, seasonNumber, "pt-BR").execute();
            if (!seasonDetailResponse.isSuccessful())
                throw new RequestException("Falha no carregamento das informações da temporada da série");

            SeasonDetailDto seasonData = seasonDetailResponse.body();

            List<Episode> episodeList = new ArrayList<>();
            for (SeasonDetailEpisodeDto episode : Objects.requireNonNull(seasonData).getEpisodes())
                episodeList.add(
                    new Episode(
                        episode.getId(),
                        episode.getEpisode_number(),
                        episode.getName(),
                        episode.getOverview(),
                        episode.getAir_date()
                    )
                );

            return new Season(
                seasonData.getId(),
                seasonData.getSeason_number(),
                seasonData.getName(),
                seasonData.getOverview(),
                seasonData.getAir_date(),
                episodeList.size(),
                episodeList,
                new Image(
                    this.imageSettings,
                    seasonData.getPoster_path()
                ),
                seasonData.getVote_average()
            );

        } catch (IOException e) {
            throw new RequestException(e.getMessage(), e);
        }
    }

    public List<StreamingProvider> getStreamingProviders(String seriesId) throws RequestException {
        try {
            Response<StreamingProviderResponseDto> streamingProviderResponse = this.tmdbApi.getStreamingProviders(seriesId).execute();
            if (!streamingProviderResponse.isSuccessful())
                throw new RequestException("Falha no carregamento de provedores de streaming");

            StreamingProviderResponseDto data = streamingProviderResponse.body();

            List<StreamingProvider> providerList = new ArrayList<>();

            for (StreamingProviderDto provider : Objects.requireNonNull(data).getResults().get("BR").getFlatrate())
                providerList.add(new StreamingProvider(provider.getProvider_id(), provider.getProvider_name()));

            return providerList;

        } catch (IOException e) {
            throw new RequestException(e.getMessage(), e);
        }
    }

    public List<Series> discoverSeries(Genre genre) throws RequestException {
        try {
            Response<SeriesListResponseDto> discoverResponse = this.tmdbApi.discoverSeries(genre.getId(), "pt-BR", 1, true).execute();
            if (!discoverResponse.isSuccessful())
                throw new RequestException("Falha no processo de pesquisa de séries por gênero");

            SeriesListResponseDto data = discoverResponse.body();

            List<Series> discoverResult = new ArrayList<>();

            for (SeriesFoundDto series : Objects.requireNonNull(data).getResults())
                discoverResult.add(
                    new Series(
                        series.getId(),
                        series.getName(),
                        new Image(
                            this.imageSettings,
                            series.getPoster_path()
                        ),
                        series.getOriginal_name(),
                        series.getOverview()
                    )
                );

            return discoverResult;

        } catch (IOException e) {
            throw new RequestException(e.getMessage(), e);
        }
    }

    public List<Series> getPopularSeries() throws RequestException {
        try {
            Response<SeriesListResponseDto> popularResponse = this.tmdbApi.getPopularSeries("pt-BR", 1).execute();
            if (!popularResponse.isSuccessful())
                throw new RequestException("Falha no processo de busca por séries populares");

            SeriesListResponseDto data = popularResponse.body();

            List<Series> popularSeries = new ArrayList<>();

            for (SeriesFoundDto series : Objects.requireNonNull(data).getResults())
                popularSeries.add(
                    new Series(
                        series.getId(),
                        series.getName(),
                        new Image(
                            this.imageSettings,
                            series.getPoster_path()
                        ),
                        series.getOriginal_name(),
                        series.getOverview(),
                        series.getPopularity()
                    )
                );

            return popularSeries;

        } catch (IOException e) {
            throw new RequestException(e.getMessage(), e);
        }
    }

    public List<Series> getTopRatedSeries() throws RequestException {
        try {
            Response<SeriesListResponseDto> topRatedResponse = this.tmdbApi.getTopRatedSeries("pt-BR", 1).execute();
            if (!topRatedResponse.isSuccessful())
                throw new RequestException("Falha no processo de busca por séries melhores avaliadas");

            SeriesListResponseDto data = topRatedResponse.body();

            List<Series> topRatedSeries = new ArrayList<>();

            for (SeriesFoundDto series : Objects.requireNonNull(data).getResults())
                topRatedSeries.add(
                    new Series(
                        series.getId(),
                        series.getName(),
                        new Image(
                            this.imageSettings,
                            series.getPoster_path()
                        ),
                        series.getOriginal_name(),
                        series.getOverview(),
                        series.getVote_average(),
                        series.getVote_count()
                    )
                );

            return topRatedSeries;

        } catch (IOException e) {
            throw new RequestException(e.getMessage(), e);
        }
    }

}
