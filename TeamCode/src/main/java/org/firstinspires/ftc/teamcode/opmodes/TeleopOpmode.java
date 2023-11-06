package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DefaultArmCommand;
import org.firstinspires.ftc.teamcode.commands.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.commands.DefaultIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.InitBotCommand;
import org.firstinspires.ftc.teamcode.commands.MoveWristDownCommand;
import org.firstinspires.ftc.teamcode.commands.MoveWristUpCommand;
import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;
import org.firstinspires.ftc.teamcode.subsytems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.ExtenderSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.LauchyGuySubsystem;
import org.firstinspires.ftc.teamcode.subsytems.ScoreSubsystem;

@TeleOp(name = "TeleOp")
public class TeleopOpmode extends CommandOpMode {

    DriveSubsystem driveSubsystem;
    ArmSubSystem armSubSystem;
    ExtenderSubsystem extenderSubsystem;
    ScoreSubsystem scoreSubsystem;
    IntakeSubsystem intakeSubsystem;
    LauchyGuySubsystem launchyGuySubsystem;

    GamepadEx driveGamepad;

    GamepadEx mechanismGamepad;

    @Override
    public void initialize() {

        driveGamepad = new GamepadEx(gamepad1);
        mechanismGamepad = new GamepadEx(gamepad2);

        driveSubsystem = new DriveSubsystem(hardwareMap, telemetry);
        armSubSystem = new ArmSubSystem(hardwareMap, telemetry);
        extenderSubsystem = new ExtenderSubsystem(hardwareMap, telemetry);
        scoreSubsystem = new ScoreSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap, telemetry);
        launchyGuySubsystem = new LauchyGuySubsystem(hardwareMap);

        register(driveSubsystem, armSubSystem, extenderSubsystem, scoreSubsystem, intakeSubsystem, launchyGuySubsystem);

        driveSubsystem.setDefaultCommand(
                new DefaultDriveCommand(
                        driveSubsystem,
                        () -> driveGamepad.getLeftX(),
                        () -> driveGamepad.getLeftY(),
                        () -> driveGamepad.getRightX(),
                        () -> driveGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).get()));

        armSubSystem.setDefaultCommand(new DefaultArmCommand(armSubSystem, () -> mechanismGamepad.getLeftY(), () -> scoreSubsystem.isWristUp()));

        intakeSubsystem.setDefaultCommand(new DefaultIntakeCommand(intakeSubsystem, () -> mechanismGamepad.getRightY()));

        // Not ready yet!!!
//        extenderSubsystem.setDefaultCommand(new DefaultExtenderCommand(extenderSubsystem, () -> mechanismGamepad.getRightY()));

        driveGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new InstantCommand(() -> driveSubsystem.resetAngle()));

        driveGamepad.getGamepadButton(GamepadKeys.Button.B).whenPressed(new MoveWristUpCommand(scoreSubsystem));
        driveGamepad.getGamepadButton(GamepadKeys.Button.A).whenPressed(new MoveWristDownCommand(scoreSubsystem));

        driveGamepad.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(new InstantCommand(() -> scoreSubsystem.closeNubbin()));
        driveGamepad.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(new InstantCommand(() -> scoreSubsystem.openNubbinForOne()));
        driveGamepad.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(new InstantCommand(() -> scoreSubsystem.openNubbinAllTheWay()));

        mechanismGamepad.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(new InstantCommand(() -> launchyGuySubsystem.flyBeFree()));

        // Schedule the command to reset the bot to its initial position.
        schedule(new InitBotCommand(scoreSubsystem));
    }
}