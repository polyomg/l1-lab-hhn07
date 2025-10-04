package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import poly.edu.entity.Product;

@Controller
public class ProductController {

    @GetMapping("formbai3")
    public String form(Model model) {
        // khởi tạo product mặc định
        model.addAttribute("product", new Product("iPhone 20", 4000.0));
        return "formbai3";
    }

    @PostMapping("savebai3")
    public String save(@ModelAttribute("product") Product product, Model model) {
        // giữ lại product vừa nhập
        model.addAttribute("product", product);
        return "formbai3";
    }
}
