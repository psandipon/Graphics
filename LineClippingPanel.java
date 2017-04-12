import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import javax.swing.JPanel;
import java.applet.*;
import java.util.*;
import java.math.*;

public class LineClippingPanel extends JPanel {

	public static final int INSIDE = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int BOTTOM = 4;
	public static final int TOP = 8;

	private int xMin;
	private int xMax;
	private int yMin;
	private int yMax;

	private LineClipper clipper;
	
	
	public static void main(String[] args) {

		JFrame mainFrame = new JFrame("Line Clipping");
		mainFrame.setSize(800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int x0, y0, x1, y1;
		

		x0 = 100;
		y0 = 100;
		x1 = 550;
		y1 = 450;

		mainFrame.add(new LineClippingPanel(x0, y0, x1, y1));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	public LineClippingPanel(int xMin, int yMin, int xMax, int yMax) {
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;

		clipper = new CohenSutherland();

	}

	
	// main algorithm .... here
	//CohenSutherLand class
	public class CohenSutherland implements LineClipper {

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

		public LineSegment clip(LineSegment line) {
			
			int x0 = line.x0, x1 = line.x1, y0 = line.y0, y1 = line.y1;
			int outCode0 = computeOutCode(x0, y0);
			int outCode1 = computeOutCode(x1, y1);
		
			boolean accept = false;

			while (true) {
				if ((outCode0 | outCode1) == 0) {
					accept = true;
					break;
				} else if ((outCode0 & outCode1) != 0) {
					break;
				} else {

					int x, y;

					// Pick at least one point outside rectangle
					int outCodeOut = (outCode0 != 0) ? outCode0 : outCode1;

					// Calculating the intersection point;
					// use formulas y = y0 + slope * (x - x0), x = x0 + (1 /
					// slope) * (y - y0)
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

					// intersection point where to clip
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
	
	//LineSegment Class
	private class LineSegment {
		public int x0;
		public int y0;
		public int x1;
		public int y1;

		public LineSegment(int x0, int y0, int x1, int y1) {
			this.x0 = x0;
			this.y0 = y0;
			this.x1 = x1;
			this.y1 = y1;
		}

		/*public String toString() {
			return "LineSegment(x0: " + x0 + ", y0: " + y0 + "; x1: " + x1 + ", y1: " + y1 + ")";
		}*/
	}
	//Line Clipper Class
	public interface LineClipper {
		public LineSegment clip(LineSegment clip);
	}


	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.black);//Changed Here
		g2d.fillRect(0, 0, getWidth(), getHeight());

		g2d.setColor(Color.white);
		drawLine(g2d, xMin, 0, xMin, getHeight());
		drawLine(g2d, xMax, 0, xMax, getHeight());
		drawLine(g2d, 0, yMin, getWidth(), yMin);
		drawLine(g2d, 0, yMax, getWidth(), yMax);

		int x0, y0, x1, y1;
		LineSegment line, clippedLine;
		for (int i = 0; i < 10000; i++) {
			x0 = (int) (Math.random() * getWidth());
			x1 = (int) (Math.random() * getWidth());
			y0 = (int) (Math.random() * getHeight());
			y1 = (int) (Math.random() * getHeight());
			line = new LineSegment(x0, y0, x1, y1);
			clippedLine = clipper.clip(line);

			if (clippedLine == null) {

			} else {
				g2d.setColor(Color.black);
				drawLine(g2d, line.x0, line.y0, clippedLine.x0, clippedLine.y0);
				drawLine(g2d, clippedLine.x1, clippedLine.y1, line.x1, line.y1);
				g2d.setColor(Color.green);
				drawLine(g2d, clippedLine.x0, clippedLine.y0, clippedLine.x1, clippedLine.y1);
			}
		}
	for(int i = 100; i>0; i-=0.00001){	
		int r = i;
		int d = 5-(4*r);
		int cx = 0;
		int cy = r;
	do
	{
		g2d.setColor(Color.red);
		g2d.drawLine(cy+300,cx+300,cy+300,cx+300);
		g2d.drawLine(cx+300,cy+300,cx+300,cy+300);
		g2d.drawLine(cx+300,-cy+300,cx+300,-cy+300);
		g2d.drawLine(cy+300,-cx+300,cy+300,-cx+300);
		g2d.drawLine(-cy+300,-cx+300,-cy+300,-cx+300);
		g2d.drawLine(-cx+300,-cy+300,-cx+300,-cy+300);
		g2d.drawLine(-cx+300,cy+300,-cx+300,cy+300);
		g2d.drawLine(-cy+300,cx+300,-cy+300,cx+300);
		
		if(d<0){
			d = d+(2*cx);
			System.out.println("d inside if "+ d);
		}
		else{
			d = d+(2*(cx-cy));
			System.out.println("d inside else "+ d);
			cy = cy-1;			
		}
		cx = cx + 1;
	}while(cx<cy);
	
	}//for loop ends
	
	}

	private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
		g.drawLine(x1, getHeight() - y1, x2, getHeight() - y2);
	}

	

}
