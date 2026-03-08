package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;

public class VisionInterface {

    private SerialPort serial;

    // Vision values
    private double strafeError = 0.0;
    private double rotateError = 0.0;   // degrees
    private double distanceError = 0.0;
    private int tagID = -1;
    private int latency = 0;

    // Camera temperature
    private double chipTemperature = 0.0;

    public VisionInterface() {

        try {
            serial = new SerialPort(115200, SerialPort.Port.kUSB);
            serial.setTimeout(0.02);
            serial.enableTermination('\n');
        } catch (Exception e) {
            System.out.println("Vision serial failed to initialize");
        }
    }

    public void update() {

        
        if (serial == null) return;

        try {

            String data = serial.readString();
            if (data == null || data.isEmpty()) return;

            System.out.println(data);

            String[] lines = data.split("\n");

            for (String line : lines) {

                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length == 0) continue;

                switch (parts[0]) {

                    case "ALIGN":

                        if (parts.length >= 6) {

                            tagID = Integer.parseInt(parts[1]);
                            strafeError = Double.parseDouble(parts[2]);
                            rotateError = Double.parseDouble(parts[3]);
                            distanceError = Double.parseDouble(parts[4]);
                            latency = Integer.parseInt(parts[5]);

                        }

                        break;

                    case "CHIP_TEMP":

                        if (parts.length >= 2) {
                            chipTemperature = Double.parseDouble(parts[1]);
                        }

                        break;

                    default:
                        break;
                }
            }

        } catch (Exception e) {
            // ignore bad packets
        }
    }

    // ----------------------------
    // Helper functions
    // ----------------------------

    public boolean hasTarget() {
        return tagID != -1;
    }

    public double getStrafeError() {
        return strafeError;
    }

    public double getRotateError() {
        return rotateError;
    }

    public double getDistanceError() {
        return distanceError;
    }

    public int getTagID() {
        return tagID;
    }

    public int getLatency() {
        return latency;
    }

    public double getChipTemperature() {
        return chipTemperature;
    }
}