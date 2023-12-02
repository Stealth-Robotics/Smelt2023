package org.firstinspires.ftc.teamcode.subsytems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ExtenderSubsystem extends SubsystemBase {

    private final Servo extenderServo;

    private final DigitalChannel limitSwitch;
    private final DigitalChannel limitSwitch1;

    Telemetry telemetry;

    public ExtenderSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        extenderServo = hardwareMap.get(Servo.class, "extenderServo");
        limitSwitch = hardwareMap.get(DigitalChannel.class, "extenderLimitSwitch");
        limitSwitch.setMode(DigitalChannel.Mode.INPUT);

        limitSwitch1 = hardwareMap.get(DigitalChannel.class, "extenderLimitSwitch1");
        limitSwitch1.setMode(DigitalChannel.Mode.INPUT);
        this.telemetry = telemetry;
    }

    public void setPosition(double position) {
        extenderServo.setPosition(position);
    }

    public double getPosition() {
        return extenderServo.getPosition();
    }

    public boolean getLimitSwitch()
    {
        return limitSwitch.getState();
    }

    public boolean getLimitSwitchAlive()
    {
        return limitSwitch.getState() != limitSwitch1.getState();
    }

    @Override
    public void periodic() {

        telemetry.addData("ExtenderLimitSwitch", limitSwitch.getState());
        telemetry.addData("ExtenderLimitSwitch1", limitSwitch1.getState());
        telemetry.update();

    }

}
