package teamcode.robot.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

import teamcode.utils.MyTelem;

@Config
public class BoxtubePivot implements Subsystem {
    DcMotorEx pivot;

    public static double p = 0, i = 0, d = 0, f = 0;
    PIDController pivotPID = new PIDController(p, i, d);
    public double targetPosition;
    public double ticksInDegree = 0; //needs to be updated

    public BoxtubePivot(DcMotorEx pivot){
        this.pivot = pivot;
        pivotPID.setPID(p, i, d);
        pivotPID.setTolerance(10);
        targetPosition = 0;
    }

    @Override
    public void periodic(){
        pivotPID.setPID(p, i, d);
        int pos = getCurrentPosition();

        double power = pivotPID.calculate(pos, targetPosition);
        double angle = targetPosition / ticksInDegree;
        angle = Math.toRadians(angle);
        pivot.setPower(power + Math.cos(angle));

        MyTelem.addData("Pivot Position", pos);
//        MyTelem.addData("Pivot AMPS", pivot.getCurrent(CurrentUnit.AMPS)); //NOT A BULKREaD DonT TunE If ITS oN!
    }

    public int getCurrentPosition(){
        return pivot.getCurrentPosition();
    }

    public void setTargetPosition(int targetPos){
        targetPosition = targetPos;
    }
    public boolean hasReached(){
        return pivotPID.atSetPoint();
    }

}
