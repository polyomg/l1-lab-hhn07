package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rectangle")
public class RectangleController {

    @GetMapping("/form")
    public String form() {
        return "rectangle"; // trả về template rectangle.html
    }

    @PostMapping("/calc")
    public String calc(@RequestParam("length") String lengthStr,
                       @RequestParam("width") String widthStr,
                       Model model) {
        try {
            double l = Double.parseDouble(lengthStr);
            double w = Double.parseDouble(widthStr);

            if (l <= 0 || w <= 0) {
                model.addAttribute("error", "Chiều dài và chiều rộng phải là số dương.");
                return "rectangle";
            }

            double area = l * w;
            double perimeter = 2 * (l + w);

            model.addAttribute("length", l);
            model.addAttribute("width", w);
            model.addAttribute("area", area);
            model.addAttribute("perimeter", perimeter);
        } catch (NumberFormatException ex) {
            model.addAttribute("error", "Giá trị nhập không hợp lệ.");
        }

        return "rectangle";
    }
}
