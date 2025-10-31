package org.firstinspires.ftc.teamcode.robot.calculations;

public class ShooterInput {
    private double hoodAngle;
    private double motorPower;

    public ShooterInput(double hoodAngle, double motorPower) {
        this.hoodAngle = hoodAngle;
        this.motorPower = motorPower;
        //hoodAngle is the servo position, between 0 and 1
    }

    public void setHoodAngle(double hoodAngle){
        this.hoodAngle = hoodAngle;
    }
    public void setMotorPower(double motorPower){
        this.motorPower = motorPower;
    }
    public double getHoodAngle(double motorPower){
        return motorPower;
    }
    public double hoodAngle(){
        return motorPower;
    }
}
