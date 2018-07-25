package co.io.headytest.data.rest;

import java.util.List;

import io.reactivex.Single;
import co.io.headytest.data.model.ProductResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoService {

    @GET("json")
    Single<ProductResponse> getProducts();
}
