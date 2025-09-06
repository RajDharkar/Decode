package teamcode.robot.commands.subsystemcommands;

import com.arcrobotics.ftclib.command.InstantCommand;

import teamcode.robot.Robot;
import teamcode.robot.subsystems.Claw;

public class SwivelTo extends InstantCommand {
    public SwivelTo(Robot robot, Claw.SwivelState state){
        super(() -> robot.claw.setSwivelState(state));
    }
}
