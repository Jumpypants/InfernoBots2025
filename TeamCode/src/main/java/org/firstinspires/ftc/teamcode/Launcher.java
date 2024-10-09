package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Launcher {
    CRServo launchServo;

    double servoPower;
    public Launcher (CRServo launchServo, double servoPower) {
        this.launchServo = launchServo;
        this.servoPower = servoPower;
    }

    public void drive(Gamepad gamepad2) {
        if (gamepad2.x) {
            launchServo.set(servoPower);
        }
    }
}
