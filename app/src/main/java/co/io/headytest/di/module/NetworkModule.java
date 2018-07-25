package co.io.headytest.di.module;

import dagger.Module;
import dagger.Provides;
import co.io.headytest.data.rest.RepoService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

   // private static final String BASE_URL = "https://api.github.com/";

    private static final String BASE_URL = "https://stark-spire-93433.herokuapp.com/";

    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    static RepoService provideRepoService(Retrofit retrofit) {
        return retrofit.create(RepoService.class);
    }

}
