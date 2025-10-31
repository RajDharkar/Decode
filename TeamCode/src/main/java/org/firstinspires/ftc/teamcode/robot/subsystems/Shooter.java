package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot.calculations.ShooterCalculations;
import org.firstinspires.ftc.teamcode.robot.calculations.ShooterInput;

@Config
public class Shooter implements Subsystem {
    DcMotorEx topShooter, bottomShooter;
    Servo hood;

    public static double kf = 0, kp = 0, ki = 0, kd = 0;
    PIDController rpmPID = new PIDController(kp, ki, kd);

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
        switch (state) {
            case SHOOTING:
                ShooterInput constants = ShooterCalculations.getHoodAndPower();
                setPIDPower(constants.getMotorRPM());
                setHood(constants.getHoodAngle());
            case STOP:
                setPIDPower(0);
        }
    }

    public void setHood(double pos){
        hood.setPosition(pos);
    }
    public void setPIDPower(double targetRPM){
        double currentRPM = topShooter.getVelocity() + bottomShooter.getVelocity();
        currentRPM /= 2;

        double power = rpmPID.calculate(targetRPM, currentRPM) + kf;

        topShooter.setPower(power);
        bottomShooter.setPower(power);
    }

    public boolean atRPM(){
        return rpmPID.atSetPoint();
    }

    public enum ShooterState{
        SHOOTING, STOP
    }
}
