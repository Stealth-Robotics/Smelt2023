package org.firstinspires.ftc.teamcode.commands.autos;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.DefaultIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.DriveForTicksCommand;
import org.firstinspires.ftc.teamcode.commands.DrivetIntake;
import org.firstinspires.ftc.teamcode.commands.TurnWithGyro;
import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;
import org.firstinspires.ftc.teamcode.subsytems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.ExtenderSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.ScoreSubsystem;

public class BlueCloseAutoCommand extends SequentialCommandGroup {
    public BlueCloseAutoCommand(DriveSubsystem driveSubsystem, ArmSubSystem armSubSystem, ExtenderSubsystem extenderSubsystem, IntakeSubsystem intakeSubsystem, ScoreSubsystem scoreSubsystems)
    {
        addCommands(
                new DriveForTicksCommand(driveSubsystem, -2000),
                new TurnWithGyro(driveSubsystem, 90),
                new DrivetIntake(intakeSubsystem,.5),
                new DriveForTicksCommand(driveSubsystem, -4000)
        );
    }
}
