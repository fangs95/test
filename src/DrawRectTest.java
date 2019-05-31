import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
public class DrawRectTest
{
    public static void main(String[] args)
    {
        new NewFrame();
    }
}
class NewFrame extends JFrame
{
    private static final long serialVersionUID = 1L;
    /*
     * ����һ��С�Ĵ��ڡ������ť��������
     */
    JButton button;
    NewFrame()
    {
        setVisible(true);
        setLayout(new FlowLayout());
        setBounds(1000, 600, 100, 100);
        setResizable(false);
        button = new JButton("��ͼ");
        add(button);
        button.addActionListener(new ActionListener()
        {//�������ť��new һ��ScreenFrame�����ÿɼ���
            public void actionPerformed(ActionEvent e)
            {
                ScreenFrame sf = new ScreenFrame();
                sf.setAlwaysOnTop(true);
                sf.setUndecorated(true);
                sf.setVisible(true);
            }
        });
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }
}
class ScreenFrame extends JFrame
{
    private static final long serialVersionUID = 2L;
    /*
     * ����һ��ȫ���Ĵ��ڣ���ȫ����ͼ�����JFrame�Ĵ����ϣ��Թ���������
     */
    Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
    ScreenFrame()
    {
        //���ô�С����ȫ��
        setSize(di);
        //���ش˴���� contentPane����
        getContentPane().add(new DrawRect());
    }

    class DrawRect extends JPanel implements MouseMotionListener, MouseListener
    {
        private static final long serialVersionUID = 3L;
        /*
         * ��ȫ����ͼ�����JPanel �ϣ� ����ͨ��new DrawRect�����JPanel������JPanel����ȫ��ͼ��
         */
        int sx, sy, ex, ey;
        int count = 1;
        File file = null;
        BufferedImage image, getImage;
        boolean flag = true;
        public DrawRect()
        {
            try
            {//��ȡȫ��ͼ�����ݣ����ظ�image
                Robot robot = new Robot();
                image = robot.createScreenCapture(new Rectangle(0, 0, di.width, di.height));
            }
            catch(Exception e)
            {
                throw new RuntimeException("��ͼʧ��");
            }
            //���� ����¼�
            addMouseListener(this);
            addMouseMotionListener(this);
        }
        //��дpaintComponent��ͨ��repaint ��ʾ���������ķ�Χ
        public void paintComponent(Graphics g)
        {
            g.drawImage(image, 0, 0, di.width, di.height, this);
            g.setColor(Color.blue);
            if(sx < ex && sy < ey)//���½�
                g.drawRect(sx-1, sy-1, ex - sx+1, ey - sy+1);
            else                 //���Ͻ�
                g.drawRect(ex-1, ey-1, sx - ex+1, sy - ey+1);
        }
        //���¶�������¼�
        public void mouseClicked(MouseEvent e)
        {
            if(e.getButton() == MouseEvent.BUTTON3)//�Ҽ��˳�����
                System.exit(0);
            else if(e.getClickCount() == 2)   //˫������
            {
                try
                {
                    cutScreens();
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        //�Զ����������
        private void cutScreens() throws Exception
        {
            Robot ro = new Robot();
            if(sx < ex && sy < ey)//���½�
                getImage = ro.createScreenCapture(new Rectangle(sx, sy,	ex - sx, ey - sy));
            else                  //���Ͻ�
                getImage = ro.createScreenCapture(new Rectangle(ex, ey,	sx - ex, sy - ey));
            String name = "jietu" + count + ".png";
            file = new File("F:\\", name);
            while(file.exists())
            {
                String na = "jietu" + (count++) + ".png";
                file = new File("F:\\", na);
            }
            ImageIO.write(getImage, "png", file);
            System.exit(0);
        }
        public void mousePressed(MouseEvent e)
        {
            if(flag)
            {
                sx = e.getX();
                sy = e.getY();
            }
        }
        public void mouseReleased(MouseEvent e)
        {
            flag = false;
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e)	{}
        //����ƶ��У�ͨ��repaint ����Ҫ�����ķ�Χ
        public void mouseDragged(MouseEvent e)
        {
            ex = e.getX();
            ey = e.getY();
            repaint();
        }
        public void mouseMoved(MouseEvent e) {}
    }
}