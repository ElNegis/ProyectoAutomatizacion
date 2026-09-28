package mau.paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InicioPage extends BasePage {
    private final By title = By.xpath("//h6[normalize-space()='Dashboard']");
    private final By pim = By.cssSelector("a[href$='/pim/viewPimModule']");

    public InicioPage(WebDriver driver) {
        super(driver);
        esperarVisible(title);
    }

    public ListadoEmpleadosPage abrirPersonal() {
        click(pim);
        return new ListadoEmpleadosPage(driver);
    }
}
