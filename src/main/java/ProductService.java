import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ProductService implements RedButton {
    private static final String URL = "jdbc:mysql://localhost:3306/InventoryDB";// Update with your URL/location for database
    private static final String USER = "root"; // Update with your MySQL user
    private static final String PASSWORD = "ProiectFinal"; // Update with your MySQL password

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // add a product ...
    public void addProduct(Product product) {
        String sql = "INSERT INTO Products (id, name, price, quantity, origin) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, product.getId());
            pstmt.setString(2, product.getName());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getQuantity());
            pstmt.setString(5, product.getOrigin());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // Add productExists method
    public boolean productExists(int productId) {
        String sql = "SELECT COUNT(*) FROM Products WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM Products WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("origin")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void removeProduct(int productId) {
        String sql = "DELETE FROM Products WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayProducts() {
        String sql = "SELECT * FROM Products";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(
                        "Product{" +
                                "id=" + rs.getInt("id") +
                                ", name='" + rs.getString("name") + '\'' +
                                ", price=" + rs.getDouble("price") +
                                ", quantity=" + rs.getInt("quantity") +
                                ", origin='" + rs.getString("origin") + '\'' +
                                '}'
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProductPrice(int productId, double newPrice) {
        String sql = "UPDATE Products SET price = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProductQuantity(int productId, int newQuantity) {
        String sql = "UPDATE Products SET quantity = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteAllProducts() {
        String sql = "DELETE FROM Products";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("All products deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting products: " + e.getMessage());
        }
    }


}
