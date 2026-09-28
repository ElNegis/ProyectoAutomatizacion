package mau.datos;

import com.google.gson.Gson;
import mau.modelo.DatosEmpleado;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public final class ArchivoEmpleados {
    private ArchivoEmpleados() { }

    public static DatosEmpleado[] leer(String resource) throws IOException {
        InputStream input = ArchivoEmpleados.class.getClassLoader().getResourceAsStream(resource);
        if (input == null) throw new IOException("No se encontro el archivo de datos: " + resource);
        try (InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
            DatosEmpleado[] employees = new Gson().fromJson(reader, DatosEmpleado[].class);
            if (employees == null || employees.length == 0) {
                throw new IOException("El archivo no contiene empleados: " + resource);
            }
            return employees;
        }
    }
}
