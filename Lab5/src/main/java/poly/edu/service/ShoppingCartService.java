package poly.edu.service;

import java.util.Collection;

import poly.edu.model.Item;

public interface ShoppingCartService {
    Item add(Integer id);          // thêm mặt hàng hoặc tăng số lượng
    void remove(Integer id);       // xóa mặt hàng
    Item update(Integer id, int qty); // cập nhật số lượng
    void clear();                  // xóa sạch giỏ hàng
    Collection<Item> getItems();   // lấy tất cả mặt hàng
    int getCount();                // tổng số lượng
    double getAmount();            // tổng tiền
}
