import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.*;
import javax.media.opengl.awt.*;
import javax.swing.JFrame;

public class Mid_Point_for_8_Zone implements GLEventListener {

    static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);

    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile

    	Mid_Point_for_8_Zone l = new Mid_Point_for_8_Zone();
        //creating frame
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(600, 480);

        final JFrame frame = new JFrame("straight Line");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);

    }

    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glBegin(GL2.GL_POINTS);//static field

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\mahmu\\Desktop\\point.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Line.class.getName()).log(Level.SEVERE, null, ex);
        }
        String intake;
        String[] breaker;
        float x0, y0, x1, y1, dx, dy;
        try {
            while ((intake = br.readLine()) != null) {
                breaker = intake.split(" ");
                x0 = Float.parseFloat(breaker[0].trim());
                y0 = Float.parseFloat(breaker[1].trim());
                x1 = Float.parseFloat(breaker[2].trim());
                y1 = Float.parseFloat(breaker[3].trim());
                dx = x1 - x0;
                dy = y1 - y0;
                System.out.println(x0 + " " + " " + y0 + " " + x1 + " " + y1);
                if (Math.abs(dx) >= Math.abs(dy)) {
                    if (dx >= 0 && dy >= 0) {
                        drawLine_0(x0, y0, x1, y1, gl);
                        System.out.println("zone0");
                    } else if (dx >= 0 && dy < 0) {
                        drawLine_7(x0, y0, x1, y1, gl);
                        System.out.println("zone7");
                    } else if (dx < 0 && dy >= 0) {
                        drawLine_3(x0, y0, x1, y1, gl);
                        System.out.println("zone3");
                    } else if (dx < 0 && dy < 0) {
                        drawLine_4(x0, y0, x1, y1, gl);
                        System.out.println("zone4");
                    }
                } else if (Math.abs(dx) < Math.abs(dy)) {

                    if (dx >= 0 && dy >= 0) {
                        drawLine_1(x0, y0, x1, y1, gl);
                        System.out.println("zone1");
                    } else if (dx >= 0 && dy < 0) {
                        drawLine_6(x0, y0, x1, y1, gl);
                        System.out.println("zone6");
                    } else if (dx < 0 && dy >= 0) {
                        drawLine_2(x0, y0, x1, y1, gl);
                        System.out.println("zone2");
                    } else if (dx < 0 && dy < 0) {
                        drawLine_5(x0, y0, x1, y1, gl);
                        System.out.println("zone5");
                    }

                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Line.class.getName()).log(Level.SEVERE, null, ex);
        }

        gl.glEnd();
        System.out.println("Ending");

    }

    public void drawLine_0(float x0, float y0, float x1, float y1, GL2 gl) {
        float inc = .001f;
        float dx = x1 - x0;
        float dy = y1 - y0;
        float d = 2 * dy - dx;
        float dne = 2 * dy - 2 * dx;
        float de = 2 * dy;
        float x = x0, y = y0;
        System.out.println("x y " + x + " " + y);
        //gl.glColor3f(1f, 1f, 1f);
        gl.glVertex2d(x, y);
        while (x < x1) {
            if (d > 0) {
                x += inc;
                y += inc;
                d += dne;
            } else {
                y += inc;
                x += inc;
                d += de;
            }
            //System.out.println("x y " + x + " " + y);
            //gl.glColor3f(1f, 1f, 1f);
            gl.glVertex2d(x, y);

        }

    }

    public void drawLine_1(float x0, float y0, float x1, float y1, GL2 gl) {
        float inc = .001f;
        float dx = x1 - x0;
        float dy = y1 - y0;
        float d = dy - 2 * dx;
        float dn = - 2 * dx;
        float dne = 2 * dy - 2 * dx;
        float x = x0, y = y0;
        //gl.glColor3f(0f, 2f, 2f);
        gl.glVertex2d(x, y);
        while (y < y1) {
            if (d > 0) {
                y += inc;
                d += dn;
            } else {
                y += inc;
                x += inc;
                d += dne;
            }
           // System.out.println("x y " + x + " " + y);
            //gl.glColor3f(2f, 2f, 2f);
            gl.glVertex2d(x, y);
        }

    }

    public void drawLine_2(float x0, float y0, float x1, float y1, GL2 gl) {
        float inc = .001f;
        float dx = x1 - x0;
        float dy = y1 - y0;
        float d = - 2 * dx - dy;
        float dn = - 2 * dx;
        float dnw = -2 * dy - 2 * dx;
        float x = x0, y = y0;
        //gl.glColor3f(0f, 3f, 3f);
        gl.glVertex2d(x, y);
        while (y < y1) {
            if (d > 0) {
                y += inc;
                x -= inc;
                d += dnw;
            } else {
                y += inc;

                d += dn;
            }
            //System.out.println("x y " + x + " " + y);
            //gl.glColor3f(0f, 3f, 3f);
            gl.glVertex2d(x, y);
        }

    }

    public void drawLine_3(float x0, float y0, float x1, float y1, GL2 gl) {
        float inc = .001f;
        float dx = x1 - x0;
        float dy = y1 - y0;
        float d = -2 * dy - dx;
        float dw = - 2 * dy;
        float dnw = -2 * dy - 2 * dx;
        float x = x0, y = y0;
        //gl.glColor3f(1f, 0f, 0f);
        gl.glVertex2d(x, y);
        while (x >= x1) {
            if (d > 0) {
                x -= inc;

                d += dw;
            } else {
                x -= inc;
                y += inc;
                d += dnw;
            }
            //System.out.println("x y " + x + " " + y);
            //gl.glColor3f(1f, 0f, 0f);
            gl.glVertex2d(x, y);
        }

    }

    public void drawLine_4(float x0, float y0, float x1, float y1, GL2 gl) {
        float inc = .001f;
        float dx = x1 - x0;
        float dy = y1 - y0;
        float d = -2 * dy + dx;
        float dw = - 2 * dy;
        float dsw = -2 * dy + 2 * dx;
        float x = x0, y = y0;
        //gl.glColor3f(3.5f, 3.5f, 0f);
        gl.glVertex2d(x, y);
        while (x >= x1) {
            if (d > 0) {
                y -= inc;
                x -= inc;
                d += dsw;
            } else {

                x -= inc;
                d += dw;
            }
            //System.out.println("x y " + x + " " + y);
            //gl.glColor3f(3.5f, 3.5f, 0f);
            gl.glVertex2d(x, y);
        }

    }

    public void drawLine_5(float x0, float y0, float x1, float y1, GL2 gl) {
        float inc = .001f;
        float dx = x1 - x0;
        float dy = y1 - y0;
        float d = -dy + 2 * dx;
        float dsw = 2 * dx - 2 * dy;
        float ds = 2 * dx;
        float x = x0, y = y0;
        //gl.glColor3f(2.5f, 3.5f, 1.5f);
        gl.glVertex2d(x, y);
        while (y >= y1) {
            if (d > 0) {
                y += inc;

                d += ds;
            } else {
                y -= inc;
                x -= inc;
                d += dsw;
            }
            //System.out.println("x y " + x + " " + y);
            //gl.glColor3f(2.5f, 3.5f, 1.5f);
            gl.glVertex2d(x, y);
        }

    }

    public void drawLine_6(float x0, float y0, float x1, float y1, GL2 gl) {
        System.out.println("i am in zone 6");
        float inc = .001f;
        float dx = x1 - x0;
        float dy = y1 - y0;
        float d = dy + 2 * dx;
        float ds = 2 * dx;
        float dse = 2 * dy + 2 * dx;
        float x = x0, y = y0;
        gl.glVertex2d(x, y);
        //gl.glColor3f(2f, 0f, 3f);
        while (y >= y1) {
            if (d > 0) {
                y -= inc;
                x += inc;
                d += dse;
            } else {
                y -= inc;

                d += ds;
            }
            //System.out.println("x y " + x + " " + y);        
            //gl.glColor3f(2f, 0f, 3f);
            gl.glVertex2d(x, y);
        }

    }

    public void drawLine_7(float x0, float y0, float x1, float y1, GL2 gl) {
        float inc = .001f;
        float dx = x1 - x0;
        float dy = y1 - y0;
        float d = 2 * dy + dx;
        float dse = 2 * dx + 2 * dy;
        float de = 2 * dy;
        float x = x0, y = y0;
        gl.glVertex2d(x, y);
        //gl.glColor3f(3.9f, 2f, 1f);
        while (x < x1) {
            if (d > 0) {

                x += inc;

                d += de;
            } else {
                y -= inc;
                x += inc;
                d += dse;
            }
            //System.out.println("x y " + x + " " + y);
            //gl.glColor3d(3.9f, 2f, 1f);
            gl.glVertex2d(x, y);
        }

    }

    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    public void init(GLAutoDrawable drawable) {
        // method body
        //4. drive the display() in a loop
    }

    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }
    //end of main
}//end of classimport javax.media.opengl.GL2;