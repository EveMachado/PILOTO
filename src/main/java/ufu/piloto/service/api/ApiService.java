package ufu.piloto.service.api;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ApiService {

    private static final Map<Class<? extends ApiService>, ApiService> instances = new HashMap<>();

    protected Retrofit retrofit;

    protected ApiService() {
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

        this.retrofit = new Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(client)
            .build();
    }

    public static <T extends ApiService> T getInstance(Class<T> serviceClass) {
        if (!instances.containsKey(serviceClass)) {
            try {
                instances.put(serviceClass, serviceClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException("Falha na inicialização do serviço!", e);
            }
        }
        return serviceClass.cast(instances.get(serviceClass));
    }


    protected abstract String getBaseUrl();
    protected abstract Map<String, String> getHeaders();


}
