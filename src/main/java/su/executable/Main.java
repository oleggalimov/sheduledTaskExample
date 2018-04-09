package su.executable;

import java.io.IOException;
import java.sql.*;
import java.util.concurrent.*;

public class Main {


    static final int minutes_for_reex = 120;

    public static void main(String[] args) throws IOException, InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(new readSubSystems(), 1,minutes_for_reex, TimeUnit.HOURS);
    }
}
class readSubSystems implements Runnable {
    static final String USER = "postgres";
    static final String PASS = "123";
    static final String DB_URL = "jdbc:postgresql://192.168.56.4:5432/postgres";
    static final int delay_for_console = 10000;
    @Override
    public void run() {
        try {
            Connection conn;
            Statement stmt;
            ResultSet resultSet;
            System.out.println("1. Registering jdbc class...");
            Class.forName("org.postgresql.Driver");
            System.out.println("2. Creating connection....");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("3. Selecting data...");
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM subsytems");
            System.out.println("4. Fetching resultset...");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id")
                        + "  |   " + resultSet.getString("name")
                        + "  |   " + resultSet.getString("title"));
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception ex) {
            System.out.println("Error in shedduled task!");
            ex.printStackTrace();
            try {
                Thread.sleep(delay_for_console);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
