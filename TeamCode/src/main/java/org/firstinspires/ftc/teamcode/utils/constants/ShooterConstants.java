package org.firstinspires.ftc.teamcode.utils.constants;

import com.acmerobotics.dashboard.config.Config;

@Config
public class ShooterConstants {
    public static double closeShootRPM = 1750; // 1800
    public static double closeShootAutoRPM = 1700;
    public static double farShootRPM = 2200; //2.16k
    public static double kf = 0.3, kp = 0.2, ki = 0, kd = 0.000002;
    public static double tuningTestingRPM = 0;
    public static int TICKS_PER_REV = 28;
    public static int MAX_RPM = 6000;

}
