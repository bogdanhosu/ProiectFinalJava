/* PARTEA A II-A :
----------------------------------------CERINȚĂ------------------------------------------------------------------------

Implementează o mini aplicație în care simulezi un magazin de produse. În
implementarea aplicației de față se urmărește gestiunea unor produse ce dețin ca
informații: nume produs, preț, cantitate. Se va crea un serviciu prin care se va
gestiona un inventar de produse și se vor urmări metode precum: Afișarea
produselor, adăugarea produselor, ștergerea produselor, modificarea prețurilor unor
produse, modificarea cantității unor produse*/

/*Explicații:
----Clasa Product: Definește atributele name, price și quantity și metodele necesare pentru a accesa și modifica aceste atribute.
----Clasa ProductService: Conține o listă de produse și metode pentru a adăuga, șterge, afișa și modifica produsele din inventar.
----Clasa Main: Folosită pentru a testa funcționalitățile serviciului de produse.
----Această implementare oferă o bază simplă pentru gestionarea unui inventar de produse și poate fi extinsă cu funcționalități
suplimentare în funcție de necesități.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Here I have added profuses with a demo role (just to see if the program works and start with the name "Demo")
        productService.addProduct(new Product(1, "DemoApple", 1.5, 200, "USA"));
        productService.addProduct(new Product(2, "DemoBanana", 0.75, 200, "Brazilia"));
        productService.addProduct(new Product(3, "DemoCirese", 0.75, 200, "Romania"));
        productService.addProduct(new Product(4, "DemoClatiteSemiPreparate", 12, 200, "Germania"));
        productService.addProduct(new Product(5, "DemoStickUsb64Gb", 250, 200, "India"));
        productService.addProduct(new Product(6, "DemoBricheta", 0.75, 500, "China"));

        while (!exit) {
            showMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Displaying all products:");
                    productService.displayProducts(); // This is how all the products in the database will be displayed
                    break;

                case 2:
                    System.out.print("Enter product ID to update price: "); // If you want to update the price
                    int productId = scanner.nextInt(); //enter the product ID
                    if (productService.productExists(productId)) {
                        System.out.print("Enter new price: ");
                        double newPrice = scanner.nextDouble(); // Enter the new price of the product
                        productService.updateProductPrice(productId, newPrice);
                        System.out.println("Updated product price.");
                    } else {
                        System.out.println("Product ID does not exist. Please Restart the operation");
                    }
                    break;

                case 3:
                    System.out.print("Enter product ID to update quantity: "); // If you want to update the quantity
                    int updateId = scanner.nextInt(); // Enter the product ID
                    if (productService.productExists(updateId)) {
                        System.out.print("Enter new quantity: ");
                        int newQuantity = scanner.nextInt(); // Enter the new quantity of the product
                        productService.updateProductQuantity(updateId, newQuantity);
                        System.out.println("Updated product quantity.");
                    } else {
                        System.out.println("Product ID does not exist.");
                    }
                    break;

                case 4:
                    System.out.print("Enter product ID to remove: "); // If you want to delete a product
                    int removeId = scanner.nextInt(); // Enter the product ID
                    if (productService.productExists(removeId)) {
                        productService.removeProduct(removeId);
                        System.out.println("Removed product.");
                    } else {
                        System.out.println("Product ID does not exist.");
                    }
                    break;

                case 5:
                    System.out.println("Deleting all products...");
                    productService.deleteAllProducts();
                    System.out.println("All products have been deleted.");
                    break;

                case 6: // doar pentru iesirea din program
                    exit = true;
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\nProduct Management Menu:");
        System.out.println("1. Display all products");
        System.out.println("2. Update product price");
        System.out.println("3. Update product quantity");
        System.out.println("4. Remove a product");
        System.out.println("5. Delete all products");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }
}
