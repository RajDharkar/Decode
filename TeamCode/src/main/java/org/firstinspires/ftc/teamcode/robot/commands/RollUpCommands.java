package org.firstinspires.ftc.teamcode.robot.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.robot.subsystems.RollUp;

public class RollUpCommand extends InstantCommand {
    public SetRollUpCommand(Robot robot, RollUp.RollState state) {
        super(() -> robot.rollUp.setState(state));
    }
}
