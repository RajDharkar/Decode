package org.firstinspires.ftc.teamcode.robot.commands.subsystemcommands;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.inspection.InspectionActivity;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.subsystems.Claw;

public class ToggleClaw extends ConditionalCommand {
    public ToggleClaw(Robot robot){
        super(
                new InstantCommand(() -> robot.claw.setClawState(Claw.ClawState.OPEN)),
                new InstantCommand(() -> robot.claw.setClawState(Claw.ClawState.CLOSED)),
                () -> robot.claw.clawState == Claw.ClawState.CLOSED
        );
    }
}
