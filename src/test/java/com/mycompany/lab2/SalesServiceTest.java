package com.mycompany.lab2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SalesServiceTest {

    private SalesService service;

    @BeforeEach
    void setUp() {
        service = new SalesService();
    }

    // ==========================================
    // BƯỚC 1: UNIT TEST CHO HÀM ĐẦU TIÊN: calculateSubtotal()
    // ==========================================

    @Test
    @DisplayName("calculateSubtotal: Tính thành tiền bình thường (giá 500, số lượng 2)")
    void testCalculateSubtotal_ValidProduct() {
        Product p = new Product("P01", "Laptop", 500.0, 2);
        // Mong đợi: price * quantity = 500 * 2 = 1000.0
        assertEquals(1000.0, service.calculateSubtotal(p), 0.001);
    }

    @Test
    @DisplayName("calculateSubtotal: Giá lẻ 1500.50, số lượng 3")
    void testCalculateSubtotal_DecimalPrice() {
        Product p = new Product("P02", "Phone", 1500.50, 3);
        // Mong đợi: 1500.50 * 3 = 4501.50
        assertEquals(4501.50, service.calculateSubtotal(p), 0.001);
    }

    @Test
    @DisplayName("calculateSubtotal: Ném ngoại lệ khi Product bị null")
    void testCalculateSubtotal_NullProduct_ThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.calculateSubtotal(null)
        );
        assertEquals("Product cannot be null", exception.getMessage());
    }
}
