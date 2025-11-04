package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot.calculations.ShooterCalculations;
import org.firstinspires.ftc.teamcode.robot.calculations.ShooterInput;
import org.firstinspires.ftc.teamcode.utils.constants.ShooterConstants;

@Config
public class Shooter implements Subsystem {
    DcMotorEx topShooter, bottomShooter;
    Servo hood;
    PIDController rpmPID = new PIDController(ShooterConstants.kp, ShooterConstants.ki, ShooterConstants.kd);

    public ShooterState state;
    public Shooter(DcMotorEx topShooter, DcMotorEx bottomShooter, Servo hood){
        rpmPID.setTolerance(10);

        this.topShooter = topShooter;
        this.bottomShooter = bottomShooter;
        this.hood = hood;
    }

    public void setState(ShooterState state){
        if (this.state == state){
            return;
        }
        this.state = state;
//        switch (state) {
//            case SHOOTING:
//                ShooterInput constants = ShooterCalculations.getHoodAndPower();
//                setPIDPower(constants.getMotorRPM());
//                setHood(constants.getHoodAngle());
//            case STOP:
//                setPIDPower(0);
//        }
        switch (state) {
            case CLOSE:
                setPIDPower(ShooterConstants.closeShootRPM);
                break;
            case FAR:
                setPIDPower(ShooterConstants.farShootRPM);
                break;
            case STOP:
                setPIDPower(0);
                break;
        }
    }

    public void setHood(double pos){
        hood.setPosition(pos);
    }
    public void setPIDPower(double targetRPM){
        double currentRPM = topShooter.getVelocity() + bottomShooter.getVelocity();
        currentRPM /= 2;

        double power = rpmPID.calculate(targetRPM, currentRPM) + ShooterConstants.kf;

        topShooter.setPower(power);
        bottomShooter.setPower(power);
    }

    public boolean atRPM(){
        return rpmPID.atSetPoint();
    }

    public ShooterState getState(){
        return state;
    }

//    public enum ShooterState{
//        SHOOTING, STOP
//    }

    public enum ShooterState {
        CLOSE, FAR, STOP
    }
}
