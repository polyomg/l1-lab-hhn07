package poly.edu.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import poly.edu.model.Account;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();

        // Lưu URI đang truy cập để redirect lại sau đăng nhập
        session.setAttribute("securityUri", uri);

        Account user = (Account) session.getAttribute("user");

        // 1️⃣ Nếu chưa đăng nhập
        if (user == null) {
            session.setAttribute("message", "Vui lòng đăng nhập trước khi truy cập!");
            response.sendRedirect("/auth/login");
            return false;
        }

        // 2️⃣ Nếu vào /admin mà không phải admin
        if (uri.startsWith("/admin") && !isAdmin(user)) {
            session.setAttribute("message", "Bạn không có quyền truy cập trang quản trị!");
            response.sendRedirect("/auth/login");
            return false;
        }

        return true;
    }

    private boolean isAdmin(Account user) {
        // Ở đây kiểm tra vai trò admin (ví dụ: username = admin)
        return user.getUsername().equalsIgnoreCase("admin");
    }
}
