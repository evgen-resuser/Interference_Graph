import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class RainbowSlider extends JSlider {
    private Image img = null;

    public RainbowSlider(int min, int max){
        try {
            InputStream stream = getClass().getResourceAsStream("sprites/spectre.png");
            assert stream != null;
            img = ImageIO.read(stream);
        } catch (IOException e){
            e.printStackTrace();
        }
        super.setMaximum(max);
        super.setMinimum(min);
        super.setPaintTrack(false);
        super.setUI(new RainbowSliderUI(this));
        super.setOpaque(false);
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
                InputStream stream = getClass().getResourceAsStream("sprites/thumb.png");
                assert stream != null;
                thumbIcon = ImageIO.read(stream);
            } catch (IOException e){
                e.printStackTrace();
            }

            Rectangle thumb = thumbRect;
            g.drawImage(thumbIcon, thumb.x, thumb.y, null);
        }

    }

}
