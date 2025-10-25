package org.firstinspires.ftc.teamcode.opmodes.testers;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.localization.Pose;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name = "Follower TeleOp Tester")
public class FollowerTeleOpTester extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(hardwareMap, false);
        Pose startPose = new Pose(0, 0, 0);
        robot.follower.setStartingPose(startPose);
        robot.follower.startTeleopDrive();
        GamepadEx gp1 = new GamepadEx(gamepad1);
        waitForStart();
        while (opModeIsActive()) {
            robot.follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, true);
            robot.update();
            telemetry.addData("X", robot.follower.getPose().getX());
            telemetry.addData("Y", robot.follower.getPose().getY());
            telemetry.addData("Heading degrees", Math.toDegrees(robot.follower.getPose().getHeading()));
            telemetry.update();
        }
        robot.stop();
    }
}
