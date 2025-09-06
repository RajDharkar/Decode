package teamcode.robot.subsystems;

import static org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit.AMPS;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import teamcode.utils.MyTelem;

@Config
public class BoxtubeExtension implements Subsystem {
    DcMotorEx ext;
    public static double p = 0, i = 0, d = 0, f = 0;
    PIDController extensionPID = new PIDController(p, i, d);
    public double targetPosition;

    public BoxtubeExtension(DcMotorEx boxtube){
        ext = boxtube;
        extensionPID.setPID(p, i, d);
        extensionPID.setTolerance(5);
        targetPosition = 0;
    }

    @Override
    public void periodic(){
        extensionPID.setPID(p, i, d);
        int pos = getCurrentPosition();
        double power = extensionPID.calculate(pos, targetPosition);

        power += f;
        if(pos == 0){
            power = -0.2;
        }

        ext.setPower(power);
        MyTelem.addData("Extension Pos", ext.getCurrentPosition());
//        MyTelem.addData("Extension Current Amps", ext.getCurrent(AMPS)); //NOT A BULKREAD DONT TUNE WITH THIS ON C!!J!@04j)!J@4
    }

    public int getCurrentPosition(){
        return ext.getCurrentPosition();
    }

    public void setTargetPosition(int targetPos){
        targetPosition = targetPos;
    }
    public boolean hasReached(){
        return extensionPID.atSetPoint();
    }

}
