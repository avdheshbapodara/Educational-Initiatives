import java.util.List;
import java.util.ArrayList;

// Character interface
interface Character {
    void attack();
}

// Individual character: Soldier
class Soldier implements Character {
    @Override
    public void attack() {
        System.out.println("Soldier attacks with a rifle!");
    }
}

// Individual character: Robot
class Robot implements Character {
    @Override
    public void attack() {
        System.out.println("Robot fires lasers!");
    }
}

// Individual character: Spy
class Spy implements Character {
    @Override
    public void attack() {
        System.out.println("Spy attacks with stealth and gadgets!");
    }
}

// Composite class: Team
class Team implements Character {
    private List<Character> members = new ArrayList<>();

    public void addMember(Character member) {
        members.add(member);
    }

    public void removeMember(Character member) {
        members.remove(member);
    }

    @Override
    public void attack() {
        System.out.println("The team is attacking as a unit!");
        for (Character member : members) {
            member.attack();
        }
    }
}

// Fight sequence
public class Composite {
    public static void main(String[] args) {
        // Individual characters
        Soldier soldier = new Soldier();
        Robot robot = new Robot();
        Spy spy = new Spy();

        // Team (composite)
        Team team = new Team();
        team.addMember(soldier);
        team.addMember(robot);
        team.addMember(spy);

        // Individual attacks
        soldier.attack();
        robot.attack();
        spy.attack();

        // Team attack
        team.attack(); // All characters attack together
    }
}
