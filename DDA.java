package lab2;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class DDA implements GLEventListener {

	double scalaingValue = .01 ;

	static GLProfile profile = GLProfile.get(GLProfile.GL2);
	static GLCapabilities capabilities = new GLCapabilities(profile);
	// The canvas
	static GLCanvas glcanvas = new GLCanvas(capabilities);

	public static void main(String[] args) {
		// getting the capabilities object of GL2 profile

		DDA l = new DDA();
		// creating frame
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(600, 400);

		final JFrame frame = new JFrame("DDA");
		// adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);

	}

	@Override
	public void display(GLAutoDrawable drawable) {

		final GL2 gl = drawable.getGL().getGL2();
		gl.glBegin(GL2.GL_POINTS);// static field

		// Creating initial values x1,y1 x2,y2

		double x1 = round(randNumb());
		double y1 = round(randNumb());
		double x2 = round(randNumb());
		double y2 = round(randNumb());

		if (x1 > x2) {
			// Swapping x
			double temp = x1;
			x1 = x2;
			x2 = temp;
		}

		//   1.089  /0.021

		// Calculating m

		double m = round((y2 - y1) / (x2 - x1));

		// going in main if condition

		if (m > -1 && m < 1) {
			if (x1 > x2) {
				// Swapping x
				double temp = x1;
				x1 = x2;
				x2 = temp;
			}

			// Delete this
			double xs = x1;
			double ys = y1;
			double xe = x2;
			double ye = y2;
			// Up to this ...

			System.out.println("1st??");
			printXYpoints(x1, y1, x2, y2);
			while (x1 <= x2) {
				gl.glBegin(GL2.GL_POINTS);// static field
				gl.glVertex2d(x1, y1); // drawing initial value + others
				gl.glEnd();
				x1 = round(x1 + scalaingValue); // x increasing
				y1 = round(y1 + (m * scalaingValue));
				printXYpoints(x1, y1, x2, y2);
				printXYpoints(xs, ys, xe, ye);
				System.out.println("1st "+"m= "+m);
			}

		} else {
			if (y1 > y2) {
				// Swapping x
				double temp = y1;
				y1 = y2;
				y2 = temp;
			}

			// Delete this
			double xs = x1;
			double ys = y1;
			double xe = x2;
			double ye = y2;
			// Up to this ...

			System.out.println("2nd??");
			//	int c = 0; // not important
			printXYpoints(x1, y1, x2, y2);
			while (y1 <= y2) {

				// drawing initial value + others
				gl.glBegin(GL2.GL_POINTS);// static field
				gl.glVertex2d(x1, y1); // drawing initial value + others
				gl.glEnd();

				// calculating next x values
				x1 = round(x1 + ((1 / m) * scalaingValue)); // x increasing
				y1 = round(y1 + scalaingValue);
				printXYpoints(x1, y1, x2, y2);
				printXYpoints(xs, ys, xe, ye);
				System.out.println("2nd"+" m= "+m);

			}

		}

		// gl.glVertex2d(x,y);

		gl.glEnd();

	}

	private void printXYpoints(double x1, double y1, double x2, double y2) {
		System.out.println(("x1=" + x1) + (" y1=" + y1) + (" x2=" + x2) + (" y2=" + y2) + ("\n"));
	}

	public static double randNumb() {

		double d = Math.random();
		double ifMius = Math.random();
		if (((int) (ifMius * 10)) % 2 == 0)
			d = (d) * (-1);

		return d;
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub

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
