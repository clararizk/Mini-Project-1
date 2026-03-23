package com.example.miniproject2.store;

import com.example.miniproject2.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductsStore {

    private static final ObservableList<Product> products =
            FXCollections.observableArrayList();

    public static ObservableList<Product> getProducts() {
        return products;
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void deleteProduct(Product product) {
        products.remove(product);
    }
}