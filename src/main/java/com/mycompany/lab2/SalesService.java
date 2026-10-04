package com.mycompany.lab2;

public class SalesService {

    public double calculateSubtotal(Product product) {
        if (product == null) {
            throw new IllegalArgumentException(
                    "Product cannot be null");
        }

        // Đã sửa B01
        return product.getPrice() * product.getQuantity();
    }

    public double calculateDiscount(double subtotal) {
        if (subtotal < 0) {
            throw new IllegalArgumentException(
                    "Subtotal cannot be negative");
        }

        if (subtotal < 1000) {
            return 0;
        } else if (subtotal < 5000) {
            // Đã sửa B02
            return subtotal * 0.05;
        } else if (subtotal < 10000) {
            return subtotal * 0.10;
        } else {
            return subtotal * 0.15;
        }
    }

    public double calculateShippingFee(double subtotal) {
        if (subtotal < 0) {
            throw new IllegalArgumentException(
                    "Subtotal cannot be negative");
        }

        // Đã sửa B03
        if (subtotal < 2000) {
            return 50;
        }

        return 0;
    }

    public double calculateTotal(Product product) {
        double subtotal = calculateSubtotal(product);

        double discount = calculateDiscount(subtotal);

        double shipping = calculateShippingFee(subtotal);

        // SỬA ĐÚNG B04: Đổi '+ discount' thành '- discount + shipping'
        return subtotal - discount + shipping;
    }

    public String classifyCustomer(double total) {
        if (total < 1000) {
            return "REGULAR";
        } else if (total < 5000) {
            return "SILVER";
        } else if (total <= 10000) {
            return "GOLD";
        } else {
            return "VIP";
        }
    }
}
