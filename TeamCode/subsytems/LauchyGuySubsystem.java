package org.firstinspires.ftc.teamcode.subsytems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class LauchyGuySubsystem extends SubsystemBase {


    final double FLY_FLY_POSITION = 0.4;



    Servo launchyServo;

    public LauchyGuySubsystem(HardwareMap hardwareMap) {
        launchyServo = hardwareMap.get(Servo.class, "planeServo");
    }



    public void setPower(double power) {
        launchyServo.setPosition(FLY_FLY_POSITION);
    }

}
