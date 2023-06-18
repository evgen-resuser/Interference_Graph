import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {

    JTextField holeSizeArea = new JTextField("500");
    JTextField distArea = new JTextField("2500");
    JTextField holesArea = new JTextField("4");
    JTextField IoArea = new JTextField("1");

    JSlider slider = new RainbowSlider(380, 780);

    JCheckBox extraGraph = new JCheckBox("Diffraction term graph");

    JLabel waveLengthLabel = new JLabel("λ - Wave Length (nm): 500");
    JLabel distanceLabel = new JLabel("d - Grid Distance (mm): ");
    JLabel holeSizeLabel = new JLabel("a - Hole Size (mm): ");
    JLabel holesNumLabel = new JLabel("N - Holes Count: ");
    JLabel IoLabel = new JLabel("Io - Intensity0: ");

    JButton button = new JButton("Create graph");

    public Settings(IntensityGraph intensityGraph){

        this.setVisible(true);
        this.setLayout(new GridLayout(6, 2, 5, 5));

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
        this.add(button);

        button.addActionListener(e -> {
            try {
                intensityGraph.setA(Double.parseDouble(holeSizeArea.getText()));
                intensityGraph.setD(Double.parseDouble(distArea.getText()));
                intensityGraph.setN(Integer.parseInt(holesArea.getText()));
                intensityGraph.setIo(Double.parseDouble(IoArea.getText()));

                intensityGraph.setLamb(slider.getValue());

                intensityGraph.setExtraGraph(extraGraph.isSelected());

                intensityGraph.reload();
            } catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(intensityGraph.settingWindow, "Wrong Input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void sliderInit(){
        slider.setOpaque(false);
        slider.setValue(500);
        slider.addChangeListener(e -> waveLengthLabel.setText("λ - Wave Length (nm): "+slider.getValue()));
    }

}
