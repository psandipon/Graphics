package lab4;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class BresenhamLineAlgorithm implements GLEventListener {

	static GLProfile profile = GLProfile.get(GLProfile.GL2);
	static GLCapabilities capabilities = new GLCapabilities(profile);
	// The canvas
	static GLCanvas glcanvas = new GLCanvas(capabilities);

	public static void main(String[] args) {
		// getting the capabilities object of GL2 profile

		BresenhamLineAlgorithm l = new BresenhamLineAlgorithm();
		// creating frame
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(600, 400);

		final JFrame frame = new JFrame("BresenhamLineAlgorithm");
		// adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glBegin(GL2.GL_POINTS);// static field
		int c = 0 ;
		while (c++<11){

			// getting points
			double x0 = randNumb();
			double y0 = randNumb();
			double x1 = randNumb();
			double y1 = randNumb();

			if (x0>x1){
				double temp = x1 ;
				x1 = x0 ;
				x0 = temp ;
			}

			// getting Zone
			double dx = (x1 - x0);
			double dy = (y1 - y0);

			int Zone = -1;

			// -----------------------//
			if (Math.abs(dx) > Math.abs(dy)) {
				if (dx > 0 && dy > 0) {
					Zone = 0;
				} else if (dx > 0 && dy < 0) {
					Zone = 7;
				} else if (dx < 0 && dy > 0) {
					Zone = 3;
				} else {
					Zone = 4;
				}
			} else {
				if (dx > 0 && dy > 0) {
					Zone = 1;
				} else if (dx < 0 && dy > 0) {
					Zone = 2;
				} else if (dx < 0 && dy < 0) {
					Zone = 5;
				} else {
					Zone = 6;
				}
			}
			// -----------------------//

			// -----------------------//
			if (Zone == 0) {

			} else if (Zone == 1) {
				double temp = x0;
				x0 = y0;
				y0 = temp;
				temp = x1;
				x1 = y1;
				y1 = temp;
			} else if (Zone == 2) {
				double temp = x0;
				x0 = y0;
				y0 = -temp;
				temp = x1;
				x1 = y1;
				y1 = -temp;
			} else if (Zone == 3) {
				x0 = -x0;
				y0 = y0;
				x1 = -x1;
				y1 = y1;
			} else if (Zone == 4) {
				x0 = -x0;
				y0 = -y0;
				x1 = -x1;
				y1 = -y1;
			} else if (Zone == 5) {
				double temp = x0;
				x0 = -y0;
				y0 = -temp;
				temp = x1;
				x1 = -y1;
				y1 = -temp;
			} else if (Zone == 6) {
				double temp = x0;
				x0 = -y0;
				y0 = temp;
				temp = x1;
				x1 = -y1;
				y1 = temp;
			} else if (Zone == 7) {
				x0 = x0;
				y0 = y0;
				x1 = x1;
				y1 = -y1;
			}
			// -----------------------//

			dx = (x1 - x0);
			dy = (y1 - y0);

			double Dinit = 2 * dy - dx;
			double dNE = 2 * dy;
			double dE = 2 * dy - 2 * dx;

			while (x0 < x1) {

				if (Dinit < 0) {
					Dinit += dE;
				} else {
					Dinit += dNE;
					y0=y0+.001;
				}
				x0=x0+.001;
				gl.glBegin (GL2.GL_POINTS);//static field
				if (Zone == 0) {
					gl.glVertex2d(x0,y0);
					gl.glColor3f(1f,0f,0f);
				} else if (Zone == 1) {
					gl.glVertex2d(y0,x0);
					gl.glColor3f(0f,1f,0f);
				} else if (Zone == 2) {
					gl.glVertex2d(-y0,x0);
					gl.glColor3f(0f,0f,1f);
				} else if (Zone == 3) {
					gl.glVertex2d(-x0,y0);
					gl.glColor3f(.5f,.5f,1f);
				} else if (Zone == 4) {
					gl.glVertex2d(-x0,-y0);
					gl.glColor3f(.5f,0f,1f);
				} else if (Zone == 5) {
					gl.glVertex2d(-y0,-x0);
					gl.glColor3f(0f,.5f,1f);
				} else if (Zone == 6) {
					gl.glVertex2d(y0,-x0);
					gl.glColor3f(1f,0f,1f);
				} else if (Zone == 7) {
					gl.glVertex2d(x0,-y0);
					gl.glColor3f(1f,1f,0f);
				}
				gl.glEnd();
			}
		}
		gl.glEnd();

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
