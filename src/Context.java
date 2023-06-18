import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Context {
    private int N = 4;
    private double d = 2500;
    private double lamb = 500;
    private double a = 500;
    private double Io = 1;

    private double maxI = 0;

    private List<Double> array = new ArrayList<>();

    private boolean extraGraph = false;
    private boolean myGraph = false;
    private boolean interPicture = false;
}
