package domainhub;

/**
 *
 * @author Utpal
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
public class Look extends Core implements MouseMotionListener,KeyListener{
    public static void main(String[] args)
    {
        new Look().run();
    }
    
    private Image bg;
    private Robot robot;
    private Point mouse;
    private Point center;
    private Point image;
    private boolean centerring;
    
    
     
    public void init()
    {
        super.init();
        mouse=new Point();
        center=new Point();
        image=new Point();
        centerring=false;
        
        try{
            robot=new Robot();
            recenterMouse();
            mouse.x=center.x;
            mouse.y=center.y;
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception 1");
        }
        Window w= s.getFullScreenWindow();
        w.addMouseMotionListener(this);
        w.addKeyListener(this);
        bg=new ImageIcon("fdgdfg").getImage();
    }
        
        public synchronized void draw(Graphics2D g)
        {
         int w=s.getWidth();
         int h=s.getHeight();
         image.x%=w;
         image.y&=h;
         if(image.x<0)
         {
             image.x+=w;
             
         }
         if(image.y<0)
         {
             image.y+=h;
         }
         int x=image.x;
         int y=image.y;
         g.drawImage(bg,x,y,null);
         g.drawImage(bg,x-w,y,null);
         g.drawImage(bg,x,y-h,null);
         g.drawImage(bg,x-w,y-h,null);
         
        }
        
        private synchronized void recenterMouse()
        {
            Window w= s.getFullScreenWindow();
            if(robot!=null && w.isShowing())
            {
                center.x=w.getWidth()/2;
                center.y=w.getHeight()/2;
                SwingUtilities.convertPointToScreen(center,w);
                centerring=true;
                robot.mouseMove(center.x,center.y);
                
            }
        }
    @Override
    public void mouseDragged(MouseEvent e)
    {
        mouseMoved(e);
    }
    public synchronized void mouseMoved(MouseEvent e)
    {
        if(centerring && center.x==e.getX() && center.y==e.getY())
        {
            centerring=false;
        }
        else
        {
            int dx=e.getX()-mouse.x;
            int dy=e.getY()-mouse.y;
            image.x+=dx;
            image.y+=dy;
            recenterMouse();
            
        }
        mouse.x=e.getX();
        mouse.y=e.getY();
    }
    
    
    public void KeyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
            stop();
        }
    }
    
    public void KeyReleased(KeyEvent e){}
    public void KeyTyped(KeyEvent e){}
      


}
