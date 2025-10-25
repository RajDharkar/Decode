package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.utils.constants.IntakeConstants;

public class Intake implements Subsystem {
    DcMotorEx intakeMotor;
    public Intake(DcMotorEx intakeMotor){
        this.intakeMotor = intakeMotor;
    }

    public void setState(IntakeState state){
        switch (state){
            case ON:
                intakeMotor.setPower(IntakeConstants.forwardPower);
                break;
            case OFF:
                intakeMotor.setPower(IntakeConstants.offPower);
                break;
            case REV:
                intakeMotor.setPower(IntakeConstants.backwardPower);
                break;
            default:
                intakeMotor.setPower(0);
                break;
        }
    }
    enum IntakeState{
        ON, OFF, REV;
    }

}
