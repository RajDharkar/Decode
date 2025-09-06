package teamcode.robot.subsystems;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.Servo;
import static teamcode.utils.constants.BotConstants.*;

public class Turret implements Subsystem {
    public Servo turret;

    public Turret(Servo turret){
        this.turret = turret;
    }

    public TurretState turretState;

    public enum TurretState{
        INTAKE, SPEC_DEPO, SAMPLE_DEPO
    }

    public void setTurretState(TurretState state){
        switch(state){
            case INTAKE:
                turret.setPosition(turretIntake);
                break;
            case SPEC_DEPO:
                turret.setPosition(turretSpecDepo);
                break;
            case SAMPLE_DEPO:
                turret.setPosition(turretSample);
                break;
        }
    }
}
