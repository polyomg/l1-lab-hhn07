package poly.edu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "accounts") // ✅ khớp với tên bảng trong SQL Server
public class Account {
    @Id
    private String username;
    private String password;
    private String fullname;
    private String email;
    private boolean admin; // ✅ thêm trường này nếu bạn cần phân quyền
}
