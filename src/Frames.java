import lombok.Setter;

import javax.swing.*;

import java.awt.*;

@Setter
public class Frames {
    private Context context;

    JFrame frame;
    JFrame frame1;
    JFrame settingWindow = new JFrame("Values Panel");
    JFrame picture = new JFrame("Interference Picture");

    public Frames(Context context) {
        this.context = context;
        initSettings();
        draw();
    }

    private void draw(){

        frame = new JFrame("Intensity Graph");
        frame1 = new JFrame("Custom Graph Builder");

        frame.add(new Intensity(context).getChartPanel());
        frame.setVisible(true);
        frame.setSize(600,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        if (context.isInterPicture()) initPicture();
        if (context.isMyGraph()) initMyGraphBuilder();

    }

    private void initMyGraphBuilder(){
        CustomGraphBuilder customGraphBuilder = new CustomGraphBuilder(context);
        frame1.getContentPane().add(customGraphBuilder);
        frame1.setVisible(true);
        frame1.setSize(700,605);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    private void initSettings(){
        settingWindow.setLayout(new FlowLayout());

        settingWindow.add(new Settings(this, context));
        settingWindow.setSize(436, 260);
        settingWindow.setVisible(true);
        settingWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        settingWindow.setLocation(610, 0);
    }

    private void initPicture(){
        JPanel panel = new Picture(context);
        this.picture.add(panel);
        picture.setVisible(true);
        picture.setSize(644, 80);
        picture.setResizable(false);
        picture.setLocation(0, 505);
        picture.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void reload(){
        frame1.dispose();
        frame.dispose();
        picture.dispose();
        context.getArray().clear();
        draw();
    }
}
