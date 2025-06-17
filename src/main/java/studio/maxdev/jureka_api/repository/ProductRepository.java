package studio.maxdev.jureka_api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studio.maxdev.jureka_api.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("""
        SELECT p FROM Product p WHERE
        LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Product> findByKeyword(@Param("keyword") String keyword);
}