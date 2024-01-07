package org.firstinspires.ftc.teamcode.subsytems;

import android.util.Size;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;

public class CameraSubsystem extends SubsystemBase {

    private VisionPortal portal;
    private PropProcessor processor;

    private PropProcessor.PropPosition result;

    private Telemetry telemetry;

    public CameraSubsystem(HardwareMap hardwaremap, Alliance alliance, Telemetry telemetry) {
        this.result = PropProcessor.PropPosition.CENTER;
        this.processor = new PropProcessor(alliance);
        portal = new VisionPortal.Builder()
                .setCamera(hardwaremap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .setCamera(BuiltinCameraDirection.BACK)
                .addProcessor(processor)
                .build();

        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        result = processor.getPropPosition();
<<<<<<< HEAD
        getResult();
    }

    public PropProcessor.PropPosition getResult() {

=======

        telemetry.addData("CameraPosition",getResult());
        telemetry.update();
    }

    public PropProcessor.PropPosition getResult() {
>>>>>>> b7d55e1c0b5454384464ad032881dc215d022ab6
        return result;
    }
}
