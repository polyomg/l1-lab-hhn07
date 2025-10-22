package poly.edu.DAO;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.edu.entity.Product;
import poly.edu.entity.Report;

public interface ProductDAO extends JpaRepository<Product, Integer> {

	// Bài 4: Dùng DSL thay vì JPQL (thay bài 1)
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // Bài 2: Tìm theo từ khóa + phân trang (thay bài 5)
    @Query("SELECT p FROM Product p WHERE p.name LIKE ?1")
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);
    
    // Bài 3: Báo cáo tồn kho theo loại hàng
    @Query("SELECT o.category AS group, " +
           "SUM(o.price) AS sum, " +
           "COUNT(o) AS count " +
           "FROM Product o " +
           "GROUP BY o.category " +
           "ORDER BY SUM(o.price) DESC")
    List<Report> getInventoryByCategory();
}
