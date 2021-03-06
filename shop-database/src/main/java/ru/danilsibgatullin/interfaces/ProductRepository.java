package ru.danilsibgatullin.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.danilsibgatullin.models.Product;


public interface ProductRepository extends JpaRepository<Product,Long> , JpaSpecificationExecutor<Product> {

}
