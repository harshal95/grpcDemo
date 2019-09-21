package DB;

import java.sql.*;

public class KeyValueDatabase {
    private static final String URL = "jdbc:sqlite:/Users/sangeethasampathkumar/test/";
    Integer rank;
    Connection conn;

    public KeyValueDatabase(Integer rank) {
        this.rank = rank;
        String databaseName = "kvDB" + rank + ".db";
        String connectionURL = URL + databaseName;
        conn = createNewDatabase(connectionURL);
        createNewTable();
    }

    public Connection createNewDatabase(String url) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("A new database has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void createNewTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS kvtable (\n"
                + "    key NVARCHAR(200) PRIMARY KEY  NOT NULL,\n"
                + "    value NVARCHAR(3000)  NOT NULL,\n"
                + "    timestamp INTEGER\n"
                + ");";

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoTable(String key, String value, long timestamp) {
        System.out.println("inside insert");
        String sql = "INSERT INTO kvtable(key, value, timestamp) VALUES(?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
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

//    public void selectAll() {
//        String sql = "SELECT *  FROM kvtable";
//
//        try (Connection conn = this.connect();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            // loop through the result set
//            while (rs.next()) {
//                System.out.println(rs.getString("key") + "\t" +
//                        rs.getString("value") + "\t" +
//                        rs.getLong("timestamp"));
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}