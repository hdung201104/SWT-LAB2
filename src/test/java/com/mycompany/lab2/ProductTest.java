package com.mycompany.lab2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    @DisplayName("Product: Tạo sản phẩm hợp lệ và kiểm tra getters")
    void testValidProduct() {
        Product p = new Product("P01", "Laptop", 1000.0, 5);
        assertEquals("P01", p.getProductId());
        assertEquals("Laptop", p.getProductName());
        assertEquals(1000.0, p.getPrice(), 0.001);
        assertEquals(5, p.getQuantity());
    }

    @Test
    @DisplayName("Product: Ném ngoại lệ khi Product ID null hoặc rỗng")
    void testInvalidProductId() {
        IllegalArgumentException ex1 = assertThrows(
                IllegalArgumentException.class,
                () -> new Product(null, "Laptop", 1000.0, 5)
        );
        assertEquals("Product ID is required", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(
                IllegalArgumentException.class,
                () -> new Product("", "Laptop", 1000.0, 5)
        );
        assertEquals("Product ID is required", ex2.getMessage());
    }

    @Test
    @DisplayName("Product: Ném ngoại lệ khi Product Name null hoặc rỗng")
    void testInvalidProductName() {
        IllegalArgumentException ex1 = assertThrows(
                IllegalArgumentException.class,
                () -> new Product("P01", null, 1000.0, 5)
        );
        assertEquals("Product name is required", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(
                IllegalArgumentException.class,
                () -> new Product("P01", "", 1000.0, 5)
        );
        assertEquals("Product name is required", ex2.getMessage());
    }

    @Test
    @DisplayName("Product: Ném ngoại lệ khi Price <= 0")
    void testInvalidPrice() {
        IllegalArgumentException ex1 = assertThrows(
                IllegalArgumentException.class,
                () -> new Product("P01", "Laptop", 0.0, 5)
        );
        assertEquals("Price must be greater than 0", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(
                IllegalArgumentException.class,
                () -> new Product("P01", "Laptop", -50.0, 5)
        );
        assertEquals("Price must be greater than 0", ex2.getMessage());
    }

    @Test
    @DisplayName("Product: Ném ngoại lệ khi Quantity <= 0")
    void testInvalidQuantity() {
        IllegalArgumentException ex1 = assertThrows(
                IllegalArgumentException.class,
                () -> new Product("P01", "Laptop", 1000.0, 0)
        );
        assertEquals("Quantity must be greater than 0", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(
                IllegalArgumentException.class,
                () -> new Product("P01", "Laptop", 1000.0, -2)
        );
        assertEquals("Quantity must be greater than 0", ex2.getMessage());
    }
}
