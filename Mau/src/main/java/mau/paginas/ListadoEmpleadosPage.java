package mau.paginas;

import mau.modelo.DatosEmpleado;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ListadoEmpleadosPage extends BasePage {
    private final By title = By.xpath("//h5[normalize-space()='Employee Information']");
    private final By addEmployee = By.linkText("Add Employee");
    private final By employeeId = By.xpath("//label[normalize-space()='Employee Id']/parent::div/following-sibling::div//input");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By loader = By.cssSelector(".oxd-loading-spinner");
    private final By rows = By.cssSelector(".oxd-table-body .oxd-table-row");
    private final By cells = By.cssSelector(".oxd-table-cell");

    public ListadoEmpleadosPage(WebDriver driver) {
        super(driver);
        esperarVisible(title);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public AltaEmpleadoPage abrirFormulario() {
        click(addEmployee);
        return new AltaEmpleadoPage(driver);
    }

    public void buscarPorId(DatosEmpleado employee) {
        write(employeeId, employee.getEmployeeId());
        click(searchButton);
    }

    public boolean contieneEmpleado(DatosEmpleado employee) {
        try {
            return wait.until(webDriver -> {
                if (driver.findElements(loader).stream().anyMatch(WebElement::isDisplayed)) return false;
                try {
                    List<WebElement> results = driver.findElements(rows);
                    // El filtro unico debe devolver una sola fila, no la grilla inicial.
                    if (results.size() != 1) return false;
                    List<WebElement> values = results.get(0).findElements(cells);
                    return values.size() >= 4
                            && values.get(1).getText().trim().equals(employee.getEmployeeId())
                            && values.get(2).getText().trim().equals(employee.nombres())
                            && values.get(3).getText().trim().equals(employee.getLastName());
                } catch (StaleElementReferenceException refreshingGrid) {
                    return false;
                }
            });
        } catch (TimeoutException employeeNotFound) {
            return false;
        }
    }
}
