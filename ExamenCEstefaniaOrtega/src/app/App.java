package app;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import modelo.Trastero;
import servicios.TrasteroEstadoException;
import servicios.TrasterosService;
import servicios.TrasteroserviceException;

public class App {

	private static Scanner sc;
	private static TrasterosService ts;

	public static void main(String[] args) throws TrasteroEstadoException {

		sc = new Scanner(System.in);
		ts = new TrasterosService();
		Boolean correcto = false;

		try {

			do {
				String codigo = "";
				BigDecimal precio = BigDecimal.ZERO;
				do {
					System.out.println("Indica código del trastero que quieres crear");
					codigo = sc.nextLine();

					if (!codigo.startsWith("A")) {
						System.out.println("El código debe empezar por A");
						correcto = false;

					} else if (codigo.length() < 5 || codigo.length() > 10) {
						System.out.println("El código tiene que tener una longitud entre 5 y 10 carácteres");
						correcto = false;
					} else {
						try {
							Trastero t = new Trastero(codigo, 10);
							ts.crearTrastero(t);
							System.out.println("Trastero creado correctamente!!");

						} catch (TrasteroserviceException e) {
							e.printStackTrace();
							break;
						}
					}
				} while (!correcto);

				do {
					System.out.println("Indica el precio por metro y dia que quieres alquilar");
					precio = sc.nextBigDecimal();
					DecimalFormat formato = new DecimalFormat("#,###.00");

					try {
						// precio = formato.format(precio);
					} catch (InputMismatchException e) {
						System.out.println("El valor indicado no es un número decimal");
						correcto = false;
					}

					if (precio.compareTo(BigDecimal.ZERO) <= 0 && precio.compareTo(new BigDecimal(100)) >= 0) {
						System.out.println("El valor indicado no es correcto");
						correcto = false;
					}

				} while (!correcto);
				try {
					Trastero t = new Trastero(codigo, 10);
					t.alquilar("12345678X", 30, precio);
					ts.actualizarPrecioLibroNuevo(t);
					System.out.println("Trastero alquilado!!");
				} catch (TrasteroserviceException e) {
					e.printStackTrace();
				} catch (TrasteroEstadoException e) {
					e.printStackTrace();
				}

			} while (!correcto);

		} finally {
			System.out.println("Gracias!! Que tengas un buen día");
			sc.close();
		}

	}

}
