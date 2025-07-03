import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection conn;

    public EmployeeDAO() {
        conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Failed to connect to database.");
        }
    }

    public void addEmployee(Employee emp) throws SQLException {
        String sql = "INSERT INTO employee (name, email, salary) VALUES (?, ?, ?)";

        if (conn == null) {
            System.out.println("❌ Connection is null in addEmployee(). Check DBConnection.");
            return;
        }

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setDouble(3, emp.getSalary());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee added successfully.");
            } else {
                System.out.println("⚠️ No employee was added.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error in addEmployee(): " + e.getMessage());
            throw e;
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Employee e = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getDouble("salary"));
            list.add(e);
        }
        rs.close();
        st.close();
        return list;
    }

    public void updateEmployee(Employee emp) throws SQLException {
        String sql = "UPDATE employee SET name=?, email=?, salary=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, emp.getName());
        ps.setString(2, emp.getEmail());
        ps.setDouble(3, emp.getSalary());
        ps.setInt(4, emp.getId());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employee WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }
}
