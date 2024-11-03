package ufu.piloto.service.api.tmdb;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import ufu.piloto.dto.series.detail.season.SeasonDetailDto;
import ufu.piloto.dto.series.detail.series.SeriesDetailDto;
import ufu.piloto.dto.series.detail.streaming.StreamingProviderResponseDto;
import ufu.piloto.dto.series.genre.GenresResponseDto;
import ufu.piloto.dto.series.list.SeriesListResponseDto;
import ufu.piloto.dto.settings.configuration.ConfigurationDto;

public interface TmdbApi {
    @GET("configuration")
    Call<ConfigurationDto> getConfiguration();

    @GET("genre/tv/list")
    Call<GenresResponseDto> getGenres(@Query("language") String language);

    @GET("search/tv")
    Call<SeriesListResponseDto> searchSeries(@Query("query") String query, @Query("language") String language, @Query("page") int page, @Query("include_adult") boolean includeAdult);

    @GET("tv/{id}")
    Call<SeriesDetailDto> getSeriesDetails(@Path("id") String seriesId, @Query("language") String language);

    @GET("tv/{id}/season/{season_number}")
    Call<SeasonDetailDto> getSeasonDetails(@Path("id") String seriesId, @Path("season_number") String seasonNumber, @Query("language") String language);

    @GET("tv/{id}/watch/providers")
    Call<StreamingProviderResponseDto> getStreamingProviders(@Path("id") String seriesId);

    @GET("discover/tv")
    Call<SeriesListResponseDto> discoverSeries(@Query("with_genres") String genreId, @Query("language") String language, @Query("page") int page, @Query("include_adult") boolean includeAdult);

    @GET("tv/popular")
    Call<SeriesListResponseDto> getPopularSeries(@Query("language") String language, @Query("page") int page);

    @GET("tv/top_rated")
    Call<SeriesListResponseDto> getTopRatedSeries(@Query("language") String language, @Query("page") int page);
}
