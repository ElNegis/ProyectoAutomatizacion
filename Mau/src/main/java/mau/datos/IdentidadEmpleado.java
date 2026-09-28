package mau.datos;

import mau.modelo.DatosEmpleado;
import java.security.SecureRandom;

public final class IdentidadEmpleado {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";

    private IdentidadEmpleado() { }

    public static DatosEmpleado preparar(DatosEmpleado base) {
        // Un solo sufijo por fila del DataProvider, compartido por los tres campos.
        StringBuilder suffix = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            suffix.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        if (base.getEmployeeId() == null || base.getEmployeeId().length() != 1) {
            throw new IllegalArgumentException("employeeId en JSON debe ser un prefijo de un caracter");
        }
        if (!"Enabled".equals(base.getStatus()) && !"Disabled".equals(base.getStatus())) {
            throw new IllegalArgumentException("status debe ser Enabled o Disabled");
        }
        return new DatosEmpleado(base.getFirstName() + "_" + suffix, base.getMiddleName(),
                base.getLastName(), base.getEmployeeId() + suffix,
                base.getUsername() + "_" + suffix, base.getPassword(), base.getStatus());
    }
}
