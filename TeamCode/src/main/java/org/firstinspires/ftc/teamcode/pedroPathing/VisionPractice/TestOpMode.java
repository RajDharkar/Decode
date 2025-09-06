package teamcode.pedroPathing.VisionPractice;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Config
@TeleOp
public class TestOpMode extends LinearOpMode {
    Camera camera;
    @Override
    public void runOpMode() throws InterruptedException {
        camera = new Camera(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Camera Output", camera.getOutput());
            telemetry.update();
        }
    }
}
