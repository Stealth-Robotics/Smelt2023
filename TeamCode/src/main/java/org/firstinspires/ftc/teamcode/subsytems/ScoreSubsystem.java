package org.firstinspires.ftc.teamcode.subsytems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ScoreSubsystem extends SubsystemBase {
    Servo wristServo;
    Servo releaseServo;

    public ScoreSubsystem(HardwareMap hardwareMap) {
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        releaseServo = hardwareMap.get(Servo.class, "releaseServo");
    }

    public void setWristPosition(double position) {
        wristServo.setPosition(position);
    }

    public void setReleasePosition(double position) {
        releaseServo.setPosition(position);
    }


}
