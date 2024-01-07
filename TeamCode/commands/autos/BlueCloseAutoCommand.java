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
import org.firstinspires.ftc.teamcode.subsytems.PropProcessor;
import org.firstinspires.ftc.teamcode.subsytems.ScoreSubsystem;

public class BlueCloseAutoCommand extends SequentialCommandGroup {
    public BlueCloseAutoCommand(DriveSubsystem driveSubsystem,
                                ArmSubSystem armSubSystem,
                                ExtenderSubsystem extenderSubsystem,
                                IntakeSubsystem intakeSubsystem,
                                ScoreSubsystem scoreSubsystems,
                                PropProcessor.PropPosition position)
    {
//<<<<<<< HEAD
        if(position== PropProcessor.PropPosition.RIGHT) {
            addCommands(
                    new DriveForTicksCommand(driveSubsystem, -2000),
                    new TurnWithGyro(driveSubsystem, 45),
                    new DriveForTicksCommand(driveSubsystem, -2000),
                    new DrivetIntake(intakeSubsystem, .5));
        }

        if(position== PropProcessor.PropPosition.CENTER) {
            addCommands(
                    new DriveForTicksCommand(driveSubsystem, -2000),
                    new DriveForTicksCommand(driveSubsystem, -2000),
                    new DrivetIntake(intakeSubsystem, .5));
        }

        if(position== PropProcessor.PropPosition.LEFT){
            addCommands(
                    new DriveForTicksCommand(driveSubsystem, -2000),
                    new TurnWithGyro(driveSubsystem,-45),
                    new DriveForTicksCommand(driveSubsystem, -2000),
                    new DrivetIntake(intakeSubsystem,.5));

        }


//=======
        addCommands(
                new DriveForTicksCommand(driveSubsystem, -2000),
                new TurnWithGyro(driveSubsystem, 90),
                new DrivetIntake(intakeSubsystem,.5),
                new DriveForTicksCommand(driveSubsystem, -4000)
        );
//>>>>>>> b7d55e1c0b5454384464ad032881dc215d022ab6
    }
}
