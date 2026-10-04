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
        assertEquals(0.0, service.calculateDiscount(999.99), 0.001);
    }

    @Test
    @DisplayName("calculateDiscount Boundary 2: subtotal 1000.0 -> Giảm 5% (50.0)")
    void testCalculateDiscount_Boundary_1000_0() {
        assertEquals(50.0, service.calculateDiscount(1000.0), 0.001);
    }

    @Test
    @DisplayName("calculateDiscount Boundary 3: subtotal 4999.99 -> Giảm 5% (249.9995)")
    void testCalculateDiscount_Boundary_4999_99() {
        assertEquals(249.9995, service.calculateDiscount(4999.99), 0.001);
    }

    @Test
    @DisplayName("calculateDiscount Boundary 4: subtotal 5000.0 -> Giảm 10% (500.0)")
    void testCalculateDiscount_Boundary_5000_0() {
        assertEquals(500.0, service.calculateDiscount(5000.0), 0.001);
    }

    @Test
    @DisplayName("calculateDiscount Boundary 5: subtotal 9999.99 -> Giảm 10% (999.999)")
    void testCalculateDiscount_Boundary_9999_99() {
        assertEquals(999.999, service.calculateDiscount(9999.99), 0.001);
    }

    @Test
    @DisplayName("calculateDiscount Boundary 6: subtotal 10000.0 -> Giảm 15% (1500.0)")
    void testCalculateDiscount_Boundary_10000_0() {
        assertEquals(1500.0, service.calculateDiscount(10000.0), 0.001);
    }

    // ==========================================
    // 3. UNIT TEST CHO HÀM 3: calculateShippingFee() (Ít nhất 3 tests)
    // ==========================================

    @Test
    @DisplayName("calculateShippingFee: subtotal < 2000 (1999.99) -> Phí giao hàng 50.0")
    void testCalculateShippingFee_Under2000() {
        assertEquals(50.0, service.calculateShippingFee(1999.99), 0.001);
    }

    @Test
    @DisplayName("calculateShippingFee: subtotal == 2000.0 -> Miễn phí giao hàng (0.0)")
    void testCalculateShippingFee_Exactly2000() {
        assertEquals(0.0, service.calculateShippingFee(2000.0), 0.001);
    }

    @Test
    @DisplayName("calculateShippingFee: subtotal > 2000 (5000.0) -> Miễn phí giao hàng (0.0)")
    void testCalculateShippingFee_Over2000() {
        assertEquals(0.0, service.calculateShippingFee(5000.0), 0.001);
    }

    // ==========================================
    // 4. UNIT TEST CHO HÀM 4: calculateTotal() (Ít nhất 2 tests)
    // ==========================================

    @Test
    @DisplayName("calculateTotal: subtotal 1000, discount 50, shipping 50 -> total = 1000 - 50 + 50 = 1000.0")
    void testCalculateTotal_StandardProduct() {
        Product p = new Product("P01", "Monitor", 500.0, 2);
        // subtotal = 1000.0, discount = 50.0, shipping = 50.0
        // Mong đợi = 1000 - 50 + 50 = 1000.0
        assertEquals(1000.0, service.calculateTotal(p), 0.001);
    }

    @Test
    @DisplayName("calculateTotal: subtotal 6000, discount 600, shipping 0 -> total = 6000 - 600 + 0 = 5400.0")
    void testCalculateTotal_HighValueProduct() {
        Product p = new Product("P02", "HighEnd Laptop", 3000.0, 2);
        // subtotal = 6000.0, discount = 600.0, shipping = 0.0
        // Mong đợi = 6000 - 600 + 0 = 5400.0
        assertEquals(5400.0, service.calculateTotal(p), 0.001);
    }
}
