package poly.edu.controller;

import poly.edu.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductControllerbai4 {

    // Danh sách toàn cục
    private List<Product> items = new ArrayList<>();

    // Sản phẩm hiện tại (null nghĩa là chưa có)
    private Product currentProduct = null;

    // Khởi tạo danh sách mặc định
    public ProductControllerbai4() {
        items.add(new Product("iPhone 15", 2000.0));
        items.add(new Product("Samsung S30", 3000.0));
        items.add(new Product("Oppo Find X10", 1500.0));
    }

    // GET: hiển thị form
    @GetMapping("formbai4")
    public String form(Model model) {
        // Nếu chưa nhập lần nào thì hiển thị mặc định
        if (currentProduct == null) {
            currentProduct = new Product("iPhone 30", 5000.0);
        }
        model.addAttribute("product", currentProduct);
        model.addAttribute("items", items);
        return "formbai4";
    }

    // POST: lưu thông tin sản phẩm
    @PostMapping("savebai4")
    public String save(@ModelAttribute("product") Product p, Model model) {
        currentProduct = p;  // cập nhật thông tin sản phẩm hiển thị
        items.add(p);        // thêm vào danh sách

        model.addAttribute("product", currentProduct);
        model.addAttribute("items", items);
        return "formbai4";
    }
}
