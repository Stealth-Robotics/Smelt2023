package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.DefaultIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.autos.BlueCloseAutoCommand;
import org.firstinspires.ftc.teamcode.subsytems.Alliance;
import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;
import org.firstinspires.ftc.teamcode.subsytems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.ExtenderSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.LauchyGuySubsystem;
import org.firstinspires.ftc.teamcode.subsytems.PropProcessor;
import org.firstinspires.ftc.teamcode.subsytems.ScoreSubsystem;

@Autonomous(name = "Blue Close Auto")
public class BlueCloseAuto extends LinearOpMode {
    DriveSubsystem driveSubsystem;
    ArmSubSystem armSubSystem;
    ExtenderSubsystem extenderSubsystem;
    IntakeSubsystem intakeSubsystem;
    LauchyGuySubsystem lauchyGuySubsystem;
    ScoreSubsystem scoreSubsystem;
    CameraSubsystem cameraSubsystem;

    PropProcessor.PropPosition position;



    @Override
    public void runOpMode() throws InterruptedException {

        driveSubsystem = new DriveSubsystem(hardwareMap, telemetry);
        armSubSystem = new ArmSubSystem(hardwareMap, telemetry);
        extenderSubsystem = new ExtenderSubsystem(hardwareMap, telemetry);
        intakeSubsystem = new IntakeSubsystem(hardwareMap, telemetry);
        scoreSubsystem = new ScoreSubsystem(hardwareMap);
        cameraSubsystem = new CameraSubsystem(hardwareMap, Alliance.BLUE, telemetry);

        register(
                driveSubsystem,
                armSubSystem,
                extenderSubsystem,
                intakeSubsystem,
                scoreSubsystem,
                cameraSubsystem
        );


        // init mode

        while (!isStarted() && !isStopRequested()){

            CommandScheduler.getInstance().run();
            //position = this.cameraSubsystem.getResult();

            telemetry.addData("CameraPosition", position);
            telemetry.update();
        }

        while (!isStarted()){ }

        //intakeSubsystem.setPower(-1);




        schedule(new BlueCloseAutoCommand(
                driveSubsystem,
                armSubSystem,
                extenderSubsystem,
                intakeSubsystem,
                scoreSubsystem,
                position
        ));

        while (!isStopRequested() && opModeIsActive())
        {
            CommandScheduler.getInstance().run();
        }

        CommandScheduler.getInstance().reset();
    }

    public void register(Subsystem... subsystems)
    {
        CommandScheduler.getInstance().registerSubsystem(subsystems);
    }

    public void schedule(Command... commands)
    {
        CommandScheduler.getInstance().schedule(commands);
    }
}
