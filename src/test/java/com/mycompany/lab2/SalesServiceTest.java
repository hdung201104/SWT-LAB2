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
    // 1. UNIT TEST CHO HÀM 1: calculateSubtotal()
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

    // ==========================================
    // 2. UNIT TEST CHO HÀM 2: calculateDiscount() (6 BOUNDARY VALUES)
    // ==========================================

    @Test
    @DisplayName("calculateDiscount Boundary 1: subtotal 999.99 -> Giảm 0% (0.0)")
    void testCalculateDiscount_Boundary_999_99() {
        // subtotal < 1000 -> 0%
        assertEquals(0.0, service.calculateDiscount(999.99), 0.001);
    }

    @Test
    @DisplayName("calculateDiscount Boundary 2: subtotal 1000.0 -> Giảm 5% (50.0)")
    void testCalculateDiscount_Boundary_1000_0() {
        // 1000 <= subtotal < 5000 -> 5% (1000 * 0.05 = 50.0)
        assertEquals(50.0, service.calculateDiscount(1000.0), 0.001);
    }

    @Test
    @DisplayName("calculateDiscount Boundary 3: subtotal 4999.99 -> Giảm 5% (249.9995)")
    void testCalculateDiscount_Boundary_4999_99() {
        // 4999.99 * 0.05 = 249.9995
        assertEquals(249.9995, service.calculateDiscount(4999.99), 0.001);
    }

    @Test
    @DisplayName("calculateDiscount Boundary 4: subtotal 5000.0 -> Giảm 10% (500.0)")
    void testCalculateDiscount_Boundary_5000_0() {
        // 5000 <= subtotal < 10000 -> 10% (5000 * 0.10 = 500.0)
        assertEquals(500.0, service.calculateDiscount(5000.0), 0.001);
    }

    @Test
    @DisplayName("calculateDiscount Boundary 5: subtotal 9999.99 -> Giảm 10% (999.999)")
    void testCalculateDiscount_Boundary_9999_99() {
        // 9999.99 * 0.10 = 999.999
        assertEquals(999.999, service.calculateDiscount(9999.99), 0.001);
    }

    @Test
    @DisplayName("calculateDiscount Boundary 6: subtotal 10000.0 -> Giảm 15% (1500.0)")
    void testCalculateDiscount_Boundary_10000_0() {
        // subtotal >= 10000 -> 15% (10000 * 0.15 = 1500.0)
        assertEquals(1500.0, service.calculateDiscount(10000.0), 0.001);
    }
}
