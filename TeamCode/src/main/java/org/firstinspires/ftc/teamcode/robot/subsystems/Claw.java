package teamcode.robot.subsystems;

import static teamcode.utils.constants.BotConstants.*;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.Servo;
public class Claw implements Subsystem {
    public Servo swivel;
    public Servo claw;

    public enum ClawState{
        CLOSED, OPEN
    }

    public enum SwivelState{
        TOP, BOTTOM, SIDEWAYS
    }
    public ClawState clawState;
    public SwivelState swivelState;
    public Claw(Servo swivel, Servo claw){
        this.swivel = swivel;
        this.claw = claw;

        clawState = ClawState.OPEN;
        swivelState = SwivelState.TOP;
    }

    public void setClawState(ClawState state){
        switch(state){
            case OPEN:
                claw.setPosition(clawOpen);
                break;
            case CLOSED:
                claw.setPosition(clawClosed);
                break;
        }
    }

    public void setSwivelState(SwivelState state){
        switch(state){
            case TOP:
                swivel.setPosition(swivelTop);
                break;
            case BOTTOM:
                swivel.setPosition(swivelBottom);
                break;
            case SIDEWAYS:
                swivel.setPosition(swivelSideways);
                break;
        }
    }
}
