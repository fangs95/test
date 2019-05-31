import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class JubingTest extends JFrame implements  MouseListener,MouseMotionListener{
    private  JLabel  statusBar;

    public JubingTest(){
        super("Demonstrating   mouse   event");
        statusBar=new  JLabel();
        getContentPane().add(statusBar,BorderLayout.SOUTH);
        addMouseListener(this);
        addMouseMotionListener(this);
        setSize(275,100);
        setVisible(true);
    }

    public void mouseClicked(MouseEvent   event)
    {
        statusBar.setText("clicked   at   ["+event.getX()+
                ","+event.getY()+"]");
    }
    public   void   mousePressed(MouseEvent   event)
    {
        statusBar.setText("Pressed   at["+event.getX()+
                ","+event.getY()+"]");
    }
    public   void   mouseReleased(MouseEvent   event)
    {
        statusBar.setText("Relessed   at["+event.getX()+
                ","+event.getY()+"]");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public   void   mouseDragged(MouseEvent   event)
    {
        statusBar.setText("Dragged   at["+event.getX()+
                ","+event.getY()+"]");
    }
    public   void   mouseMoved(MouseEvent   event)
    {
        statusBar.setText("Moved   at["+event.getX()+
                ","+event.getY()+"]");
    }

    public   static   void   main(String   args[])
    {
        JubingTest   application=new   JubingTest();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}