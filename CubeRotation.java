import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class CubeRotation implements GLEventListener {

	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();

		double x0 = 0.0;
		double y0 = 0.0;
		double x1 = 0.6;
		double y1 = 0.0;
		double x2 = -0.4;
		double y2 = -0.35;
		double x3 = 0.2;
		double y3 = -0.35;
		double x4 = 0.0;
		double y4 = 0.6;
		double x5 = 0.6;
		double y5 = 0.6;
		double x6 = -0.4;
		double y6 = 0.45;
		double x7 = 0.2;
		double y7 = 0.45;
		double angle = 90;
		double theta = angle*(180/3.1416);
		int time = 1;

		do {
			// x0,y0,x1,y1
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x0, y0);
			gl.glVertex2d(x1, y1);
			//gl.glEnd();

			// x0,y0,x2,y2
			//gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x0, y0);
			gl.glVertex2d(x2, y2);
			//gl.glEnd();

			// x0,y0,x4,y4
			//gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x0, y0);
			gl.glVertex2d(x4, y4);
			//gl.glEnd();

			// x2,y2,x3,y3
			//gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x2, y2);
			gl.glVertex2d(x3, y3);
			//gl.glEnd();

			// x1,y1,x3,y3
			//gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x1, y1);
			gl.glVertex2d(x3, y3);
			//gl.glEnd();

			// x1,y1,x5,y5
			//gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x1, y1);
			gl.glVertex2d(x5, y5);
			//gl.glEnd();

			// x4,y4,x5,y5
			//gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x4, y4);
			gl.glVertex2d(x5, y5);
			//gl.glEnd();

			// x2,y2,x6,y6
			//gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x2, y2);
			gl.glVertex2d(x6, y6);
			//gl.glEnd();

			// x4,y4,x6,y6
			//gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x4, y4);
			gl.glVertex2d(x6, y6);
			//gl.glEnd();

			// x3,y3,x7,y7
			//gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x3, y3);
			gl.glVertex2d(x7, y7);
			//gl.glEnd();

			// x6,y6,x7,y7
			//gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x6, y6);
			gl.glVertex2d(x7, y7);
			//gl.glEnd();

			// x5,y5,x7,y7
			//gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x5, y5);
			gl.glVertex2d(x7, y7);
			gl.glEnd();
			if (time == 2) {
				break;
			} else if (time == 1) {
				// System.out.println("Check");
				x0 = x0 * Math.cos(theta) - y0 * Math.sin(theta);
				y0 = x0 * Math.sin(theta) + y0 * Math.cos(theta);
				x1 = x1 * Math.cos(theta) - y1 * Math.sin(theta);
				y1 = x1 * Math.sin(theta) + y1 * Math.cos(theta);
				x2 = x2 * Math.cos(theta) - y2 * Math.sin(theta);
				y2 = x2 * Math.sin(theta) + y2 * Math.cos(theta);
				x3 = x3 * Math.cos(theta) - y3 * Math.sin(theta);
				y3 = x3 * Math.sin(theta) + y3 * Math.cos(theta);
				x4 = x4 * Math.cos(theta) - y4 * Math.sin(theta);
				y4 = x4 * Math.sin(theta) + y4 * Math.cos(theta);
				x5 = x5 * Math.cos(theta) - y5 * Math.sin(theta);
				y5 = x5 * Math.sin(theta) + y5 * Math.cos(theta);
				x6 = x6 * Math.cos(theta) - y6 * Math.sin(theta);
				y6 = x6 * Math.sin(theta) + y6 * Math.cos(theta);
				x7 = x7 * Math.cos(theta) - y7 * Math.sin(theta);
				y7 = x7 * Math.sin(theta) + y7 * Math.cos(theta);
				time++;
			}
		} while (true);

		/*//Increase & Decrease
		int m = 1;
		while (true) {
			x0 = 0.0;
			y0 = 0.0;
			x1 = 0.6;
			y1 = 0.0;
			x2 = -0.4;
			y2 = -0.35;
			x3 = 0.2;
			y3 = -0.35;
			x4 = 0.0;
			y4 = 0.6;
			x5 = 0.6;
			y5 = 0.6;
			x6 = -0.4;
			y6 = 0.45;
			x7 = 0.2;
			y7 = 0.45;
			if (m == 2) {
				x0 = x0 / 2;
				y0 = y0 / 2;
				x1 = x1 / 2;
				y1 = y1 / 2;
				x2 = x2 / 2;
				y2 = y2 / 2;
				x3 = x3 / 2;
				y3 = y3 / 2;
				x4 = x4 / 2;
				y4 = y4 / 2;
				x5 = x5 / 2;
				y5 = y5 / 2;
				x6 = x6 / 2;
				y6 = y6 / 2;
				x7 = x7 / 2;
				y7 = y7 / 2;
			} else if (m == 3) {
				x0 = x0 * 2;
				y0 = y0 * 2;
				x1 = x1 * 2;
				y1 = y1 * 2;
				x2 = x2 * 2;
				y2 = y2 * 2;
				x3 = x3 * 2;
				y3 = y3 * 2;
				x4 = x4 * 2;
				y4 = y4 * 2;
				x5 = x5 * 2;
				y5 = y5 * 2;
				x6 = x6 * 2;
				y6 = y6 * 2;
				x7 = x7 * 2;
				y7 = y7 * 2;
			}

			// x0,y0,x1,y1
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x0, y0);
			gl.glVertex2d(x1, y1);
			gl.glEnd();

			// x0,y0,x2,y2
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x0, y0);
			gl.glVertex2d(x2, y2);
			gl.glEnd();

			// x0,y0,x4,y4
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x0, y0);
			gl.glVertex2d(x4, y4);
			gl.glEnd();

			// x2,y2,x3,y3
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x2, y2);
			gl.glVertex2d(x3, y3);
			gl.glEnd();

			// x1,y1,x3,y3
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x1, y1);
			gl.glVertex2d(x3, y3);
			gl.glEnd();

			// x1,y1,x5,y5
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x1, y1);
			gl.glVertex2d(x5, y5);
			gl.glEnd();

			// x4,y4,x5,y5
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x4, y4);
			gl.glVertex2d(x5, y5);
			gl.glEnd();

			// x2,y2,x6,y6
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x2, y2);
			gl.glVertex2d(x6, y6);
			gl.glEnd();

			// x4,y4,x6,y6
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x4, y4);
			gl.glVertex2d(x6, y6);
			gl.glEnd();

			// x3,y3,x7,y7
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x3, y3);
			gl.glVertex2d(x7, y7);
			gl.glEnd();

			// x6,y6,x7,y7
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x6, y6);
			gl.glVertex2d(x7, y7);
			gl.glEnd();

			// x5,y5,x7,y7
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x5, y5);
			gl.glVertex2d(x7, y7);
			gl.glEnd();
			m++;
			if (m > 3) {
				break;
			}
		}*/
	}

	public void dispose(GLAutoDrawable arg0) {
		// method body
	}

	public void init(GLAutoDrawable arg0) {
		// method body
	}

	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// method body
	}

	public static void main(String[] args) {

		// getting the capabilities object of GL2 profile
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);

		// The canvas
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		CubeRotation cube = new CubeRotation();
		glcanvas.addGLEventListener(cube);
		glcanvas.setSize(700, 700);

		// creating frame
		final JFrame frame = new JFrame("CubeRotation");

		// adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
	}

}