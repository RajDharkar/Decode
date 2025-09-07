package org.firstinspires.ftc.teamcode.robot;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
import org.firstinspires.ftc.teamcode.utils.MyTelem;

import java.util.List;

@Config
public class Robot {
    public Follower follower;

    // hardware stuff, servos, motors, etc.

    // all subsystem classes

    public List<LynxModule> hubs;

    public Robot (HardwareMap hm, boolean isAuto) {
        follower = new Follower(hm, FConstants.class, LConstants.class);

        //declare all hardware names
        //ie. name = hardwareMap.get(Servo.class, "...");

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
