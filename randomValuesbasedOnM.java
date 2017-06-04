package lab3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class randomValuesbasedOnM {

	public static void main (String [] args) throws IOException{
		createFile() ;
	}

	private static final String FILENAME = "coordinatesBasedOn_M.txt";

	public static void createFile() throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));


		String content = "1";


		double m = -100000;
		boolean b = false;
		double x1;
		double y1;
		double x2;
		double y2;

		int c = 20;

		while (true) {

			b = false;

			while (true) {
				x1 = round(randNumb());
				y1 = round(randNumb());
				x2 = round(randNumb());
				y2 = round(randNumb());

				if ((x2 - x1) != 0) {
					m = round((y2 - y1) / (x2 - x1));
					if (m == 0) {
						c--;
						break;
					}

					if (m == -1) {
						c--;
						break;
					}

					if (m == 1) {
						c--;
						break;
					}

				} else {
					c--;
					b = true;
					break;
				}

			}

			content = (("" + x1) + (" " + y1) + (" " + x2) + (" " + y2) );
			System.out.println(content);

			writer.write(content);
			//
			//			if (b)
			//				System.out.println("           Infinity");
			//			else
			//				System.out.println("           " + m);

			if (c == 0)
				break;
		}



	}

	private static String printXYpoints(double x1, double y1, double x2, double y2) {
		return (("" + x1) + (" " + y1) + (" " + x2) + (" " + y2) + ("\n"));

	}

	public static double randNumb() {

		double d = Math.random();
		double ifMius = Math.random();
		if (((int) (ifMius * 10)) % 2 == 0)
			d = (d) * (-1);

		return d;
	}

	public static double round(double value) {
		int places = 3;
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}