package eva.graphqldemo.graphqldemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping("/myProduct")
    public ResponseEntity getMyProduct(@RequestParam("sku") String sku) throws ExecutionException, InterruptedException {

        return new ResponseEntity<>( (productService.findBySKU(sku)), HttpStatus.OK);
    }



    @GetMapping("/myGraphQLProduct")
    public ResponseEntity getMyGraphQLProduct(@RequestParam("sku") String sku) throws ExecutionException, InterruptedException {

        return new ResponseEntity<> ( productService.findBySKUGraphQL(sku), HttpStatus.OK);
    }


}
