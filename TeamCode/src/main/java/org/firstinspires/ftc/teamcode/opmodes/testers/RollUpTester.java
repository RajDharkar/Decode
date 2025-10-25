package org.firstinspires.ftc.teamcode.opmodes.testers;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.subsystems.RollUp;
import org.firstinspires.ftc.teamcode.robot.commands.RollUpCommand;

@TeleOp(name = "RollUp Tester")
public class RollUpTester extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(hardwareMap, false);
        GamepadEx gp1 = new GamepadEx(gamepad1);
        gp1.getGamepadButton(GamepadKeys.Button.A).whenPressed(new RollUpCommand(robot, RollUp.RollState.ON));
        gp1.getGamepadButton(GamepadKeys.Button.B).whenPressed(new RollUpCommand(robot, RollUp.RollState.OFF));
        waitForStart();
        while (opModeIsActive()) {
            robot.update();
        }
        robot.stop();
    }
}
