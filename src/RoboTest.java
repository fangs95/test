import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.SampleModel;
import java.io.*;

public class RoboTest {
    public static void main(String[] args) {
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //new Rectangle(100, 100, 100, 100)坐标100，100的位置上取一个大小为100，100的像素矩形
        //r.createScreenCapture通过它来获取这个像素矩形里面的图片信息
        BufferedImage bi = r.createScreenCapture(new Rectangle(100, 100, 100, 100));
        int type = bi.getType();
        int height = bi.getHeight();
        int width = bi.getWidth();
        SampleModel sampleModel = bi.getSampleModel();
        int dataType = sampleModel.getDataType();
        System.out.println("type"+type);
        System.out.println("height"+height);//图片的高
        System.out.println("width"+width);//图片的宽
        System.out.println("sampleModel"+sampleModel);
        System.out.println("dataType"+dataType);
        //设定存在哪。存的文件名叫啥
        File f = new File("D:\\save.png");
        //打开一个输出流
        OutputStream os = null;
        try {
            os = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //bi.flush();这个是刷新操作，具体干啥的不知道
        //第一个参数传BufferedImage，第二个传图片类型，
        //支持，png,，pg,，gif
        //第三个传一个OutputStream流
        //成功写入磁盘
        try {
            ImageIO.write(bi, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
