package mau.modelo;

public class DatosEmpleado {
    private String firstName;
    private String middleName;
    private String lastName;
    private String employeeId;
    private String username;
    private String password;
    private String status;

    public DatosEmpleado(String firstName, String middleName, String lastName,
                        String employeeId, String username, String password, String status) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
    public String getEmployeeId() { return employeeId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getStatus() { return status; }

    public String nombres() {
        return (firstName + " " + middleName).trim();
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s (%s, %s)",
                employeeId, nombres(), lastName, username, status);
    }
}
