package eva.graphqldemo.graphqldemo;

import eva.graphqldemo.graphqldemo.products.MyGraphQLProduct;
import eva.graphqldemo.graphqldemo.products.MyProduct;
import io.sphere.sdk.products.ProductProjection;

import java.util.concurrent.ExecutionException;

public interface ProductService {

    ProductProjection findBySKU(String sku) throws ExecutionException, InterruptedException;

    ProductProjection findBySKUGraphQL(String sku);
}
