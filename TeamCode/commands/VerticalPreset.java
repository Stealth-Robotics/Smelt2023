package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;
import org.firstinspires.ftc.teamcode.subsytems.ScoreSubsystem;

public class VerticalPreset extends SequentialCommandGroup {
    public VerticalPreset(ScoreSubsystem scoreSubsystem, ArmSubSystem armSubSystem)
    {
        addCommands(
                new InstantCommand(scoreSubsystem::closeNubbin),
                new WaitCommand(250),
                new MoveWristUpCommand(scoreSubsystem),
                new WaitCommand(1000),
                new MoveArmToSetPoint(ArmSubSystem.ArmPosition.UP.getValue(), armSubSystem)
        );
    }
}
