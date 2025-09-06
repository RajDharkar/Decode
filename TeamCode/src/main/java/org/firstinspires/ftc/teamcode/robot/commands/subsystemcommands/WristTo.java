package teamcode.robot.commands.subsystemcommands;

import com.arcrobotics.ftclib.command.InstantCommand;

import teamcode.robot.Robot;
import teamcode.robot.subsystems.Turret;
import teamcode.robot.subsystems.Wrist;

public class WristTo extends InstantCommand {
    public WristTo(Robot robot, Wrist.WristState state){
        super(() -> robot.wrist.setWristState(state));

        addRequirements(robot.wrist);
    }
}
