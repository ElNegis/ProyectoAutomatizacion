package mau.pruebas;

import mau.base.BaseTest;
import mau.modelo.DatosEmpleado;
import mau.paginas.AccesoPage;
import mau.paginas.AltaEmpleadoPage;
import mau.paginas.InicioPage;
import mau.paginas.ListadoEmpleadosPage;
import mau.proveedores.EmpleadosProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class RegistroPersonalTest extends BaseTest {
    @Test(description = "Registrar personal con acceso y localizarlo por identificador",
            dataProvider = "personalNuevo", dataProviderClass = EmpleadosProvider.class)
    public void empleadoRegistradoApareceEnLaBusqueda(DatosEmpleado empleado) {
        Reporter.log("Registro de personal: " + empleado, true);

        InicioPage inicio = new AccesoPage(driver).ingresarComoAdministrador();
        ListadoEmpleadosPage listado = inicio.abrirPersonal();
        AltaEmpleadoPage formulario = listado.abrirFormulario();
        formulario.registrar(empleado);

        listado = formulario.volverAlListado();
        listado.buscarPorId(empleado);
        Assert.assertTrue(listado.contieneEmpleado(empleado),
                "No se encontro la ficha con el ID y nombre esperados: " + empleado);
    }
}
