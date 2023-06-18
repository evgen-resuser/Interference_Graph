import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RainbowSlider extends JSlider {
    private Image img = null;

    public RainbowSlider(int min, int max){
        try {
            img = ImageIO.read(new File("src/sprites/spectre.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        super.setMaximum(max);
        super.setMinimum(min);
        super.setPaintTrack(false);
        super.setUI(new RainbowSliderUI(this));
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(img, 0, 0, null);
        super.paintComponent(g);
    }

    private static class RainbowSliderUI extends BasicSliderUI {

        public RainbowSliderUI(JSlider slider){
            super(slider);
        }

        @Override
        public void paintThumb(Graphics g){
            Image thumbIcon = null;

            try {
                thumbIcon = ImageIO.read(new File("src/sprites/thumb.png"));
            } catch (IOException e){
                e.printStackTrace();
            }

            Rectangle thumb = thumbRect;
            g.drawImage(thumbIcon, thumb.x, thumb.y, null);
        }

    }

}
