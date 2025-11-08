import model.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        casoAlumnos();
        casoProductos();
        casoLibros();
        casoEmpleados();
    }

    public static void casoAlumnos() {
        List<Alumno> alumnos = Arrays.asList(
                new Alumno("Valentina", 8.5, "A"),
                new Alumno("Aigail", 6.0, "B"),
                new Alumno("Natalia", 9.0, "A"),
                new Alumno("Julieta", 7.5, "B"),
                new Alumno("Candela", 5.0, "A")
        );

        System.out.println("=== CASO ALUMNOS ===");

        // 1. Obtener los nombres de los alumnos aprobados en mayúsculas y ordenados
        List<String> aprobados = alumnos.stream()
                .filter(a -> a.getNota() >= 7)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        System.out.println("1. Alumnos aprobados: " + aprobados);

        // 2. Calcular el promedio general de notas
        double promedio = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0.0);
        System.out.println("2. Promedio general: " + promedio);

        // 3. Agrupar alumnos por curso
        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        System.out.println("3. Alumnos por curso: " + alumnosPorCurso);

        // 4. Obtener los 3 mejores promedios
        List<Double> mejoresNotas = alumnos.stream()
                .map(Alumno::getNota)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("4. 3 mejores notas: " + mejoresNotas);
        System.out.println();
    }

    public static void casoProductos() {
        List<Producto> productos = Arrays.asList(
                new Producto("Laptop", "Tecnología", 1500.0, 10),
                new Producto("Mouse", "Tecnología", 50.0, 25),
                new Producto("Silla", "Oficina", 300.0, 15),
                new Producto("Mesa", "Oficina", 500.0, 8),
                new Producto("Tablet", "Tecnología", 800.0, 12)
        );

        System.out.println("=== CASO PRODUCTOS ===");

        // 1. Productos con precio > 100, ordenados descendente
        List<Producto> productosCaros = productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted((p1, p2) -> Double.compare(p2.getPrecio(), p1.getPrecio()))
                .collect(Collectors.toList());
        System.out.println("1. Productos > $100: " + productosCaros);

        // 2. Agrupar por categoría y calcular stock total
        Map<String, Integer> stockPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));
        System.out.println("2. Stock por categoría: " + stockPorCategoria);

        // 3. String con nombre y precio separados por ";"
        String productosString = productos.stream()
                .map(p -> p.getNombre() + " - $" + p.getPrecio())
                .collect(Collectors.joining("; "));
        System.out.println("3. Productos string: " + productosString);

        // 4. Precio promedio general y por categoría
        double promedioGeneral = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
        System.out.println("4a. Precio promedio general: " + promedioGeneral);

        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));
        System.out.println("4b. Precio promedio por categoría: " + promedioPorCategoria);
        System.out.println();
    }

    public static void casoLibros() {
        List<Libro> libros = Arrays.asList(
                new Libro("Cien años de soledad", "Gabriel García Márquez", 450, 25.0),
                new Libro("El principito", "Antoine de Saint-Exupéry", 96, 15.0),
                new Libro("1984", "George Orwell", 328, 20.0),
                new Libro("Don Quijote", "Miguel de Cervantes", 863, 30.0),
                new Libro("Rayuela", "Julio Cortázar", 650, 22.0)
        );

        System.out.println("=== CASO LIBROS ===");

        // 1. Títulos de libros con más de 300 páginas, ordenados alfabéticamente
        List<String> titulosLargos = libros.stream()
                .filter(l -> l.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("1. Libros >300 págs: " + titulosLargos);

        // 2. Promedio de páginas
        double promedioPaginas = libros.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);
        System.out.println("2. Promedio páginas: " + promedioPaginas);

        // 3. Agrupar por autor y contar libros
        Map<String, Long> librosPorAutor = libros.stream()
                .collect(Collectors.groupingBy(
                        Libro::getAutor,
                        Collectors.counting()
                ));
        System.out.println("3. Libros por autor: " + librosPorAutor);

        // 4. Libro más caro
        Optional<Libro> libroMasCaro = libros.stream()
                .max(Comparator.comparingDouble(Libro::getPrecio));
        System.out.println("4. Libro más caro: " + libroMasCaro.orElse(null));
        System.out.println();
    }

    public static void casoEmpleados() {
        List<Empleado> empleados = Arrays.asList(
                new Empleado("Valentina Paz", "Ventas", 2500.0, 21),
                new Empleado("Abigail Muñoz", "TI", 3500.0, 21),
                new Empleado("Natalia Vega", "Ventas", 1800.0, 23),
                new Empleado("Julieta Velasco", "TI", 4000.0, 20),
                new Empleado("Candela Salinas", "RH", 2200.0, 20)
        );

        System.out.println("=== CASO EMPLEADOS ===");

        // 1. Empleados con salario > 2000, ordenados descendente
        List<Empleado> empleadosAltosSalarios = empleados.stream()
                .filter(e -> e.getSalario() > 2000)
                .sorted((e1, e2) -> Double.compare(e2.getSalario(), e1.getSalario()))
                .collect(Collectors.toList());
        System.out.println("1. Empleados > $2000: " + empleadosAltosSalarios);

        // 2. Salario promedio general
        double salarioPromedio = empleados.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);
        System.out.println("2. Salario promedio: " + salarioPromedio);

        // 3. Agrupar por departamento y sumar salarios
        Map<String, Double> salarioPorDepto = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));
        System.out.println("3. Salario total por dto: " + salarioPorDepto);

        // 4. 2 empleados más jóvenes
        List<String> empleadosJovenes = empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .collect(Collectors.toList());
        System.out.println("4. 2 empleados más jóvenes: " + empleadosJovenes);
        System.out.println();
    }
}