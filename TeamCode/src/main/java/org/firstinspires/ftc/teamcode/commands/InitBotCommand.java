package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsytems.ScoreSubsystem;

public class InitBotCommand extends SequentialCommandGroup {
    public InitBotCommand(ScoreSubsystem scoreSubsystem) {
        addRequirements(scoreSubsystem);

        // TODO: right now this only adjusts the wrist.
        //  - later, you want to:
        //    - move the wrist up
        //    - close the nubbin
        //    - retract the extender until it hits the upper limit switch
        //    - move the arm down until it contacts the lower limit switch
        //    - move the wrist down
        addCommands(
                new MoveWristDownCommand(scoreSubsystem),
                new InstantCommand(() -> scoreSubsystem.openNubbinAllTheWay()),
                new WaitCommand(1000) // Give the servos a second to settle
        );
    }
}
