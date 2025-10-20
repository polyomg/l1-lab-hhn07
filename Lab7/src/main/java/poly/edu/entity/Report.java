package poly.edu.entity;

import java.io.Serializable;

public interface Report {
    Serializable getGroup();  // dùng Serializable để group by
    Double getSum();
    Long getCount();
}
