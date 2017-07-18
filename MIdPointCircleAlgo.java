package lab6;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class MIdPointCircleAlgo implements GLEventListener {

	static GLProfile profile = GLProfile.get(GLProfile.GL2);
	static GLCapabilities capabilities = new GLCapabilities(profile);
	// The canvas
	static GLCanvas glcanvas = new GLCanvas(capabilities);

	public static void main(String[] args) {
		// getting the capabilities object of GL2 profile

		MIdPointCircleAlgo l = new MIdPointCircleAlgo();
		// creating frame
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(600, 600);

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

		float r = (float) Math.random();
		float x = 0;
		float y = r;

		gl.glVertex2d(0, 0);
		gl.glVertex2d(x, y);

		float dinit = (5 / 4) - r;
		System.out.println(dinit);

		while (x < y) {

			gl.glColor3f((float) (Math.random()), (float) Math.random(),
					(float) Math.random());

			gl.glColor3d(1, 1, 0);
			gl.glVertex2d(x, y);
			gl.glVertex2d(y, x);

			gl.glColor3f((float) (Math.random()), (float) Math.random(),
					(float) Math.random());
			gl.glVertex2d(-x, y);
			gl.glVertex2d(-y, x);

			gl.glColor3f((float) (Math.random()), (float) Math.random(),
					(float) Math.random());
			gl.glVertex2d(x, -y);
			gl.glVertex2d(y, -x);

			gl.glColor3d(0, .9, 0);
			gl.glVertex2d(-x, -y);
			gl.glVertex2d(-y, -x);

			if (dinit < 0) {
				dinit = (float) (dinit + 2 * x + .001);
			} else {
				dinit = (float) (dinit + 2 * (x - y) + .001);
				y = (float) (y - .001);
			}
			x = (float) (x + .001);

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

}
