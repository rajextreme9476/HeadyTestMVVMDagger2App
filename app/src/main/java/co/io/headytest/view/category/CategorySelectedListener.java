package co.io.headytest.view.category;

import co.io.headytest.data.model.ProductResponse;

public interface CategorySelectedListener {

    void onCategorySelected(ProductResponse.Category category);
}
