package studio.maxdev.jureka_api.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import studio.maxdev.jureka_api.exception.FileStorageException;
import studio.maxdev.jureka_api.exception.ProductNotFoundException;
import studio.maxdev.jureka_api.model.Product;
import studio.maxdev.jureka_api.repository.ProductRepository;
import studio.maxdev.jureka_api.exception.BadRequestException;

import java.io.IOException;
import java.util.List;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProductsByKeyword(String keyword){
        if(keyword == null || keyword.isBlank()){
            return getAllProducts();
        }

        return productRepository.findByKeyword(keyword);
    }

    public Product getProductById(int id) {
        System.out.println("called");
        return productRepository
                    .findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product addProduct(Product product, MultipartFile file)  {
        if(product == null){
            throw new BadRequestException("Product data is missing.");
        }

        if(file != null) {
            setImageData(product, file);
        }

        return productRepository.save(product);
    }

    public Product updateProduct(Product product, MultipartFile file){
        if(product == null){
            throw new BadRequestException("Product cannot be null!!!");
        }

        int productId = product.getId();
        if(!productRepository.existsById(productId)){
            throw new ProductNotFoundException(productId);
        }

        if(file != null) {
            setImageData(product, file);
        }

        return productRepository.save(product);
    }

    public void deleteProductById(int id){
        productRepository.deleteById(id);
    }

    private void setImageData(Product product, MultipartFile file){
        try{
            product.setImageName(file.getOriginalFilename());
            product.setImageType(file.getContentType());
            product.setImageData(file.getBytes());
        } catch (IOException e){
            throw new FileStorageException("Failed to process image data");
        }
    }
}