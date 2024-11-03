package ufu.piloto.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ufu.piloto.adapter.LocalDateAdapter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public abstract class ApiService {

    private static final Map<Class<? extends ApiService>, ApiService> instances = new HashMap<>();

    protected Retrofit retrofit;

    protected ApiService() {

    }

    @SneakyThrows
    public static ApiService getInstance(Class<? extends ApiService> serviceClass) {
        if (!instances.containsKey(serviceClass)) {
            var constructor = serviceClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            ApiService service = constructor.newInstance();
            service.intiRetrofit();
            service.apiCreate();
            instances.put(serviceClass, service);
        }

        return serviceClass.cast(instances.get(serviceClass));
    }

    protected final void intiRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder requestBuilder = chain.request().newBuilder();

                        for (Map.Entry<String, String> header : getHeaders().entrySet())
                            requestBuilder.header(header.getKey(), header.getValue());

                        return chain.proceed(requestBuilder.build());
                    }
                }
            )
            .build();

        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

        this.retrofit = new Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    }

    protected abstract String getBaseUrl();

    protected abstract Map<String, String> getHeaders();

    protected abstract void apiCreate();

}
