package teamcode.robot.commands.subsystemcommands;

import com.arcrobotics.ftclib.command.CommandBase;

import teamcode.robot.Robot;

public class ExtendTo extends CommandBase {
    public Robot robot;

    public ExtendTo(Robot robot, int target){
        this.robot = robot;
        robot.boxtubePivot.setTargetPosition(target);

        addRequirements(robot.boxext);
    }

    @Override
    public boolean isFinished(){
        return robot.boxtubePivot.hasReached();
    }
}
