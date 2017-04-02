/*GRAPHICS HOMEWORK 3*/

import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class DDA_with_random_value implements GLEventListener {
	static GLProfile profile = GLProfile.get(GLProfile.GL2);
	static GLCapabilities capabilities = new GLCapabilities(profile);
	// The canvas
	static GLCanvas glcanvas = new GLCanvas(capabilities);

	public static void main(String[] args) {
		// getting the capabilities object of GL2 profile

		DDA_with_random_value l = new DDA_with_random_value();
		// creating frame
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(600, 400);

		final JFrame frame = new JFrame("straight Line");
		// adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);

	}

	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();

		double starTx = 0;// x1
		double starTy = 0;// y1
		double enDx = 0;// x2
		double enDy = 0;// y2
		double m = 0;
		double tempx = 0;
		double tempy = 0;
		int max = 1;
		int min = -1;

		for (int i = 0; i < 4; i++) {
			starTx = Math.random() * (max - min) + min;// x1
			starTy = Math.random() * (max - min) + min;// y1
			enDx = Math.random() * (max - min) + min;// x2
			enDy = Math.random() * (max - min) + min;// y2

			if (starTx > enDx) {
				tempx = starTx;
				starTx = enDx;
				enDx = tempx;

				tempy = starTy;
				starTy = enDy;
				enDy = tempy;
			}
			m = (enDy - starTy) / (enDx - starTx);
			System.out.println("after m " + m);
			if (m > -1 && m < 1) {
				while (starTx < enDx) {
					starTx = starTx + 0.001;
					starTy = starTy + (m * 0.001);
					System.err.println("x(if) = " + starTx + " " + "y(if) = "
							+ starTy + " m(if) = " + m);
					gl.glBegin(GL2.GL_POINTS);// static field
					gl.glVertex2d(starTx, starTy);
					gl.glEnd();
				}
			} else {
				while (starTx < enDx) {
					starTx = starTx + (1 / m);
					starTy = starTy + 0.001;
					gl.glBegin(GL2.GL_POINTS);// static field
					gl.glVertex2d(starTx, starTy);
					gl.glEnd();
				}// while (starTx < enDx) {

			}

		}// for ends
	}// Display Method

	public void dispose(GLAutoDrawable arg0) {
		// method body
	}

	public void init(GLAutoDrawable drawable) {
		// method body
		// 4. drive the display() in a loop
	}

	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// method body
	}
	// end of main
}// end of classimport javax.media.opengl.GL2;
