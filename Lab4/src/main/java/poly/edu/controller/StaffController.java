package poly.edu.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import poly.edu.model.Staff;

@Controller
@RequestMapping("/staff")
public class StaffController {

    // ================= Bài 1: Databinding =================
    @GetMapping("/create/form")
    public String createForm(Model model, @ModelAttribute("staff") Staff staff) {
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "/demo/staff-create";  // file staff-create.html
    }

    @PostMapping("/create/save")
    public String createSave(Model model,
                             @ModelAttribute("staff") Staff staff,
                             @RequestPart("photo_file") MultipartFile photoFile) {
        if (!photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getOriginalFilename());
        }
        model.addAttribute("message", "Xin chào " + staff.getFullname());
        return "/demo/staff-create";
    }

    // ================= Bài 2: Validation =================
    @GetMapping("/validate/form")
    public String validateForm(Model model, @ModelAttribute("staff") Staff staff) {
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "/demo/staff-validate";  // file staff-validate.html
    }

    @PostMapping("/validate/save")
    public String validateSave(Model model,
                               @RequestPart("photo_file") MultipartFile photoFile,
                               @Valid @ModelAttribute("staff") Staff staff,
                               Errors errors) {
        if (!photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getOriginalFilename());
        }

        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
        } else {
            model.addAttribute("message", "Dữ liệu đã nhập đúng! Xin chào " + staff.getFullname());
        }
        return "/demo/staff-validate";
    }
}
