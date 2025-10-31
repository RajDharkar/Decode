package org.firstinspires.ftc.teamcode.robot.calculations;

public class ShooterInput {
    private double hoodAngle, motorRPM;
    public ShooterInput(double hoodAngle, double motorRPM) {
        this.hoodAngle = hoodAngle;
        this.motorRPM = motorRPM;
        //hoodAngle is the servo position, between 0 and 1
        //motorRPM is just the rpm
    }

    public void setHoodAngle(double hoodAngle){
        this.hoodAngle = hoodAngle;
    }
    public void setMotorRPM(double motorRPM){
        this.motorRPM = motorRPM;
    }
    public double getHoodAngle(){
        return hoodAngle;
    }
    public double getMotorRPM(){
        return motorRPM;
    }
}
