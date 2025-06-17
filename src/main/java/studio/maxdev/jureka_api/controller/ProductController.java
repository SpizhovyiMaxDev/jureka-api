package studio.maxdev.jureka_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import studio.maxdev.jureka_api.model.Product;
import studio.maxdev.jureka_api.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> fetchProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") int id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product.getImageData());
    }

    @GetMapping(value = "/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("keyword") String keyword){
        List<Product> products = productService.getProductsByKeyword(keyword);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> postProduct(
            @RequestPart Product product,
            @RequestPart(required = false) MultipartFile file
    ){
       Product savedProduct = productService.addProduct(product, file);
       return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") int id,
            @RequestPart Product product,
            @RequestPart(required = false) MultipartFile file
    ){
        product.setId(id);
        Product updatedProduct = productService.updateProduct(product, file);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product was deleted successfully");
    }
}
