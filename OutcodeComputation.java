package lab5;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class OutcodeComputation implements GLEventListener {

	static GLProfile profile = GLProfile.get(GLProfile.GL2);
	static GLCapabilities capabilities = new GLCapabilities(profile);
	// The canvas
	static GLCanvas glcanvas = new GLCanvas(capabilities);



	public static double x = 0 ;
	public static double y = 0 ;


	public static void main(String[] args) {
		// getting the capabilities object of GL2 profile

		OutcodeComputation l = new OutcodeComputation();
		// creating frame
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(600, 400);

		final JFrame frame = new JFrame("OutcodeComputation");
		// adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);

		x = randNumb();
		y = randNumb();

		String outCode = getOutcode(x, y);
		System.out.println("x is = "+x);
		System.out.println("y is = "+y);
		System.out.println("So, outCode is = "+outCode);
	}

	private static String getOutcode(double x, double y) {

		double xMin = -.5 ;
		double xMax = .5 ;
		double yMin = -.5 ;
		double yMax = .5 ;

		String code = "" ;

		if (x < xMin) {
			code = "1"+code ;
		}else{
			code = "0"+code ;
		}
		if (x > xMax) {
			code = "1"+code ;
		}else{
			code = "0"+code ;
		}
		if (y < yMin) {
			code = "1"+code ;
		}else{
			code = "0"+code ;
		}
		if (y > yMax) {
			code = "1"+code ;
		}else{
			code = "0"+code ;
		}
		return code;

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();

		gl.glBegin(GL2.GL_LINES);

		gl.glVertex2f(-.5f, -.5f);
		gl.glVertex2f(-0.5f, 0.5f);

		gl.glVertex2f(-.5f, -.5f);
		gl.glVertex2f(0.5f, -0.5f);

		gl.glVertex2f(-.5f, 0.5f);
		gl.glVertex2f(0.5f, 0.5f);

		gl.glVertex2f(0.5f, -.5f);
		gl.glVertex2f(0.5f, 0.5f);
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
