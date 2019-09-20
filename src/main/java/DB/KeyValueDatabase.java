package DB;

import java.sql.*;

public class KeyValueDatabase {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        KeyValueDatabase keyValueDatabase = new KeyValueDatabase();
//        keyValueDatabase.insert("drink", "tea", System.currentTimeMillis());
        keyValueDatabase.selectAll();
    }

    /**
     * Connect to a sample database
     */
    public Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:/Users/sangeethasampathkumar/test/kv.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a new row into the warehouses table
     *
     * @param key
     * @param value
     */
    public void insert(String key, String value, long timestamp) {
        System.out.println("inside insert");
        String sql = "INSERT INTO kvtable(key, value, timestamp) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, key);
            pstmt.setString(2, value);
            pstmt.setLong(3, timestamp);
            System.out.println("b4 exec");
            int result = pstmt.executeUpdate();
            System.out.println("res: " + result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * select all
     */
    public void selectAll() {
        String sql = "SELECT *  FROM kvtable";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("key") + "\t" +
                        rs.getString("value") + "\t" +
                        rs.getLong("timestamp"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}