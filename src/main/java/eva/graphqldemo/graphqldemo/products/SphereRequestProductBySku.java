package eva.graphqldemo.graphqldemo.products;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import io.sphere.sdk.client.HttpRequestIntent;
import io.sphere.sdk.client.SphereRequest;
import io.sphere.sdk.http.HttpMethod;
import io.sphere.sdk.http.HttpResponse;
import io.sphere.sdk.json.SphereJsonUtils;
import io.sphere.sdk.models.Base;
import org.apache.commons.lang3.Validate;

import javax.annotation.Nullable;

public class SphereRequestProductBySku extends Base implements SphereRequest<MyGraphQLProduct> {


    final private String sku;

    public SphereRequestProductBySku(String sku){
        Validate.notEmpty(sku);
        this.sku = sku;
    }

    @Nullable
    @Override
    public MyGraphQLProduct deserialize(HttpResponse httpResponse) {
        final JsonNode productResult = SphereJsonUtils.parse(httpResponse.getResponseBody())
                .get("data").get("product");

        return SphereJsonUtils.readObject(productResult, new TypeReference<MyGraphQLProduct>() { });
    }

    @Override
    public HttpRequestIntent httpRequestIntent() {
        final String queryString = String.format("query productBySku($sku: String!) {\n" +
                "       product(sku: $sku) {\n" +
                "               id\n" +
                "               masterData {\n" +
                "                   current {\n" +
                "                       masterVariant {\n" +
                "                           sku\n" +
                "                       }"+
                "                   }\n" +
                "               }\n" +
                "       }\n" +
                "   }");
        final String body = String.format(
                "{\n" +
                        "   \"query\": \"%s\", \n" +
                        "   \"variables\": { \"sku\": \"%s\" }\n" +
                        "}",
                queryString.replace("\n", "\\n")
                        .replace("\"", "\\\""),
                sku);
        return HttpRequestIntent.of(HttpMethod.POST, "/graphql", body);
    }
}
