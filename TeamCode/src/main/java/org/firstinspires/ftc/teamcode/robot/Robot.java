package org.firstinspires.ftc.teamcode.robot;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
import org.firstinspires.ftc.teamcode.utils.MyTelem;

import java.util.List;

@Config
public class Robot {
    boolean auto = false;

    public Follower follower;

    // hardware stuff, servos, motors, etc.
    DcMotorEx backLeft, backRight, frontLeft, frontRight;
    DcMotorEx topShooter, bottomShooter;
    DcMotorEx rollUp;
    DcMotorEx intake;

    // all subsystem classes

    public List<LynxModule> hubs;

    public Robot (HardwareMap hm, boolean isAuto) {
        auto = isAuto;

        follower = new Follower(hm, FConstants.class, LConstants.class);
        follower.breakFollowing();

        topShooter = hardwareMap.get(DcMotorEx.class, "topShooter");
        bottomShooter = hardwareMap.get(DcMotorEx.class, "bottomShooter");

        rollUp = hardwareMap.get(DcMotorEx.class, "rollUp");

        intake = hardwareMap.get(DcMotorEx.class, "intake");
        //declare all hardware names

        //ie. name = hardwareMap.get(Servo.class, "...");

        // We can in pedro for this as well.
        //        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        //        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        //        frontLeft = hardwareMap.get(DcMotorEx.class, "frontRight");
        //        frontRight = hardwareMap.get(DcMotorEx.class, "frontLeft");


        //handle auto specific behaviors
        //ie. resetting motor encoders, etc.


        //handle hardware specific settings
        //ie. zeropowermode, etc.

        //declare all subsystem objects
        //ie. turret = new Turret()

        //register subsystems
        //CommandScheduler.getInstance().register()

        hubs = hm.getAll(LynxModule.class);
        for (LynxModule hub : hubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
    }

    public void update(){
        CommandScheduler.getInstance().run();
        follower.update();

        for(LynxModule hub : hubs){
            hub.clearBulkCache();
        }

        MyTelem.update();
    }

    public void stop(){
        for(LynxModule hub : hubs){
            hub.clearBulkCache();
        }
        CommandScheduler.getInstance().reset();
    }

}
