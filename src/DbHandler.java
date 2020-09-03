import java.sql.*;
import java.util.Set;

public class DbHandler {
    private Set<Record> records;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;


    private static final String url = "jdbc:mysql://193.169.189.61:3306/corona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "corona_parser";
    private static final String psswd = "Infern00!qwerty";
    private static final String prefix = "Insert into corona_db(countryName, totalCases, newCases, totDeaths, newDeaths, totRecovered, newRecovered, activeCases, serious, totCasesPer1M, totDeathsPer1M, totTest, totTestPer1M, population) VALUES('";

    public DbHandler(Set<Record> records) {
        this.records = records;
    }

    private void init() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(url, user, psswd);
        statement = connection.createStatement();
    }

    public void uploadResults() {
        try {
            init();
            for (Record record: records) {
                statement.executeUpdate(record.getRequest(prefix));
            }
        } catch (SQLException | InstantiationException | ClassNotFoundException | IllegalAccessException throwables) {
            throwables.printStackTrace();

        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
