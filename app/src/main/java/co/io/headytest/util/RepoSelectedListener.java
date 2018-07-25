package co.io.headytest.util;

import co.io.headytest.data.model.ProductResponse;

public interface RepoSelectedListener {

    void onRepoSelected(ProductResponse.Category repo);

    void onProductSelected(ProductResponse.Product product);
}
