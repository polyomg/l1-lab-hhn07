package poly.edu.model;

import lombok.Data;

@Data
public class Mail {
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private String filenames; // danh sách file đính kèm, ngăn cách bằng dấu , hoặc ;
}
