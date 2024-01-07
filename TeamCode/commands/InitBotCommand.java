package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsytems.ArmSubSystem;
import org.firstinspires.ftc.teamcode.subsytems.ExtenderSubsystem;
import org.firstinspires.ftc.teamcode.subsytems.LauchyGuySubsystem;
import org.firstinspires.ftc.teamcode.subsytems.ScoreSubsystem;

public class InitBotCommand extends SequentialCommandGroup {
    public InitBotCommand(ScoreSubsystem scoreSubsystem, ExtenderSubsystem extenderSubsystem, ArmSubSystem armSubSystem) {
        addRequirements(scoreSubsystem, extenderSubsystem, armSubSystem);

        // TODO: right now this only adjusts the wrist.
        //  - later, you want to:
        //    - move the wrist up
        //    - close the nubbin
        //    - retract the extender until it hits the upper limit switch
        //    - move the arm down until it contacts the lower limit switch
        //    - move the wrist down
        addCommands(
                new MoveWristUpCommand(scoreSubsystem),
                new InstantCommand(scoreSubsystem::openNubbinAllTheWay),
                new WaitCommand(1000), // Give the servos a second to settle
                new ZeroExtenderCommand(extenderSubsystem),
                new ZeroArmCommand(armSubSystem),
                new MoveWristDownCommand(scoreSubsystem)


        );
    }
}
