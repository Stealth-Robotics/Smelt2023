package org.firstinspires.ftc.teamcode.subsytems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ScoreSubsystem extends SubsystemBase {
    final double UP_POSITION = 0.6;
    final double DOWN_POSITION = 0.91;

    boolean isWristUp = true; // Assume the worst!!
    Servo wristServo;
    Servo releaseServo;

    public ScoreSubsystem(HardwareMap hardwareMap) {
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        releaseServo = hardwareMap.get(Servo.class, "releaseServo");
    }


    public void setReleasePosition(double position) {
        releaseServo.setPosition(position);
    }

    public void setWristUp() {
        wristServo.setPosition(UP_POSITION);
    }

    public void setWristDown() {
        wristServo.setPosition(DOWN_POSITION);
    }

    public void wristIsReallyUpNow() {
        isWristUp = true;
    } public void wristIsReallyDownNow() {
        isWristUp = false;
    }

    public boolean isWristUp() {
        return isWristUp;
    }
}
