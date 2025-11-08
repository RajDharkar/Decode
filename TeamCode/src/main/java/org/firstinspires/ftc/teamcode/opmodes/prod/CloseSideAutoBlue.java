package org.firstinspires.ftc.teamcode.opmodes.prod;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandGroupBase;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.util.DashboardPoseTracker;
import com.pedropathing.util.Drawing;
import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.AutoConstants;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.commands.botcommands.FollowPathCommand;
import org.firstinspires.ftc.teamcode.robot.commands.subsystemcommands.BlockerCommand;
import org.firstinspires.ftc.teamcode.robot.commands.subsystemcommands.IntakeCommand;
import org.firstinspires.ftc.teamcode.robot.commands.subsystemcommands.KickerCommand;
import org.firstinspires.ftc.teamcode.robot.commands.subsystemcommands.ShooterCommand;
import org.firstinspires.ftc.teamcode.robot.commands.subsystemcommands.TurretCommand;
import org.firstinspires.ftc.teamcode.robot.subsystems.Blocker;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.Kicker;
import org.firstinspires.ftc.teamcode.robot.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;
import org.firstinspires.ftc.teamcode.utils.MyTelem;

@Autonomous
@Config
public class CloseSideAutoBlue extends LinearOpMode {
    public ElapsedTime timer;
    private DashboardPoseTracker dashboardPoseTracker;
    Robot robot;

    private CloseSideAutoPaths paths;

    @Override
    public void runOpMode(){
        timer = new ElapsedTime();
        timer.reset();
        MyTelem.init(telemetry);
        robot = new Robot(hardwareMap, true);

        CommandGroupBase shootThree = new SequentialCommandGroup(
                new KickerCommand(robot, Kicker.KickerState.ON),
                new WaitCommand(3500),
                new ShooterCommand(robot, Shooter.ShooterState.STOP),
                new KickerCommand(robot, Kicker.KickerState.OFF)
        );

        paths = new CloseSideAutoPaths(robot.follower);
        SequentialCommandGroup auto = new SequentialCommandGroup(

            new ParallelCommandGroup(
                    new FollowPathCommand(robot.follower, paths.Path1),
                    new BlockerCommand(robot, Blocker.BlockerState.UNBLOCKED),
                    new IntakeCommand(robot, Intake.IntakeState.ON),
                    new ShooterCommand(robot, Shooter.ShooterState.CLOSE),
                    new TurretCommand(robot, Turret.TurretState.FRONT)
            ),
            shootThree,
            new ParallelCommandGroup(
                    new BlockerCommand(robot, Blocker.BlockerState.BLOCKED),
                    new FollowPathCommand(robot.follower, paths.Path2)
            ),
            new WaitCommand(300),
            new ParallelCommandGroup(
                    new FollowPathCommand(robot.follower, paths.Path3),
                    new SequentialCommandGroup(
                            new WaitCommand(500),
                            new ShooterCommand(robot, Shooter.ShooterState.CLOSE),
                            new WaitCommand(1000),
                            new BlockerCommand(robot, Blocker.BlockerState.UNBLOCKED)

                    )
            ),
            shootThree,
                new ParallelCommandGroup(
                        new BlockerCommand(robot, Blocker.BlockerState.BLOCKED),
                        new FollowPathCommand(robot.follower, paths.Path4)
                ),
            new WaitCommand(300),
            new ParallelCommandGroup(
                new FollowPathCommand(robot.follower, paths.Path5),
                new SequentialCommandGroup(
                        new WaitCommand(500),
                        new ShooterCommand(robot, Shooter.ShooterState.CLOSE),
                        new WaitCommand(1000),
                        new BlockerCommand(robot, Blocker.BlockerState.UNBLOCKED)
                )
            ),
            shootThree,
                new ParallelCommandGroup(
                        new BlockerCommand(robot, Blocker.BlockerState.BLOCKED),
                        new FollowPathCommand(robot.follower, paths.Path6)
                ),
            new WaitCommand(300),
            new ParallelCommandGroup(
                    new FollowPathCommand(robot.follower, paths.Path7),
                    new SequentialCommandGroup(
                            new WaitCommand(500),
                            new ShooterCommand(robot, Shooter.ShooterState.CLOSE),
                            new WaitCommand(1000),
                            new BlockerCommand(robot, Blocker.BlockerState.UNBLOCKED)
                    )
            ),
            shootThree
        );

        dashboardPoseTracker = new DashboardPoseTracker(robot.follower.poseUpdater);
        Drawing.drawRobot(robot.follower.poseUpdater.getPose(), "#4CAF50");
        Drawing.sendPacket();

        waitForStart();

        CommandScheduler.getInstance().schedule(auto);

        while (!isStopRequested() && opModeIsActive()) {
            robot.update();
            MyTelem.addData("POSE", robot.follower.getPose());
            MyTelem.addData("TIMER", timer.seconds());
            MyTelem.update();
            dashboardPoseTracker.update();
            Drawing.drawPoseHistory(dashboardPoseTracker, "#4CAF50");
            Drawing.drawRobot(robot.follower.poseUpdater.getPose(), "#4CAF50");
            Drawing.sendPacket();
        }
        robot.stop();
    }

    public static class CloseSideAutoPaths {
        public PathChain Path1;
        public PathChain Path2;
        public PathChain Path3;
        public PathChain Path4;
        public PathChain Path5;
        public PathChain Path6;
        public PathChain Path7;

        public CloseSideAutoPaths(Follower follower) {
            follower.setStartingPose(new Pose(23.9, 123.400, Math.toRadians(143)));
            Path1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(19.250, 123.000), new Pose(60, 84))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(143), Math.toRadians(AutoConstants.shootingAngle))
                    .build();

            Path2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(60, 84.000),
                                    new Pose(56.86, 78.80),
                                    new Pose(16.000, 84.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(AutoConstants.shootingAngle), Math.toRadians(180))
                    .build();

            Path3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(16.00, 84.000), new Pose(60, 84))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(AutoConstants.shootingAngle))
                    .build();

            Path4 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(60, 84),
                                    new Pose(66, 52),
                                    new Pose(14.900, 60.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(AutoConstants.shootingAngle), Math.toRadians(180))
                    .build();

            Path5 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(14.900, 60.000), new Pose(60, 84))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(AutoConstants.shootingAngle))
                    .build();

            Path6 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(60, 84),
                                    new Pose(87, 31),
                                    new Pose(15.000, 36.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(AutoConstants.shootingAngle), Math.toRadians(180))
                    .build();

            Path7 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(15.000, 36.000), new Pose(60, 84))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(AutoConstants.shootingAngle))
                    .build();
        }
    }
}


