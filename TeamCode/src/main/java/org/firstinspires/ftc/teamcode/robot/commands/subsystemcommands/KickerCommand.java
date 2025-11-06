package org.firstinspires.ftc.teamcode.robot.commands.subsystemcommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.subsystems.Kicker;

public class KickerCommand extends InstantCommand {
    public KickerCommand(Robot robot, Kicker.KickerState state) {
        super(() -> robot.kicker.setState(state));
    }
}
