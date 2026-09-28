package mau.paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccesoPage extends BasePage {
    // Localizadores observados en el DOM publico.
    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By demoUsername = By.xpath("//p[starts-with(normalize-space(), 'Username :')]");
    private final By demoPassword = By.xpath("//p[starts-with(normalize-space(), 'Password :')]");

    public AccesoPage(WebDriver driver) {
        super(driver);
        esperarVisible(username);
    }

    // Acciones: las credenciales se leen del aviso publico de la demo.
    public InicioPage ingresarComoAdministrador() {
        String user = getText(demoUsername).split(":", 2)[1].trim();
        String pass = getText(demoPassword).split(":", 2)[1].trim();
        return login(user, pass);
    }

    public InicioPage login(String user, String pass) {
        write(username, user);
        write(password, pass);
        click(loginButton);
        return new InicioPage(driver);
    }
}
