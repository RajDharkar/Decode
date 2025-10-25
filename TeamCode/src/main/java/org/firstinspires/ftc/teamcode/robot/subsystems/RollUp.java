package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.utils.constants.IntakeConstants;
import org.firstinspires.ftc.teamcode.utils.constants.RollUpConstants;

public class RollUp implements Subsystem {
    DcMotorEx rollUpMotor;
    public RollState state;
    public RollUp(DcMotorEx intakeMotor){
        this.rollUpMotor = intakeMotor;
    }

    public void setState(RollState state){
        this.state = state;
        switch (state){
            case ON:
                rollUpMotor.setPower(RollUpConstants.forwardPower);
                break;
            case OFF:
                rollUpMotor.setPower(RollUpConstants.offPower);
                break;
            default:
                rollUpMotor.setPower(0);
                break;
        }
    }

    public RollState getState(){
        return state;
    }
    public enum RollState{
        ON, OFF;
    }

}
