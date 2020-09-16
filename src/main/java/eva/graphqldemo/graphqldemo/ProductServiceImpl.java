package eva.graphqldemo.graphqldemo;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.products.search.ProductProjectionSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService {


    private final SphereClient client;

    @Autowired
    public ProductServiceImpl(SphereClient client) {
        this.client = client;
    }

    @Override
    public ProductProjection findBySKU(String sku) throws ExecutionException, InterruptedException {

        return client.execute(ProductProjectionSearch
                .ofCurrent().bySku(sku))
                .toCompletableFuture()
                .get()
                .getResults()
                .get(0);
    }


    @Override
    public ProductProjection findBySKUGraphQL(String sku) {
        return null;
    }


}
