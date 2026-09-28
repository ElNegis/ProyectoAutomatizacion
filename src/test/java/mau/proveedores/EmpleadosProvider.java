package mau.proveedores;

import mau.datos.ArchivoEmpleados;
import mau.datos.IdentidadEmpleado;
import mau.modelo.DatosEmpleado;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class EmpleadosProvider {
    @DataProvider(name = "personalNuevo")
    public Iterator<Object[]> personalNuevo() throws IOException {
        DatosEmpleado[] plantillas = ArchivoEmpleados.leer("datos/employees.json");
        return Arrays.stream(plantillas)
                .map(IdentidadEmpleado::preparar)
                .map(empleado -> new Object[] { empleado })
                .iterator();
    }
}
