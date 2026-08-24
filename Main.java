import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Test database connection
        try {
            Connection con = DBConnection.getConnection();
            System.out.println("Database connected successfully!");
            con.close();

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return;
        }

        while (true) {

            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            // =========================
            // 1. ADD EMPLOYEE
            // =========================
            if (choice == 1) {

                System.out.print("Enter Employee ID: ");
                int id = sc.nextInt();

                System.out.print("Enter Employee Name: ");
                String name = sc.next();

                System.out.print("Enter Department: ");
                String department = sc.next();

                System.out.print("Enter Salary: ");
                double salary = sc.nextDouble();

                try {

                    Connection con = DBConnection.getConnection();

                    String sql =
                            "INSERT INTO employees (id, name, department, salary) VALUES (?, ?, ?, ?)";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setInt(1, id);
                    ps.setString(2, name);
                    ps.setString(3, department);
                    ps.setDouble(4, salary);

                    ps.executeUpdate();

                    System.out.println("Employee added successfully!");

                    ps.close();
                    con.close();

                } catch (Exception e) {

                    System.out.println("Failed to add employee.");
                    e.printStackTrace();
                }
            }

            // =========================
            // 2. VIEW EMPLOYEES
            // =========================
            else if (choice == 2) {

                try {

                    Connection con = DBConnection.getConnection();

                    String sql = "SELECT * FROM employees";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ResultSet rs = ps.executeQuery();

                    boolean found = false;

                    while (rs.next()) {

                        found = true;

                        System.out.println("\n-------------------------");
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Name: " + rs.getString("name"));
                        System.out.println("Department: " + rs.getString("department"));
                        System.out.printf(
                                "Salary: %.2f%n",
                                rs.getDouble("salary")
                        );
                    }

                    if (!found) {
                        System.out.println("No employees found.");
                    }

                    rs.close();
                    ps.close();
                    con.close();

                } catch (Exception e) {

                    System.out.println("Failed to view employees.");
                    e.printStackTrace();
                }
            }

            // =========================
            // 3. SEARCH EMPLOYEE
            // =========================
            else if (choice == 3) {

                System.out.print("Enter Employee ID to search: ");
                int searchId = sc.nextInt();

                try {

                    Connection con = DBConnection.getConnection();

                    String sql =
                            "SELECT * FROM employees WHERE id = ?";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setInt(1, searchId);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {

                        System.out.println("\nEmployee Found!");
                        System.out.println("-------------------------");
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Name: " + rs.getString("name"));
                        System.out.println(
                                "Department: " +
                                rs.getString("department")
                        );
                        System.out.printf(
                                "Salary: %.2f%n",
                                rs.getDouble("salary")
                        );

                    } else {

                        System.out.println("Employee not found.");
                    }

                    rs.close();
                    ps.close();
                    con.close();

                } catch (Exception e) {

                    System.out.println("Failed to search employee.");
                    e.printStackTrace();
                }
            }

            // =========================
            // 4. UPDATE EMPLOYEE
            // =========================
            else if (choice == 4) {

                System.out.print("Enter Employee ID to update: ");
                int updateId = sc.nextInt();

                System.out.print("Enter new name: ");
                String newName = sc.next();

                System.out.print("Enter new department: ");
                String newDepartment = sc.next();

                System.out.print("Enter new salary: ");
                double newSalary = sc.nextDouble();

                try {

                    Connection con = DBConnection.getConnection();

                    String sql =
                            "UPDATE employees " +
                            "SET name = ?, department = ?, salary = ? " +
                            "WHERE id = ?";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setString(1, newName);
                    ps.setString(2, newDepartment);
                    ps.setDouble(3, newSalary);
                    ps.setInt(4, updateId);

                    int rowsUpdated = ps.executeUpdate();

                    if (rowsUpdated > 0) {

                        System.out.println(
                                "Employee updated successfully!"
                        );

                    } else {

                        System.out.println("Employee not found.");
                    }

                    ps.close();
                    con.close();

                } catch (Exception e) {

                    System.out.println("Failed to update employee.");
                    e.printStackTrace();
                }
            }

            // =========================
            // 5. DELETE EMPLOYEE
            // =========================
            else if (choice == 5) {

                System.out.print("Enter Employee ID to delete: ");
                int deleteId = sc.nextInt();

                try {

                    Connection con = DBConnection.getConnection();

                    String sql =
                            "DELETE FROM employees WHERE id = ?";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setInt(1, deleteId);

                    int rowsDeleted = ps.executeUpdate();

                    if (rowsDeleted > 0) {

                        System.out.println(
                                "Employee deleted successfully!"
                        );

                    } else {

                        System.out.println("Employee not found.");
                    }

                    ps.close();
                    con.close();

                } catch (Exception e) {

                    System.out.println("Failed to delete employee.");
                    e.printStackTrace();
                }
            }

            // =========================
            // 6. EXIT
            // =========================
            else if (choice == 6) {

                System.out.println("Exiting...");
                break;
            }

            // =========================
            // INVALID CHOICE
            // =========================
            else {

                System.out.println(
                        "Invalid choice. Please try again."
                );
            }
        }

        sc.close();
    }
}