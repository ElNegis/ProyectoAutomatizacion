# Mau · Registro de personal en OrangeHRM

**Autor:** Mauricio Gutierrez Pacheco  
**Curso:** Automatizacion  
**Docente:** Lopez Fuentes, Juan David  

---

## Descripción

Este proyecto automatiza un flujo completo de registro de personal en la demo pública de **OrangeHRM** utilizando **Java, Maven, Selenium WebDriver, TestNG y Page Object Model**.

La prueba realiza el siguiente recorrido:

1. Inicia sesión con las credenciales administrativas publicadas en OrangeHRM.
2. Ingresa al módulo **PIM**.
3. Registra un empleado nuevo.
4. Activa **Create Login Details** y crea sus datos de acceso.
5. Guarda el registro.
6. Regresa al listado de empleados.
7. Busca al empleado mediante su identificador.
8. Comprueba que el registro aparece correctamente en la grilla.

La automatización se concentra únicamente en el alta inicial. No modifica información posterior de **Personal Details**, contactos, cargo, salario, dependencias ni fotografía.

---

## Tecnologías utilizadas

- Java 11 o superior
- Maven 3.9
- Selenium WebDriver 4.48.0
- TestNG 7.12.0
- Gson 2.14.0
- Maven Compiler Plugin 3.14.1
- Maven Surefire Plugin 3.5.4
- Google Chrome
- Mozilla Firefox

Selenium Manager administra automáticamente los drivers de Chrome y Firefox, por lo que no es necesario almacenar `chromedriver.exe` o `geckodriver.exe` dentro del proyecto.

---

## Ruta del proyecto

En este equipo el proyecto se encuentra en:

```text
C:\Users\Desktop\Downloads\Mau\Mau
```

Desde PowerShell:

```powershell
cd 'C:\Users\Desktop\Downloads\Mau\Mau'
mvn clean test
```

Si la terminal ya se encuentra dentro de la carpeta del proyecto, se verá aproximadamente así:

```text
C:\Users\Desktop\Downloads\Mau\Mau>
```

y solo será necesario ejecutar:

```powershell
mvn clean test
```

Si se utilizan las herramientas portátiles incluidas en el entorno:

```powershell
cd 'C:\Users\Desktop\Downloads\Mau\Mau'
. .\activar-entorno.ps1
mvn clean test
```

La activación afecta únicamente a la terminal actual.

---

## Ejecución

### Suite completa

```powershell
mvn clean test
```

La suite ejecuta el caso en **Chrome y Firefox**.

Como existen dos empleados de prueba y dos navegadores, el resultado esperado es:

```text
2 empleados × 2 navegadores = 4 ejecuciones
```

### Solo Chrome

```powershell
mvn test -Dtest=mau.pruebas.RegistroPersonalTest -Dbrowser=chrome
```

### Solo Firefox

```powershell
mvn test -Dtest=mau.pruebas.RegistroPersonalTest -Dbrowser=firefox
```

### Modo headless

Para ejecutar sin abrir visualmente el navegador:

```powershell
mvn clean test -Dheadless=true
```

También puede combinarse con un navegador concreto:

```powershell
mvn test -Dtest=mau.pruebas.RegistroPersonalTest -Dbrowser=chrome -Dheadless=true
```

---

## Flujo automatizado

```text
OrangeHRM
   ↓
Login administrativo
   ↓
PIM
   ↓
Add Employee
   ↓
Datos personales básicos
   ↓
Create Login Details
   ↓
Datos de usuario
   ↓
Guardar
   ↓
Employee List
   ↓
Buscar por Employee ID
   ↓
Validar empleado en la grilla
```

Después de guardar, OrangeHRM redirige a **Personal Details**. La automatización únicamente espera esa redirección y vuelve al listado; no completa información adicional.

---

## Organización del proyecto

```text
Mau/
│
├── pom.xml
├── testng.xml
├── VALIDACION.md
├── evidencias/
│
├── src/main/java/mau/
│   ├── paginas/
│   │   ├── BasePage
│   │   ├── AccesoPage
│   │   ├── InicioPage
│   │   ├── AltaEmpleadoPage
│   │   └── ListadoEmpleadosPage
│   │
│   ├── modelo/
│   │   └── DatosEmpleado
│   │
│   └── datos/
│       ├── ArchivoEmpleados
│       └── IdentidadEmpleado
│
├── src/test/java/mau/
│   ├── base/
│   │   └── BaseTest
│   │
│   ├── proveedores/
│   │   └── EmpleadosProvider
│   │
│   └── pruebas/
│       └── RegistroPersonalTest
│
└── src/test/resources/datos/
    └── employees.json
```

