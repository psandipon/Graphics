package lab5;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class CohenSutherlandExtra implements GLEventListener   {

	public static final int INSIDE = 0;
	public static final int LEFT   = 1;
	public static final int RIGHT  = 2;
	public static final int BOTTOM = 4;
	public static final int TOP    = 8;

	private double xMin = -.5 ;
	private double xMax = .5 ;
	private double yMin = -.5 ;
	private double yMax = .5 ;

	static GLProfile profile = GLProfile.get(GLProfile.GL2);
	static GLCapabilities capabilities = new GLCapabilities(profile);
	// The canvas
	static GLCanvas glcanvas = new GLCanvas(capabilities);

	public static double x = 0;
	public static double y = 0;

	public static void main(String[] args) {

		// getting the capabilities object of GL2 profile

		CohenSutherlandExtra l = new CohenSutherlandExtra();
		// creating frame
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(600, 400);

		final JFrame frame = new JFrame("OutcodeComputation");
		// adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);


	}

	@Override
	public void display(GLAutoDrawable drawable) {
		CohenSutherland co = new CohenSutherland() ;
		final GL2 gl = drawable.getGL().getGL2();
		gl.glBegin(GL2.GL_LINES);

		for (int i = 0; i < 10; i++) {
			gl.glColor3f( 0.0f, 1.0f, 0.0f);
			double x0 = randNumb();
			double y0 = randNumb();
			double x1 = randNumb();
			double y1 = randNumb();
			LineSegment ls = new LineSegment(x0, y0, x1, y1) ;
			ls=co.clip(ls) ;
			if (ls!=null){
				gl.glColor3f( 0.0f, 0.0f, 0.9f);
				gl.glVertex2d(x0, y0);
				gl.glVertex2d(x1, y1);
				gl.glColor3f( 0.0f, 1.0f, 0.0f);
				gl.glVertex2d(ls.x0, ls.y0);
				gl.glVertex2d(ls.x1, ls.y1);
			}else{
				gl.glColor3f( 0.9f, 0.0f, 0.0f);
				gl.glVertex2d(x0, y0);
				gl.glVertex2d(x1, y1);
			}
		}

		gl.glColor3f( 1.0f, 1.0f, 1.0f);
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

	private static double randNumb() {
		double d = Math.random();
		double ifMius = Math.random();
		if (((int) (ifMius * 10)) % 2 == 0)
			d = (d) * (-1);

		return d;
	}

	private class LineSegment {
		public double x0;
		public double y0;
		public double x1;
		public double y1;

		public LineSegment(double x0, double y0, double x1, double y1) {
			this.x0 = x0;
			this.y0 = y0;
			this.x1 = x1;
			this.y1 = y1;
		}

		@Override
		public String toString() {
			return "LineSegment(x0: " + x0 + ", y0: " + y0 + "; x1: " + x1 + ", y1: " + y1 + ")";
		}
	}

	public class CohenSutherland  {

		private int computeOutCode(double x, double y) {
			int code = INSIDE;

			if (x < xMin) {
				code |= LEFT;
			} else if (x > xMax) {
				code |= RIGHT;
			}
			if (y < yMin) {
				code |= BOTTOM;
			} else if (y > yMax) {
				code |= TOP;
			}

			return code;
		}

		/**
		 * Execute line clipping using Cohen-Sutherland
		 * Taken from: http://en.wikipedia.org/wiki/Cohen-Sutherland
		 * @param line LineSegment to work with
		 * @return Clipped line
		 */
		public LineSegment clip(LineSegment line) {

			double x0 = line.x0, x1 = line.x1, y0 = line.y0, y1 = line.y1;
			int outCode0 = computeOutCode(x0, y0);
			int outCode1 = computeOutCode(x1, y1);
			System.out.println("OutCode0: " + outCode0 + ", OutCode1: " + outCode1);
			boolean accept = false;

			while (true) {
				if ((outCode0 | outCode1) == 0) { // Bitwise OR is 0. Trivially accept
					accept = true;
					break;
				} else if ((outCode0 & outCode1) != 0) { // Bitwise AND is not 0. Trivially reject
					break;
				} else {
					double x;
					double y;

					// Pick at least one point outside rectangle
					int outCodeOut = (outCode0 != 0) ? outCode0 : outCode1;

					// Now find the intersection point;
					// use formulas y = y0 + slope * (x - x0), x = x0 + (1 / slope) * (y - y0)
					if ((outCodeOut & TOP) != 0) {
						x = x0 + (x1 - x0) * (yMax - y0) / (y1 - y0);
						y = yMax;
					} else if ((outCodeOut & BOTTOM) != 0) {
						x = x0 + (x1 - x0) * (yMin - y0) / (y1 - y0);
						y = yMin;
					} else if ((outCodeOut & RIGHT) != 0) {
						y = y0 + (y1 - y0) * (xMax - x0) / (x1 - x0);
						x = xMax;
					} else {
						y = y0 + (y1 - y0) * (xMin - x0) / (x1 - x0);
						x = xMin;
					}

					// Now we move outside point to intersection point to clip
					if (outCodeOut == outCode0) {
						x0 = x;
						y0 = y;
						outCode0 = computeOutCode(x0, y0);
					} else {
						x1 = x;
						y1 = y;
						outCode1 = computeOutCode(x1, y1);
					}
				}
			}
			if (accept) {
				return new LineSegment(x0, y0, x1, y1);
			}
			return null;
		}

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





}
