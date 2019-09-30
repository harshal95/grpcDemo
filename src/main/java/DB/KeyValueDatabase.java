package DB;

import com.grpc.sample.KvStore;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KeyValueDatabase {
    private static final String URL = "jdbc:sqlite:/Users/sangeethasampathkumar/test/";
    Integer rank;
    Connection conn;

    public KeyValueDatabase(Integer rank) {
        this.rank = rank;
        String databaseName = "kvDB" + rank + ".db";
        String connectionURL = URL + databaseName;
        conn = createNewDatabase(connectionURL);
        createKVTable();
        createUpdateNodeTable();
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

    public void createKVTable() {
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
            System.out.println("KV Table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUpdateNodeTable() {
        // SQL statement for creating a new update node table
        String sql = "CREATE TABLE IF NOT EXISTS updateNodeTable (\n"
                + "    operation NVARCHAR(200) PRIMARY KEY  NOT NULL,\n"
                + "    node INTEGER  NOT NULL,\n"
                + "    timestamp INTEGER\n"
                + ");";

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Update Node Table Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlSelect = "SELECT node, timestamp FROM updateNodeTable";
        try {

            ResultSet rs = stmt.executeQuery(sqlSelect);
            int size = 0;
            // loop through the result set
            while (rs.next()) {
                size++;
            }
            if (size == 0)
                insetIntoUpdateNodeTable("update", 0, System.currentTimeMillis());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Pair<Integer, Long> getUpdateNodeInfo() {
        String sqlSelect = "SELECT node, timestamp FROM updateNodeTable";
        int node = -1;
        long timestamp = 0;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlSelect);
            // loop through the result set
            while (rs.next()) {
                node = rs.getInt("node");
                timestamp = rs.getLong("timestamp");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Pair<Integer, Long>(node, timestamp);
    }


    public void insetIntoUpdateNodeTable(String operation, int updateNode, long timestamp) {
        String sql = "INSERT INTO updateNodeTable(operation, node, timestamp) VALUES(?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, operation);
            pstmt.setInt(2, updateNode);
            pstmt.setLong(3, timestamp);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public long getMaxTimestampFromKVTable() {
        String sqlSelect = "SELECT MAX(timestamp) AS maxtime FROM kvtable";
        Statement stmt = null;
        long maxTimestamp = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlSelect);
            // loop through the result set
            while (rs.next()) {
                maxTimestamp = rs.getLong("maxtime");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return maxTimestamp;
    }

    public List<KvStore.Row> getRecordsSince(long timestamp) {
        String sqlSelect = "SELECT key, value, timestamp FROM kvtable WHERE timestamp >= " + timestamp;
        Statement stmt = null;
        List<KvStore.Row> resultList = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlSelect);
            // loop through the result set
            while (rs.next()) {
                KvStore.Row.Builder recordBuilder = KvStore.Row.newBuilder();
                recordBuilder.setKey(rs.getString("key"));
                recordBuilder.setValue(rs.getString("value"));
                recordBuilder.setTimestamp(rs.getLong("timestamp"));
                resultList.add(recordBuilder.build());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
    }

    public void reconciliateDB(List<KvStore.Row> records) {
        System.out.println("Server : " + rank + " inside reconciliateDB");
        String sqlReplace = "REPLACE INTO kvtable (key, value, timestamp)\n" +
                "VALUES(?, ? , ?)";

        PreparedStatement pstmt = null;
        for (KvStore.Row record : records) {
            try {
                pstmt = conn.prepareStatement(sqlReplace);
                pstmt.setString(1, record.getKey());
                pstmt.setString(2, record.getValue());
                pstmt.setLong(3, record.getTimestamp());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void updateUpdateNodeTable(int updateNode, long timestamp) {
        String sql = "UPDATE updateNodeTable SET node = ? ,"
                + "timestamp = ? "
                + "WHERE operation = ? ";

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, updateNode);
            pstmt.setLong(2, timestamp);
            pstmt.setString(3, "update");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void insertIntoTable(String key, String value, long timestamp) {
        String sql = "INSERT INTO kvtable(key, value, timestamp) VALUES(?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, key);
            pstmt.setString(2, value);
            pstmt.setLong(3, timestamp);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateKeyIfExistsInTable(String key, String value, long timestamp) {
        String sql = "UPDATE kvtable SET value = ? ,"
                + "timestamp = ? "
                + "WHERE key = ? ";

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, value);
            pstmt.setLong(2, timestamp);
            pstmt.setString(3, key);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}