---

## Responsabilidad de los componentes

| Componente | Función |
|---|---|
| `BasePage` | Centraliza esperas, escritura, lectura, clics y manejo de capas de carga. |
| `AccesoPage` | Obtiene las credenciales públicas e inicia sesión. |
| `InicioPage` | Permite acceder al módulo PIM. |
| `AltaEmpleadoPage` | Completa la identidad del empleado y sus datos de acceso. |
| `ListadoEmpleadosPage` | Busca por ID y devuelve el resultado de la comprobación. |
| `DatosEmpleado` | Representa los datos utilizados durante el flujo. |
| `ArchivoEmpleados` | Lee `employees.json` mediante Gson. |
| `IdentidadEmpleado` | Genera valores únicos para cada ejecución. |
| `BaseTest` | Prepara el navegador, abre OrangeHRM, toma evidencias y finaliza la sesión. |
| `EmpleadosProvider` | Entrega los datos de empleados mediante `DataProvider`. |
| `RegistroPersonalTest` | Ejecuta el flujo de negocio y contiene la aserción final. |

---

## Datos de prueba

Los datos base se encuentran en:

```text
src/test/resources/datos/employees.json
```

El archivo contiene dos plantillas ficticias:

- **Lucía Isabel Vargas** — estado `Enabled`
- **Mateo Daniel Rojas** — estado `Disabled`

El `DataProvider` lee ambos registros y genera una instancia independiente para cada ejecución.

---

## Datos únicos

OrangeHRM es una demo pública utilizada por muchas personas, por lo que el proyecto evita reutilizar identificadores que podrían existir previamente.

Para cada empleado se genera un sufijo alfanumérico de nueve caracteres mediante `SecureRandom`.

Ese mismo valor se reutiliza en los datos que necesitan ser únicos, principalmente:

- nombre;
- username;
- Employee ID.

Ejemplo conceptual:

```text
Nombre base: Lucia
Sufijo: A7M29Q4XZ
Resultado: Lucia_A7M29Q4XZ
```

La identidad se genera una sola vez y se conserva hasta finalizar la prueba, de modo que el dato registrado sea exactamente el mismo que posteriormente se busca.

---

## Sincronización

La automatización utiliza:

```text
WebDriverWait
ExpectedConditions
```

No se utiliza `Thread.sleep()` para resolver sincronizaciones normales.

También se espera a que desaparezcan las capas de carga de OrangeHRM antes de continuar con operaciones como:

- validación del Employee ID;
- validación del username;
- guardado;
- búsqueda en el listado.

---

## Page Object Model

Los localizadores y el acceso directo al DOM permanecen dentro de las páginas.

La clase de prueba no contiene:

```text
By
WebElement
findElement
XPath
CSS Selector
```

Las páginas tampoco contienen assertions de TestNG.

La comprobación final se realiza exclusivamente desde el test de negocio.

---

## Evidencias y resultados

Los reportes generados por Maven/TestNG quedan en:

```text
target/surefire-reports
```

Las capturas realizadas durante las pruebas se guardan en:

```text
target/capturas
```

Cuando una prueba falla también se conserva el HTML de la página para facilitar el diagnóstico.

Las evidencias seleccionadas para la entrega permanecen en:

```text
evidencias/
```

El resumen de validación se encuentra en:

```text
VALIDACION.md
```

`mvn clean` elimina el contenido generado dentro de `target`.

---

## Consideraciones

OrangeHRM es una demo pública y sus datos pueden cambiar o reiniciarse.

Por esa razón:

- los empleados utilizados son ficticios;
- cada ejecución genera identificadores nuevos;
- la prueba no depende de registros previos;
- no se eliminan empleados existentes;
- un rechazo real del servidor provoca el fallo de la prueba y no se oculta creando otro registro.

---

## Resultado esperado

Una ejecución correcta debe comprobar que:

- el login administrativo funciona;
- PIM puede abrirse;
- el empleado puede registrarse;
- `Create Login Details` puede activarse;
- el usuario y Employee ID son únicos;
- el registro se guarda correctamente;
- el empleado puede buscarse por su ID;
- la fila encontrada contiene los datos esperados;
- la aserción final pasa;
- el mismo flujo funciona en Chrome y Firefox.
