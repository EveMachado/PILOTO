package ufu.piloto.service.api.tmdb;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBApi {
    @GET("configuration")
    Call<ResponseBody> getConfiguration();

    @GET("genre/tv/list")
    Call<ResponseBody> getGenres(@Query("language") String language);

    @GET("search/tv")
    Call<ResponseBody> searchSeries(@Query("query") String query, @Query("language") String language, @Query("page") int page, @Query("include_adult") boolean includeAdult);

    @GET("tv/{id}")
    Call<ResponseBody> getSeriesDetails(@Path("id") int seriesId, @Query("language") String language);

    @GET("tv/{id}/season/{season_number}")
    Call<ResponseBody> getSeasonDetails(@Path("id") int seriesId, @Path("season_number") int seasonNumber, @Query("language") String language);

    @GET("tv/{id}/watch/providers")
    Call<ResponseBody> getWatchProviders(@Path("id") int seriesId);

    @GET("discover/tv")
    Call<ResponseBody> discoverSeries(@Query("with_genres") String genreId, @Query("language") String language, @Query("page") int page, @Query("include_adult") boolean includeAdult);

    @GET("tv/popular")
    Call<ResponseBody> getPopularSeries(@Query("language") String language, @Query("page") int page);

    @GET("tv/top_rated")
    Call<ResponseBody> getTopRatedSeries(@Query("language") String language, @Query("page") int page);
}
