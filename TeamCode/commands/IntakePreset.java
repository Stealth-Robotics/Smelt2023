package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;
import org.firstinspires.ftc.teamcode.subsytems.ExtenderSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.ScoreSubsystem;

public class IntakePreset extends SequentialCommandGroup {

    public IntakePreset(ArmSubSystem armSubSystem, ExtenderSubsystem extenderSubsystem, ScoreSubsystem scoreSubsystem){
        addCommands(
                new ZeroExtenderCommand(extenderSubsystem),
                new MoveWristUpCommand(scoreSubsystem),
                new InstantCommand(scoreSubsystem::openNubbinAllTheWay),
                new WaitCommand(500),
                new ZeroArmCommand(armSubSystem),
                new MoveWristDownCommand(scoreSubsystem)
        );
    }

}
