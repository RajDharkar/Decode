package teamcode.pedroPathing.VisionPractice;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class TestPipeline extends OpenCvPipeline {
    public Scalar lower = new Scalar(0, 0, 0);
    public Scalar upper = new Scalar(255, 255, 255);
    private String output = "nothing";
    private Mat hsvMat = new Mat();
    private Mat binaryMat = new Mat();
    private Mat maskedInputMat = new Mat();
    private Point topLeft1 = new Point(10, 0), bottomRight1 = new Point(40, 20);
    private Point topLeft2 = new Point(10, 0), bottomRight2 = new Point(40, 20);

    @Override
    public Mat processFrame(Mat input) { //processing the image matrix
//        output = "Sample Pipeline Is Running";
        Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);
        Core.inRange(hsvMat, lower, upper, binaryMat);

        double w1 = 0, w2 = 0;
        for(int i = (int) topLeft1.x; i <= bottomRight1.x; i++){
            for(int j = (int) bottomRight1.y; j <= topLeft1.y; j++){

            }
        }


        return input;
    }

    public String getOutput(){
        return output;
    }
}
