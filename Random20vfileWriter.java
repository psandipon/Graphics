package lab1;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Random20vfileWriter {

	private static final String FILENAME = "coordinates.txt";

	public  void createFile() {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {


			String content = "";

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			for (int i = 0; i < 20; i++) {
				content = randNumb()+" "+randNumb() ;
				if (i!=19) content = content+"\n";
				bw.write(content);
			}




		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
	public static double randNumb(){

		double d = Math.random() ;
		double ifMius = Math.random() ;
		if (((int)(ifMius*10))%2 == 0) d = (d)*(-1) ;

		return d ;
	}

}