package org.firstinspires.ftc.teamcode.opmodes.prod;

import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.pedropathing.localization.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.commands.subsystemcommands.IntakeCommand;
import org.firstinspires.ftc.teamcode.robot.commands.subsystemcommands.KickerCommand;
import org.firstinspires.ftc.teamcode.robot.commands.subsystemcommands.ShooterCommand;
import org.firstinspires.ftc.teamcode.robot.commands.subsystemcommands.TurretCommand;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.Kicker;
import org.firstinspires.ftc.teamcode.robot.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;
import org.firstinspires.ftc.teamcode.utils.MyTelem;

@TeleOp(name = "Teleop")
public class Teleop extends LinearOpMode {
    @Override
    public void runOpMode() {
        MyTelem.init(telemetry);

        Robot robot = new Robot(hardwareMap, false);
        robot.follower.setStartingPose(new Pose(0,0,0));
        GamepadEx gp1 = new GamepadEx(gamepad1);
        GamepadEx gp2 = new GamepadEx(gamepad2);

        gp1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenReleased(new IntakeCommand(robot, Intake.IntakeState.OFF));
        gp1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new IntakeCommand(robot, Intake.IntakeState.REV));
        gp1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new IntakeCommand(robot, Intake.IntakeState.ON));
        gp1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenReleased(new IntakeCommand(robot, Intake.IntakeState.OFF));

        Trigger rightTrig = new Trigger(() -> gp1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5);
        Trigger leftTrig = new Trigger(() -> gp1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.5);

        rightTrig.whenActive(new ShooterCommand(robot, Shooter.ShooterState.CLOSE));
        leftTrig.whenActive(new ShooterCommand(robot, Shooter.ShooterState.FAR));

        rightTrig.whenInactive(new ShooterCommand(robot, Shooter.ShooterState.STOP));
        leftTrig.whenActive(new ShooterCommand(robot, Shooter.ShooterState.STOP));

        gp1.getGamepadButton(GamepadKeys.Button.B).whenPressed(new KickerCommand(robot, Kicker.KickerState.ON));
        gp1.getGamepadButton(GamepadKeys.Button.B).whenReleased(new KickerCommand(robot, Kicker.KickerState.OFF));

        gp1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(new TurretCommand(robot, Turret.TurretState.FRONT));
        gp1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(new TurretCommand(robot, Turret.TurretState.BACK));
        waitForStart();

        if(isStarted()){
            robot.follower.startTeleopDrive();
        }

        while (opModeIsActive()) {
            robot.follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, true);
            robot.update();
        }

        robot.stop();
    }
}
