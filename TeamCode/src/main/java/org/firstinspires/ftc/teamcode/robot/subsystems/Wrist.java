package teamcode.robot.subsystems;

import static teamcode.utils.constants.BotConstants.wristIntake;
import static teamcode.utils.constants.BotConstants.wristSample;
import static teamcode.utils.constants.BotConstants.wristSpecDepo;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.Servo;

public class Wrist implements Subsystem {
    public Servo wristLeft;
    public Servo wristRight;

    public enum WristState{
        INTAKE, SPEC_DEPO, SAMPLE_DEPO
    }

    public WristState wristState;

    public Wrist(Servo wristLeft, Servo wristRight){
        this.wristLeft = wristLeft;
        this.wristRight = wristRight;
    }

    public void setWristState(WristState state){
        switch(state){
            case INTAKE:
                wristLeft.setPosition(wristIntake);
                wristRight.setPosition(wristIntake);
                break;
            case SPEC_DEPO:
                wristLeft.setPosition(wristSpecDepo);
                wristRight.setPosition(wristSpecDepo);
                break;
            case SAMPLE_DEPO:
                wristRight.setPosition(wristSample);
                wristLeft.setPosition(wristSample);
                break;
        }
    }
}
