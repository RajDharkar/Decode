package org.firstinspires.ftc.teamcode.robot.commands.subsystemcommands;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;
import org.firstinspires.ftc.teamcode.robot.subsystems.Wrist;

public class WristTo extends InstantCommand {
    public WristTo(Robot robot, Wrist.WristState state){
        super(() -> robot.wrist.setWristState(state));

        addRequirements(robot.wrist);
    }
}
