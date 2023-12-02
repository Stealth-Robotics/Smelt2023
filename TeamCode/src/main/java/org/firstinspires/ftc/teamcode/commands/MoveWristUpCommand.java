package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsytems.ScoreSubsystem;

public class MoveWristUpCommand extends SequentialCommandGroup {
    public MoveWristUpCommand(ScoreSubsystem scoreSubsystem) {
        addRequirements(scoreSubsystem);

        addCommands(
                new InstantCommand(() -> scoreSubsystem.setWristUp()),
                new WaitCommand(500)
        );
    }
}
