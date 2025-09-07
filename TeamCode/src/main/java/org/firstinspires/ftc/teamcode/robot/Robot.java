package org.firstinspires.ftc.teamcode.robot;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;
import org.firstinspires.ftc.teamcode.robot.subsystems.BoxtubeExtension;
import org.firstinspires.ftc.teamcode.robot.subsystems.BoxtubePivot;
import org.firstinspires.ftc.teamcode.robot.subsystems.Claw;
import org.firstinspires.ftc.teamcode.robot.subsystems.Wrist;
import org.firstinspires.ftc.teamcode.utils.MyTelem;

@Config
public class Robot {
    public Follower follower;
    public Servo wristLeft, wristRight;
    public Servo turretS;
    public Servo swivel;
    public Servo c;

    public Claw claw;
    public Turret turret;
    public Wrist wrist;

    public DcMotorEx ext;
    public DcMotorEx pivot;

    public BoxtubeExtension boxext;
    public BoxtubePivot boxtubePivot;

    public List<LynxModule> ar;
    public Robot(HardwareMap hm, boolean auto){
        follower = new Follower(hm, FConstants.class, LConstants.class);

        wristLeft = hm.get(Servo.class, "wristLeft");
        wristRight = hm.get(Servo.class, "wristRight");

        wristLeft.setDirection(Servo.Direction.REVERSE);

        turretS = hm.get(Servo.class, "turret");

        c = hm.get(Servo.class, "claw");
        swivel = hm.get(Servo.class, "swivel");

        ext = hm.get(DcMotorEx.class, "extension");
        pivot = hm.get(DcMotorEx.class, "pivot");

        pivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        ext.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        if(auto){
            ext.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            pivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        pivot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ext.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        claw = new Claw(c, swivel);
        turret = new Turret(turretS);
        wrist = new Wrist(wristLeft, wristRight);
        boxext = new BoxtubeExtension(ext);
        boxtubePivot = new BoxtubePivot(pivot);

        CommandScheduler.getInstance().registerSubsystem(claw, turret, wrist, boxext, boxtubePivot);

        ar = hm.getAll(LynxModule.class);
        for(LynxModule huh : ar){
            huh.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }

    }

    public void update(){
        CommandScheduler.getInstance().run();
        follower.update();

        for(LynxModule hub : ar){
            hub.clearBulkCache();
        }
        MyTelem.addData("HELLO WORLD", true);
        MyTelem.update();
    }
    public void stop(){
        for(LynxModule hub : ar){
            hub.clearBulkCache();
        }
        CommandScheduler.getInstance().reset();
    }
}
