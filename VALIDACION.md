# Resultado de la variante Mau

Se ejecutó `mvn clean test` el 27/09/2026. Finalizó a las 18:16:17,
hora de Bolivia, con **BUILD SUCCESS** en 1 minuto y 37 segundos.

```text
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
```

| Navegador | ID generado | Empleado | Estado | Resultado |
|---|---|---|---|---|
| Chrome | L27mh9i7z0 | Lucia_27mh9i7z0 Isabel Vargas | Enabled | PASS |
| Chrome | Tru6mdeqtg | Mateo_ru6mdeqtg Daniel Rojas | Disabled | PASS |
| Firefox | L2ii3balvr | Lucia_2ii3balvr Isabel Vargas | Enabled | PASS |
| Firefox | T6h4grmtjk | Mateo_6h4grmtjk Daniel Rojas | Disabled | PASS |

Cada caso completó el login, PIM, alta con datos de acceso, regreso al listado,
búsqueda por ID y aserción del ID, nombres y apellido en la grilla.

Entorno: Java 11.0.32.1, Maven 3.9.11, Chrome 154.0.8037.57 y Firefox 156.0.1.
La ejecución utilizó navegadores visibles, sin headless. Las advertencias de CDP
y SLF4J no impidieron la ejecución con WebDriver estándar.

## Comprobaciones adicionales

- El test de negocio no contiene By, selectores, WebElement ni búsquedas del DOM.
- Las páginas no importan TestNG ni contienen assertions.
- No se utiliza Thread.sleep.
- El JSON contiene dos empleados distintos y está fuera del test.
- El proveedor independiente genera la identidad una sola vez por fila.
- La suite ejecuta el mismo caso dos veces por navegador.
- Los 34 archivos fuente, documentos y evidencias del proyecto anterior conservan
  sus hashes respecto del comienzo de este trabajo.
- Mau no contiene una carpeta .git ni se publicó en GitHub.

## Archivos de evidencia

La carpeta `evidencias/` contiene el log de Maven, el XML de TestNG, su reporte HTML
y cuatro capturas finales. Son resultados obtenidos con Mau, no reportes copiados
de ejecuciones del proyecto anterior. Permanecen aunque se ejecute nuevamente clean.

La demo compartida puede resetear los registros después de esta validación.
