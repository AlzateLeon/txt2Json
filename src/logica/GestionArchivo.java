package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class GestionArchivo {

	private static File f;

	/**
	 * Metodo encargado de leer un archivo en formato .txt y generar un Json a
	 * partir de el
	 * 
	 * @author Sebastian Alzate Leon
	 */
	public static void leerArchivo() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(fileChooser);

		FileWriter fw = null;
		try {
			// se captura la ruta del archivo
			String ruta = fileChooser.getSelectedFile().getAbsolutePath();
			StringBuilder data = new StringBuilder();

			// se formatea el nombre del archivo json a generar
			String[] listaRuta = ruta.split("\\\\");
			String nombre = listaRuta[listaRuta.length - 1];
			String[] lista2 = nombre.split("\\.");
			File file = new File(lista2[0] + "Json.txt");

			FileReader archivo = new FileReader(ruta);
			BufferedReader lector = new BufferedReader(archivo);
			fw = new FileWriter(file);

			String linea = lector.readLine();

			data.append("{" + "\"" + lista2[0] + "\"" + ":[" + "\n");
			boolean abrio = false;

			while (linea != null) {

				String[] ingredientePreparacion = null;
				if (linea != null) {
					ingredientePreparacion = linea.split(":");
				}

				if (linea != null && linea.length() == 0) {
					if (abrio) {
						data.append("\"");
					}
					data.append("\n");
				}

				// se valida si es un nuevo campo del JSON
				else if (linea != null && linea.charAt(0) == '-' && linea.charAt(1) == '-') {

					if (abrio) {
						data.append("\n" + "}," + "\n");
					}

					String lineaCabecera = "{" + "\"nombrePostre\" :" + "\"" + linea.substring(2, linea.length()) + "\""
							+ "," + "\n";
					data.append(lineaCabecera);
					abrio = true;

					// se valida si son ingredientes
				} else if (linea != null && ingredientePreparacion[0] != null && ingredientePreparacion.length > 1
						&& ingredientePreparacion[0].equals("Ingredientes")) {
					String lineaIngredientes = "\"ingredientes\" :" + "\"" + ingredientePreparacion[1];
					data.append(lineaIngredientes);

					// se valida si es preparacion
				} else if (linea != null && ingredientePreparacion[0] != null && ingredientePreparacion.length > 1
						&& ingredientePreparacion[0].equals("Preparación")) {

					data.append("\"" + "," + "\n");

					String lineaPreparacion = "\"preparacion\" :" + "\"" + ingredientePreparacion[1];
					data.append(lineaPreparacion);

				} else if (linea != null) {
					// linea de ingrediente o preparacion
					data.append(linea);
				}

				linea = lector.readLine();
			}

			lector.close();
			data.append("}" + "\n" + "]" + "\n" + "}" + "\n");
			fw.write(data.toString());

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("No se ha seleccionado ningún fichero" + e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// close resources
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		leerArchivo();
	}
}
