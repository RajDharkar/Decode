package org.firstinspires.ftc.teamcode.opmodes.testers;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.commands.IntakeCommand;

@TeleOp(name = "Intake Tester")
public class IntakeTester extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(hardwareMap, false);
        GamepadEx gp1 = new GamepadEx(gamepad1);
        gp1.getGamepadButton(GamepadKeys.Button.A).whenPressed(new IntakeCommand(robot, Intake.IntakeState.ON));
        gp1.getGamepadButton(GamepadKeys.Button.B).whenPressed(new IntakeCommand(robot, Intake.IntakeState.OFF));
        gp1.getGamepadButton(GamepadKeys.Button.X).whenPressed(new IntakeCommand(robot, Intake.IntakeState.REV));
        waitForStart();
        while (opModeIsActive()) {
            robot.update();
        }
        robot.stop();
    }
}
