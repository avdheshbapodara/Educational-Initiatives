// Electric attack interface
interface ElectricAttack {
    void dischargeElectricity();
}

// Laser attack interface
interface LaserAttack {
    void fireLaser();
}

// Robot class
class Robot implements ElectricAttack {
    public void dischargeElectricity() {
        System.out.println("Robot discharges electricity!");
    }
}

// Drone class
class Drone implements LaserAttack {
    public void fireLaser() {
        System.out.println("Drone fires laser!");
    }
}

// Adapter that makes Drone's laser work like an electric attack
class DroneAdapter implements ElectricAttack {
    private Drone drone;

    public DroneAdapter(Drone drone) {
        this.drone = drone;
    }

    @Override
    public void dischargeElectricity() {
        drone.fireLaser();
    }
}

// Battle sequence
public class Adapter {
    public static void main(String[] args) {
        Robot robot = new Robot();
        Drone drone = new Drone();
        ElectricAttack droneWithElectricity = new DroneAdapter(drone);

        // Robot and Drone attack together using electricity-like attacks
        robot.dischargeElectricity();
        droneWithElectricity.dischargeElectricity(); // Drone's laser is adapted as electricity
    }
}
