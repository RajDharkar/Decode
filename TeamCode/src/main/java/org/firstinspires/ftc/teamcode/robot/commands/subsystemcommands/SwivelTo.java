package org.firstinspires.ftc.teamcode.robot.commands.subsystemcommands;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.subsystems.Claw;

public class SwivelTo extends InstantCommand {
    public SwivelTo(Robot robot, Claw.SwivelState state){
        super(() -> robot.claw.setSwivelState(state));
    }
}
