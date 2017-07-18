
package lab6;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class midpointCircle implements GLEventListener {


	static GLProfile profile = GLProfile.get(GLProfile.GL2);
	static GLCapabilities capabilities = new GLCapabilities(profile);
	// The canvas
	static GLCanvas glcanvas = new GLCanvas(capabilities);

	public static void main(String[] args) {
		// creating frame

		midpointCircle l = new midpointCircle();
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(800, 600);

		final JFrame frame = new JFrame("Lab circle ");
		// adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
	}



	@Override
	public void display(GLAutoDrawable drawable) {



		final GL2 gl = drawable.getGL().getGL2();
		gl.glBegin(GL2.GL_POINTS);// static field

		gl.glVertex2d(.5 , .5 );

		float xc = 0;
		float yc = 0;
		float R = 0;
		while (true) {
			R = (float) Math.random(); // here ...
			if (R != 0)
				break;
		}
		System.out.println("R is "+R);
		float x = 0;
		float y = R;
		float dinit = 1 - R;
		while (x <= y) {
			System.out.println("x is = "+x + "y is = "+y);
			gl.glColor3d(1, 1, 1);
			gl.glVertex2d(.001, .002);
			gl.glVertex2d(x + xc, y + yc); // ocat 1
			//			gl.glVertex2d(y + xc, x + yc); // ocat 1
			//			gl.glVertex2d(-x + xc, y + yc); // ocat 1
			//			gl.glVertex2d(-y + xc, x + yc); // ocat 1
			//			gl.glVertex2d(x + xc, -y + yc); // ocat 1
			//			gl.glVertex2d(y + xc, -x + yc); // ocat 1
			//			gl.glVertex2d(-x + xc, -y + yc); // ocat 1
			//			gl.glVertex2d(-y + xc, -x + yc); // ocat 1
			x = (float) (x + 0.001);
			if (dinit <= 0) {
				dinit = dinit + (2 * x + 1);
			} else {
				y = (float) (y - 0.001);
				dinit = dinit + (2 * (x - y) + 1);
			}
		}
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
		GL baa = arg0.getGL();
		((GL2ES1) baa).glOrtho(-800, arg3, -600, arg4, -1.0, 1.0);
	}

}