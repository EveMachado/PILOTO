package ufu.piloto.service.api.tmdb;

import ufu.piloto.service.api.ApiService;

import java.util.HashMap;
import java.util.Map;

public class TMDBService extends ApiService {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String TOKEN = System.getenv("TMDB_API_TOKEN");

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

    public TMDBApi getApi() {
        return retrofit.create(TMDBApi.class);
    }
}
