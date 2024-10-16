package mx.uvm.anuar.ecommerce_platform.repository;

import mx.uvm.anuar.ecommerce_platform.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{



}
