//package org.firstinspires.ftc.teamcode;
//
//import androidx.annotation.NonNull;
//
//import com.arcrobotics.ftclib.hardware.motors.CRServo;
//import com.arcrobotics.ftclib.hardware.motors.Motor;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//
//import java.util.ArrayList;
//
//@TeleOp(name = "Sample Detection OpMode")
//public class SampleDetectionOpMode extends LinearOpMode {
//
//    OpenCvCamera camera;
//    SampleDetectionPipelinePNP pipeline;
//
//    @Override
//    public void runOpMode() {
//        // Initialize OpenCV camera
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "12E453FF", hardwareMap.appContext.getPackageName());
//        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//
//        // Initialize pipeline
//        pipeline = new SampleDetectionPipelinePNP();
//        camera.setPipeline(pipeline);
//
//        // Start the camera
//        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override
//            public void onOpened() {
//                camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            @Override
//            public void onError(int errorCode) {
//                telemetry.addData("Camera Error", "Error code: %d", errorCode);
//                telemetry.update();
//            }
//        });
//
//        DriveBase driveBase = getDriveBase();
//
//        // Wait for the start command
//        waitForStart();
//
//        while (opModeIsActive()) {
//            // Get detected stones from the pipeline
//            ArrayList<SampleDetectionPipelinePNP.AnalyzedStone> detectedStones = pipeline.getDetectedStones();
//
//            // Process detected stones (e.g., print information to telemetry)
//            for (SampleDetectionPipelinePNP.AnalyzedStone stone : detectedStones) {
//                double[] translationVector = getTranslationVector(stone);
//                if (translationVector[0] < 0) {
//                    telemetry.addData("Stone", "Left");
//                } else if (translationVector[0] > 0) {
//                    telemetry.addData("Stone", "Right");
//                } else {
//                    telemetry.addData("Stone", "Center");
//                }
//            }
//
//            if (!detectedStones.isEmpty()) {
//                SampleDetectionPipelinePNP.AnalyzedStone stone = detectedStones.get(0);
//                //driveBase.driveRotateTo(getTranslationVector(stone)[0], gamepad1, telemetry);
//            }
//
//            telemetry.update();
//        }
//
//        // Stop the camera when done
//        camera.stopStreaming();
//    }
//
//    private double[] getTranslationVector(SampleDetectionPipelinePNP.AnalyzedStone stone) {
//        return new double[]{stone.tvec.get(0, 0)[0], stone.tvec.get(1, 0)[0], stone.tvec.get(2, 0)[0]};
//    }
//
//    private @NonNull DriveBase getDriveBase() {
//        Motor frontLeft = new Motor(hardwareMap, "back-left");
//        Motor frontRight = new Motor(hardwareMap, "back-right");
//        CRServo backLeft = new CRServo(hardwareMap, "front-left");
//        CRServo backRight = new CRServo(hardwareMap, "front-right");
//
//        ArrayList<Motor> driveMotors = new ArrayList<Motor>();
//        driveMotors.add(frontLeft);
//        driveMotors.add(frontRight);
//        driveMotors.add(backLeft);
//        driveMotors.add(backRight);
//
//        DriveBase driveBase = new DriveBase(driveMotors, 1);
//        return driveBase;
//    }
//}
