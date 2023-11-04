package org.firstinspires.ftc.teamcode.subsytems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ScoreSubsystem extends SubsystemBase {
    final double UP_POSITION = 0.6;
    final double DOWN_POSITION = 0.91;

    final double NUBBIN_CLOSED = 0.27;
    final double NUBBIN_OPEN_FOR_ONE = 0.39;
    final double NUBBIN_OPEN_ALL_THE_WAY = 0.45;


    boolean isWristUp = false; // Assume the worst!!
    Servo wristServo;
    Servo releaseServo;

    public ScoreSubsystem(HardwareMap hardwareMap) {
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        releaseServo = hardwareMap.get(Servo.class, "releaseServo");
    }


    public void setWristUp() {
        wristServo.setPosition(UP_POSITION);
    }

    public void setWristDown() {
        wristServo.setPosition(DOWN_POSITION);
    }

    public void wristIsReallyUpNow() {
        isWristUp = true;
    }

    public void wristIsReallyDownNow() {
        isWristUp = false;
    }

    public boolean isWristUp() {
        return isWristUp;
    }

    public void closeNubbin() {
        releaseServo.setPosition(NUBBIN_CLOSED);
    }

    public void openNubbinForOne() {
        releaseServo.setPosition(NUBBIN_OPEN_FOR_ONE);
    }

    public void openNubbinAllTheWay() {
        releaseServo.setPosition(NUBBIN_OPEN_ALL_THE_WAY);

    }

}
