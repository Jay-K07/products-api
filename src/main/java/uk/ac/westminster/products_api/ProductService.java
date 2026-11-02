package uk.ac.westminster.products_api;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    public ProductService(){
        //both subclasses go into one List<Product>
        //no cast is needed because each is a product
        products.add(new DigitalProduct(
                nextId++, "Clean Code (E-Book)", 29.99,
                "/downlaods/clean-code.pdf", 4.2
        ));

        products.add(new PhysicalProduct(
                nextId++, "27-inch Monitor", 249.99,
                5.4,"61cm x 41cm x 18cm"
        ));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(Long id){
        return products.stream()
                        .filter(p -> p.getId().equals(id))
                        .findFirst();
    }

    public Product addProduct(Product product) {
        product.setId(nextId++); // use value then add one for next
        products.add(product);
        return product;
    }

    public void deleteProduct(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public List<Product> findByName(String name) {
        return products.stream()
                        .filter(p-> p.getName().contains(name))
                        .toList();
    }
}
