package poly.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest request;

    // Đọc chuỗi giá trị của tham số
    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    // Đọc số nguyên giá trị của tham số
    public int getInt(String name, int defaultValue) {
        try {
            return Integer.parseInt(request.getParameter(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // Đọc số thực giá trị của tham số
    public double getDouble(String name, double defaultValue) {
        try {
            return Double.parseDouble(request.getParameter(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // Đọc giá trị boolean của tham số
    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        if (value == null) return defaultValue;
        return Boolean.parseBoolean(value);
    }

    // Đọc giá trị thời gian của tham số
    public Date getDate(String name, String pattern) {
        try {
            String value = request.getParameter(name);
            if (value == null) return null;
            return new SimpleDateFormat(pattern).parse(value);
        } catch (Exception e) {
            throw new RuntimeException("Sai định dạng thời gian: " + pattern);
        }
    }

    // Lưu file upload vào thư mục
    public File save(MultipartFile file, String path) {
        if (file.isEmpty()) return null;
        try {
            String realPath = request.getServletContext().getRealPath(path);
            File dir = new File(realPath);
            if (!dir.exists()) dir.mkdirs();
            File savedFile = new File(dir, file.getOriginalFilename());
            file.transferTo(savedFile);
            return savedFile;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi lưu file: " + e.getMessage());
        }
    }
}
