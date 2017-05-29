package lab3;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class pointPositions implements GLEventListener {

	static GLProfile profile = GLProfile.get(GLProfile.GL2);
	static GLCapabilities capabilities = new GLCapabilities(profile);
	// The canvas
	static GLCanvas glcanvas = new GLCanvas(capabilities);

	public static void main(String[] args) {
		// getting the capabilities object of GL2 profile

		pointPositions l = new pointPositions();
		// creating frame
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(600, 400);

		final JFrame frame = new JFrame("Random dots");
		// adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glBegin(GL2.GL_POINTS);// static field

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

			printXYpoints(x1, y1, x2, y2, 0);
			if (b)
				System.out.println("           Infinity");
			else
				System.out.println("           " + m);

			gl.glVertex2d(x1, y1);// drawing point
			gl.glVertex2d(x2, y2);// drawing point

			if (c == 0)
				break;
		}

		gl.glEnd();

	}

	public static double round(double value) {
		int places = 3;
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public static double randNumb() {

		double d = Math.random();
		double ifMius = Math.random();
		if (((int) (ifMius * 10)) % 2 == 0)
			d = (d) * (-1);

		return d;
	}

	private void printXYpoints(double x1, double y1, double x2, double y2) {
		System.out.print(("double x1=" + x1) + (",y1=" + y1) + (",x2=" + x2) + (",y2=" + y2) + (";\n"));
	}

	private void printXYpoints(double x1, double y1, double x2, double y2, int a) {
		String X1 = x1 + "";
		String Y1 = y1 + "";
		String X2 = x2 + "";
		String Y2 = y2 + "";

		if (x1 > 0) {
			X1 = "+" + x1;
		}
		if (y1 > 0) {
			Y1 = "+" + y1;
		}
		if (x2 > 0) {
			X2 = "+" + x2;
		}
		if (y2 > 0) {
			Y2 = "+" + y2;
		}

		System.out.print((" " + X1) + (" " + Y1) + (" " + X2) + (" " + Y2));
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// method body
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// method body
		// 4. drive the display() in a loop
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// method body
	}
	// end of main
}// end of classimport javax.media.opengl.GL2;
