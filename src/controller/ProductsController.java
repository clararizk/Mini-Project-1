package com.example.miniproject2.controller;

import com.example.miniproject2.model.Product;
import com.example.miniproject2.store.ProductsStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductsController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.setItems(ProductsStore.getProducts());

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> {
            if (selected != null) {
                idField.setText(String.valueOf(selected.getId()));
                nameField.setText(selected.getName());
                priceField.setText(String.valueOf(selected.getPrice()));
            }
        });
    }

    @FXML
    private void addProduct() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());

            ProductsStore.addProduct(new Product(id, name, price));
            clearFields();
            messageLabel.setText("Product added");
        } catch (Exception e) {
            messageLabel.setText("Invalid input");
        }
    }

    @FXML
    private void updateProduct() {
        Product selected = tableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            try {
                selected.setId(Integer.parseInt(idField.getText()));
                selected.setName(nameField.getText());
                selected.setPrice(Double.parseDouble(priceField.getText()));

                tableView.refresh();
                clearFields();
                messageLabel.setText("Product updated");
            } catch (Exception e) {
                messageLabel.setText("Invalid input");
            }
        } else {
            messageLabel.setText("Select a product first");
        }
    }

    @FXML
    private void deleteProduct() {
        Product selected = tableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            ProductsStore.deleteProduct(selected);
            clearFields();
            messageLabel.setText("Product deleted");
        } else {
            messageLabel.setText("Select a product first");
        }
    }

    @FXML
    private void clearFields() {
        idField.clear();
        nameField.clear();
        priceField.clear();
        tableView.getSelectionModel().clearSelection();
        messageLabel.setText("");
    }

    @FXML
    private void backHome(ActionEvent event) {
        messageLabel.setText("Home view not added yet");
    }
}