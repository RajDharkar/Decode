package teamcode.opmodes.testers;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import teamcode.robot.Robot;
import teamcode.utils.MyTelem;

@TeleOp
public class TestingRobot extends LinearOpMode {
    Robot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        MyTelem.init(telemetry);
        robot = new Robot(hardwareMap, false);

        GamepadEx gp1 = new GamepadEx(gamepad1);

//        gp1.getGamepadButton(GamepadKeys.Button.A).whenPressed(
//            new SequentialCommandGroup(
//                    new InstantCommand(() -> robot.arm.setTurretState(Arm.TurretState.INTAKE)),
//                    new WaitCommand(1000),
//                    new InstantCommand(() -> robot.arm.setTurretState(Arm.TurretState.SPEC_DEPO))
//            )
//        );

        waitForStart();

        while(opModeIsActive()){
            MyTelem.addData("HELLO WORLD", true);
            robot.update();
        }
        robot.stop();
    }
}
