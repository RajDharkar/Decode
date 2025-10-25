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
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.utils.MyTelem;

import java.util.List;

@Config
public class Robot {
    boolean auto = false;

    public Follower follower;

    // hardware stuff, servos, motors, etc.
    DcMotorEx backLeftMotor, backRightMotor, frontLeftMotor, frontRightMotor;
    DcMotorEx topShooterMotor, bottomShooterMotor;
    DcMotorEx intakeMotor; Intake intake;

    // all subsystem classes
    public List<LynxModule> hubs;

    public Robot (HardwareMap hm, boolean isAuto) {
        auto = isAuto;

        follower = new Follower(hm, FConstants.class, LConstants.class);
        follower.breakFollowing();

        topShooterMotor = hm.get(DcMotorEx.class, "topShooter");
        bottomShooterMotor = hm.get(DcMotorEx.class, "bottomShooter");
        intakeMotor = hm.get(DcMotorEx.class, "intake");
        //declare all hardware names

        //ie. name = hm.get(Servo.class, "...");

        // We can in pedro for this as well.
        //        backLeft = hm.get(DcMotorEx.class, "backLeft");
        //        backRight = hm.get(DcMotorEx.class, "backRight");
        //        frontLeft = hm.get(DcMotorEx.class, "frontRight");
        //        frontRight = hm.get(DcMotorEx.class, "frontLeft");


        //handle auto specific behaviors
        //ie. resetting motor encoders, etc.


        //handle hardware specific settings
        //ie. zeropowermode, etc.

        //declare all subsystem objects
        //ie. turret = new Turret()
        intake = new Intake(intakeMotor);

        //register subsystems
        CommandScheduler.getInstance().registerSubsystem(intake);

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
        CommandScheduler.getInstance().reset();

        for(LynxModule hub : hubs){
            hub.clearBulkCache();
        }
    }

}
