package mx.uvm.anuar.ecommerce_platform.service;

import mx.uvm.anuar.ecommerce_platform.model.Product;

import java.util.Optional;

public interface ProductService {

    public Product save(Product product);
    public Optional<Product> get(Integer id); // Optional allows us to verify in the DB if the object exists
    public void update(Product product);
    public void delete(Integer id);

}
