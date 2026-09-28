package mau.paginas;

import mau.modelo.DatosEmpleado;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AltaEmpleadoPage extends BasePage {
    private final By firstName = By.name("firstName");
    private final By middleName = By.name("middleName");
    private final By lastName = By.name("lastName");
    private final By employeeId = inputWithLabel("Employee Id");
    private final By createLoginDetails = By.cssSelector(".oxd-switch-wrapper label");
    private final By loginCheckbox = By.cssSelector(".oxd-switch-wrapper input[type='checkbox']");
    private final By username = inputWithLabel("Username");
    private final By password = inputWithLabel("Password");
    private final By confirmPassword = inputWithLabel("Confirm Password");
    private final By saveButton = By.cssSelector("button[type='submit']");
    private final By employeeList = By.linkText("Employee List");

    private static By inputWithLabel(String label) {
        return By.xpath("//label[normalize-space()='" + label + "']/parent::div/following-sibling::div//input");
    }

    private By statusLabel(String status) {
        return By.xpath("//label[normalize-space()='" + status + "' and input[@type='radio']]");
    }

    private By statusRadio(String status) {
        return By.xpath("//label[normalize-space()='" + status + "']/input[@type='radio']");
    }

    public AltaEmpleadoPage(WebDriver driver) {
        super(driver);
        esperarVisible(firstName);
    }

    public void registrar(DatosEmpleado employee) {
        completarIdentidad(employee);
        configurarAcceso(employee);
        click(saveButton);
        // La redireccion confirma el alta; no se editan los datos posteriores.
        wait.until(ExpectedConditions.urlContains("/pim/viewPersonalDetails/empNumber/"));
    }

    private void completarIdentidad(DatosEmpleado employee) {
        write(firstName, employee.getFirstName());
        write(middleName, employee.getMiddleName());
        write(lastName, employee.getLastName());
        write(employeeId, employee.getEmployeeId());
    }

    private void configurarAcceso(DatosEmpleado employee) {
        click(createLoginDetails);
        wait.until(ExpectedConditions.elementToBeSelected(loginCheckbox));
        write(username, employee.getUsername());
        write(password, employee.getPassword());
        write(confirmPassword, employee.getPassword());
        click(statusLabel(employee.getStatus()));
        wait.until(ExpectedConditions.elementToBeSelected(statusRadio(employee.getStatus())));
    }

    public ListadoEmpleadosPage volverAlListado() {
        click(employeeList);
        return new ListadoEmpleadosPage(driver);
    }
}
