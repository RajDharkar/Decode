package teamcode.pedroPathing.VisionPractice;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

public class Camera {
    private OpenCvWebcam camera;
    private TestPipeline pipeline;

    public Camera(HardwareMap hardwareMap){
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraModifierViewId", "id", hardwareMap.appContext.getPackageName());

        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "camera"), cameraMonitorViewId);

        camera.setPipeline(pipeline);

        camera.setMillisecondsPermissionTimeout(2500);

        camera.openCameraDeviceAsync(
                new OpenCvCamera.AsyncCameraOpenListener() {
                    @Override
                    public void onOpened() {
                        camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT); //update for camera specs
                    }

                    @Override
                    public void onError(int errorCode) {

                    }
                }
        );

    }
    public String getOutput(){
        return pipeline.getOutput();
    }
    public void stop() {
        camera.stopStreaming();
    }
}
