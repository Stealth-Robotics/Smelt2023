package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DefaultArmCommand;
import org.firstinspires.ftc.teamcode.commands.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.commands.DefaultExtenderCommand;
import org.firstinspires.ftc.teamcode.commands.DefaultIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.HorizontalPreset;
import org.firstinspires.ftc.teamcode.commands.InitBotCommand;
import org.firstinspires.ftc.teamcode.commands.MoveWristDownCommand;
import org.firstinspires.ftc.teamcode.commands.MoveWristUpCommand;
import org.firstinspires.ftc.teamcode.commands.IntakePreset;
import org.firstinspires.ftc.teamcode.commands.PlaneLaunchCommand;
import org.firstinspires.ftc.teamcode.commands.ScorePreset;
import org.firstinspires.ftc.teamcode.commands.VerticalPreset;
import org.firstinspires.ftc.teamcode.subsytems.Alliance;
import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;
import org.firstinspires.ftc.teamcode.subsytems.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.ExtenderSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.LauchyGuySubsystem;
import org.firstinspires.ftc.teamcode.subsytems.ScoreSubsystem;

@TeleOp(name = "Camera")
public class CameraTest extends CommandOpMode {


    public CameraSubsystem cameraSubsystem;

    @Override
    public void initialize() {


        cameraSubsystem = new CameraSubsystem(hardwareMap, Alliance.BLUE, telemetry);

        cameraSubsystem = new CameraSubsystem(hardwareMap, Alliance.RED, telemetry);


    }
}