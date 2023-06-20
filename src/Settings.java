import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

public class Settings extends JPanel {

    JTextField holeSizeArea = new JTextField("500");
    JTextField distArea = new JTextField("2500");
    JTextField holesArea = new JTextField("4");
    JTextField IoArea = new JTextField("1");

    JSlider slider = new RainbowSlider(380, 780);

    JCheckBox extraGraph = new JCheckBox("Diffraction Term Graph");
    JCheckBox enableMyGraphBuilder = new JCheckBox("Custom graph builder (WIP)");
    JCheckBox interPicture = new JCheckBox("Interference Picture");

    JLabel waveLengthLabel = new JLabel("λ - Wave Length (nm): 500");
    JLabel distanceLabel = new JLabel("d - Grid Distance: ");
    JLabel holeSizeLabel = new JLabel("a - Hole Size: ");
    JLabel holesNumLabel = new JLabel("N - Holes Count: ");
    JLabel IoLabel = new JLabel("Io - Intensity0: ");

    JButton button = new JButton("Create graph");

    public Settings(Frames frames, Context context){

        this.setVisible(true);
        this.setLayout(new GridLayout(7, 2, 5, 5));

        this.setSize(50, 430);

        sliderInit();

        this.add(holeSizeLabel);
        this.add(holeSizeArea);

        this.add(waveLengthLabel);
        this.add(slider);

        this.add(distanceLabel);
        this.add(distArea);

        this.add(holesNumLabel);
        this.add(holesArea);

        this.add(IoLabel);
        this.add(IoArea);

        this.add(extraGraph);
        this.add(interPicture);
        this.add(enableMyGraphBuilder);

        this.add(button);

        button.addActionListener(e -> {
            try {
                if (abs(Double.parseDouble(distArea.getText())) <= abs(Double.parseDouble(holeSizeArea.getText())))
                    throw new NumberFormatException();

                context.setA(abs(Double.parseDouble(holeSizeArea.getText())));
                context.setD(abs(Double.parseDouble(distArea.getText())));
                context.setN(abs(Integer.parseInt(holesArea.getText())));
                context.setIo(abs(Double.parseDouble(IoArea.getText())));

                context.setLamb(slider.getValue());

                context.setExtraGraph(extraGraph.isSelected());
                context.setMyGraph(enableMyGraphBuilder.isSelected());
                context.setInterPicture(interPicture.isSelected());

                frames.reload();
            } catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(frames.settingWindow, "Wrong Input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void sliderInit(){
        slider.setValue(500);
        slider.addChangeListener(e -> waveLengthLabel.setText("λ - Wave Length (nm): "+slider.getValue()));
    }

}
