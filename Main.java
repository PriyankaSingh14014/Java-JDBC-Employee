import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();

        while (true) {
            System.out.println("\n1. Add Employee\n2. View Employees\n3. Update Employee\n4. Delete Employee\n5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Email: ");
                    String email = sc.next();
                    System.out.print("Enter Salary: ");
                    while (!sc.hasNextDouble()) {
                        System.out.println("Invalid input. Please enter a numeric salary:");
                        sc.next();
                    }
                    double salary = sc.nextDouble();
                    dao.addEmployee(new Employee(0, name, email, salary));
                    System.out.println("Employee Added.");
                    break;

                case 2:
                    List<Employee> employees = dao.getAllEmployees();
                    for (Employee e : employees) {
                        System.out.println(e);
                    }
                    break;

                case 3:
                    System.out.print("Enter ID to Update: ");
                    int id = sc.nextInt();
                    System.out.print("Enter New Name: ");
                    name = sc.next();
                    System.out.print("Enter New Email: ");
                    email = sc.next();
                    System.out.print("Enter New Salary: ");
                    while (!sc.hasNextDouble()) {
                        System.out.println("Invalid input. Please enter a numeric salary:");
                        sc.next();
                    }
                    salary = sc.nextDouble();
                    dao.updateEmployee(new Employee(id, name, email, salary));
                    System.out.println("Employee Updated.");
                    break;

                case 4:
                    System.out.print("Enter ID to Delete: ");
                    int deleteId = sc.nextInt();
                    dao.deleteEmployee(deleteId);
                    System.out.println("Employee Deleted.");
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
