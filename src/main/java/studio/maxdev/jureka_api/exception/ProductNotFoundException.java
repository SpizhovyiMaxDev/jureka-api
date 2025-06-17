package studio.maxdev.jureka_api.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(int id){
        super("🚨Product with id: " + id + " was not found!");
    }
}

