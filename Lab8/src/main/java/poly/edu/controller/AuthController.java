package poly.edu.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.model.Account;
import poly.edu.service.AccountService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AccountService accountService;

    @GetMapping("/login")
    public String loginForm(HttpSession session, Model model) {
        Object msg = session.getAttribute("message");
        if (msg != null) {
            model.addAttribute("message", msg);
            session.removeAttribute("message");
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginProcess(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        Account acc = accountService.findById(username);
        if (acc == null || !acc.getPassword().equals(password)) {
            model.addAttribute("message", "Sai tài khoản hoặc mật khẩu!");
            return "auth/login";
        }

        // ✅ Lưu user vào session
        session.setAttribute("user", acc);

        // ✅ Quay lại trang bị chặn trước đó (nếu có)
        String securityUri = (String) session.getAttribute("securityUri");
        if (securityUri != null) {
            session.removeAttribute("securityUri");
            return "redirect:" + securityUri;
        }

        // ✅ Mặc định vào trang admin home
        return "redirect:/admin/home/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
