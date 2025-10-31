package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Shooter {
    DcMotorEx topShooter, bottomShooter;
    public ShooterState state;
    public Shooter(DcMotorEx topShooter, DcMotorEx bottomShooter){
        this.topShooter = topShooter;
        this.bottomShooter = bottomShooter;
    }

    public void setState(ShooterState state){
        this.state = state;
        switch (state) {
            case ON:
                topShooter.setPower();

        }
    }
    public enum ShooterState{
        ON, OFF
    }
}
