package eva.graphqldemo.graphqldemo.products;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyGraphQLProduct {

        private String id;


        public MyGraphQLProduct(){}

        @JsonCreator
        public static MyGraphQLProduct createGraphQLProduct(
                @JsonProperty("id") String id
        ){
            MyGraphQLProduct myGraphQLProduct = new MyGraphQLProduct();

            myGraphQLProduct.id= id;

            return myGraphQLProduct;
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
