import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class Functions {

    public static double calculateIntensity(double theta, int holes, Context context){
        double res;

        double lamb = context.getLamb();
        double d = context.getD();

        double tmp = sinc(PI * context.getA() / lamb * sin(theta));
        double tmp1 = sin(holes * PI * d / lamb * sin(theta)) /
                sin (PI * d / lamb * sin(theta));

//        double tmp = sinc(a / d * theta);
//        double tmp1 = sin(holes * theta) /
//                sin (theta);

        res = context.getIo() * tmp*tmp * tmp1*tmp1;

        return res ;
    }

    private static double sinc(double angle){
        if (angle == 0) return 1;
        return sin(angle/**PI*/)/(angle/**PI*/);
    }

}
