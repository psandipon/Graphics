import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import java.lang.*;

import com.jogamp.opengl.util.Animator;

import java.awt.*;

public class Clock implements GLEventListener {
	double angle = 0;
	double angle2 = 0;
	double theta = 0;
	double theta2 = 0;
	int count = 1;
	double x3 = 0.0;
	double y3 = 0.0;
	double x4 = 0.35;
	double y4 = -0.23;
	public static Animator animator;

	public static void main(String[] args) {
		Clock s = new Clock();
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		caps.setDoubleBuffered(true);
		GLCanvas canvas = new GLCanvas(caps);
		canvas.addGLEventListener(s);
		JFrame frame = new JFrame("Animating triangle");
		frame.setSize(600, 600);
		frame.add(canvas);
		frame.setVisible(true);

		canvas.addGLEventListener(new Clock());
		animator = new Animator(canvas);
		animator.start();
	}

	public void display(GLAutoDrawable drawable) {
		angle -= 6;
		theta = Math.toRadians(angle);
		angle2 += 6;
		theta2 = Math.toRadians(angle2);

		System.out.println("BEFORE BEFORE ANGLE " + angle);
		GL2 gl = drawable.getGL().getGL2();

		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

		// Circle Algorithm
		gl.glBegin(GL2.GL_POINTS);
		double radius = 0.6;
		double x = 0.0;
		double y = radius;
		gl.glColor3f(1f, 0f, 0f);
		gl.glVertex2d(0.0, 0.0);
		gl.glVertex2d(x, y);
		gl.glVertex2d(-x, y);
		gl.glVertex2d(x, -y);
		gl.glVertex2d(-x, -y);
		gl.glVertex2d(y, x);
		gl.glVertex2d(-y, x);
		gl.glVertex2d(y, -x);
		gl.glVertex2d(-y, -x);

		double eps = .001;
		double eps2 = eps * eps;
		while (x < y) {
			y = Math.sqrt(y * y - 2 * eps * x - eps2);
			x += eps;
			gl.glVertex2d(x, y);
			gl.glVertex2d(-x, y);
			gl.glVertex2d(x, -y);
			gl.glVertex2d(-x, -y);
			gl.glVertex2d(y, x);
			gl.glVertex2d(-y, x);
			gl.glVertex2d(y, -x);
			gl.glVertex2d(-y, -x);
		}
		gl.glEnd();
		// Circle Algorithm

		// Second's Hand
		double x0 = 0.0;
		double y0 = 0.0;
		double x1 = 0.25;
		double y1 = 0.2;

		

		x0 = (0.0 * Math.cos(theta)) - (0.0 * Math.sin(theta));
		y0 = (0.0 * Math.sin(theta)) + (0.0 * Math.cos(theta));
		x1 = (.25 * Math.cos(theta)) - (0.2 * Math.sin(theta));
		y1 = (.25 * Math.sin(theta)) + (0.2 * Math.cos(theta));

		gl.glColor3f(0f, 0f, 1f);
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2d(x0, y0);
		gl.glVertex2d(x1, y1);
		gl.glEnd();
		count++;
		// Second's Hand

		// Minute's Hand
		//angle2 -= 6;
		//theta2 = Math.toRadians(angle2);
		
		if(count < 60){
		gl.glColor3f(0f, 1f, 1f);
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2d(x3, y3);
		gl.glVertex2d(x4, y4);
		gl.glEnd();
		}
		//System.out.println("BEFORE ANGLE " + angle2);
		System.out.println("COUNT: "+count);	
		if(count == 60) {
			System.out.println("COUNT: "+count);
			x3 = (0.0 * Math.cos(theta2)) - (0.0 * Math.sin(theta2));
			y3 = (0.0 * Math.sin(theta2)) + (0.0 * Math.cos(theta2));
			x4 = (0.35 * Math.cos(theta2)) - (-0.23 * Math.sin(theta2));
			y4 = (0.35 * Math.sin(theta2)) + (-0.23 * Math.cos(theta2));
			System.out.println("ANGLE " + angle2);
			
			//gl.glColor3f(0f, 1f, 1f);
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex2d(x3, y3);
			gl.glVertex2d(x4, y4);
			gl.glEnd();
			count = 0;
			// Minute's Hand
		}
		
	}

	public void dispose(GLAutoDrawable drawable) {

	}

	public void init(GLAutoDrawable drawable) {
		drawable.getGL().setSwapInterval(1);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
	}

}
