package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.calculations.ShooterCalculations;
import org.firstinspires.ftc.teamcode.robot.calculations.ShooterInput;
import org.firstinspires.ftc.teamcode.utils.MyTelem;
import org.firstinspires.ftc.teamcode.utils.constants.ShooterConstants;

@Config
public class Shooter implements Subsystem {
    DcMotorEx topShooter, bottomShooter;
    Servo hood;
    PIDController rpmPID;

    public ShooterState state = ShooterState.STOP;
    public Shooter(DcMotorEx topShooter, DcMotorEx bottomShooter, Servo hood){
        rpmPID = new PIDController(ShooterConstants.kp, ShooterConstants.ki, ShooterConstants.kd);
        rpmPID.setTolerance(10);

        this.topShooter = topShooter;
        this.bottomShooter = bottomShooter;
        this.hood = hood;
    }

    public void setState(ShooterState state){
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
            case TESTING:
                setPIDPower(ShooterConstants.tuningTestingRPM);
                break;
            case REV:
                topShooter.setPower(-0.5);
                bottomShooter.setPower(-0.5);
                break;
        }
    }

    public void setHood(double pos){
        hood.setPosition(pos);
    }
    public double ticksPerSecToRPM(double tps){
        return tps * 60.0 / ShooterConstants.TICKS_PER_REV;
    }
    public void setPIDPower(double targetRPM){
        double topVelocity = Math.abs(topShooter.getVelocity());

        double currentRPM = (ticksPerSecToRPM(topVelocity)) / 2;
        MyTelem.addData("Shooter Current RPM", currentRPM);
        rpmPID.setPID(ShooterConstants.kp, ShooterConstants.ki, ShooterConstants.kd);
        double power = rpmPID.calculate(currentRPM, targetRPM);
        power += (targetRPM > 0) ? (ShooterConstants.kf * (targetRPM / ShooterConstants.MAX_RPM)) : 0.0;
        power = Range.clip(power, 0, 1);
        MyTelem.addData("Power", power);
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

    @Override
    public void periodic() {
        setState(state);
        rpmPID.setPID(ShooterConstants.kp, ShooterConstants.ki, ShooterConstants.kd);
    }

    public enum ShooterState {
        CLOSE, FAR, STOP, TESTING, REV
    }
}
