package lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class LineTask02 implements GLEventListener{

	static GLProfile profile = GLProfile.get(GLProfile.GL2);
	static GLCapabilities capabilities = new GLCapabilities(profile);
	// The canvas
	static GLCanvas glcanvas = new GLCanvas(capabilities);

	public static void main(String[] args) throws FileNotFoundException {

		Random20vfileWriter fw = new Random20vfileWriter() ; // creating 20 values
		fw.createFile();

		//getting the capabilities object of GL2 profile

		LineTask02 l = new LineTask02();
		//creating frame
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(600, 400);

		final JFrame frame = new JFrame ("straight Line");
		//adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glBegin (GL2.GL_POINTS);//static field

		Scanner sc = null;
		try {
			sc = new Scanner (new File("coordinates.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(sc.hasNextLine()){
			String[] arr = (sc.nextLine()).split(" ");
			double x = Double.parseDouble(arr[0]) ;
			double y = Double.parseDouble(arr[1]) ;
			System.out.println(x+" "+y);
			gl.glVertex2d(x,y);
		}

		gl.glEnd();

	}

	public static double randNumb(){

		double d = Math.random() ;
		double ifMius = Math.random() ;
		if (((int)(ifMius*10))%2 == 0) d = (d)*(-1) ;

		return d ;
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		//method body
	}


	@Override
	public void init(GLAutoDrawable drawable) {
		// method body
		//4. drive the display() in a loop
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// method body
	}
	//end of main
}//end of classimport javax.media.opengl.GL2;
