// Product interface: Character
interface Character {
    void assemble();
}

// Concrete Product 1: Robot
class Robot implements Character {
    @Override
    public void assemble() {
        System.out.println("Robot is assembled.");
    }
}

// Concrete Product 2: Soldier
class Soldier implements Character {
    @Override
    public void assemble() {
        System.out.println("Soldier is assembled.");
    }
}

// Factory class: CharacterFactory
class CharacterFactory {
    public static Character getCharacter(String type) {
        if ("Robot".equalsIgnoreCase(type)) {
            return new Robot();
        } else if ("Soldier".equalsIgnoreCase(type)) {
            return new Soldier();
        }
        return null;
    }
}

// Main to test
public class Factory {
    public static void main(String[] args) {
        Character robot = CharacterFactory.getCharacter("Robot");
        robot.assemble();

        Character soldier = CharacterFactory.getCharacter("Soldier");
        soldier.assemble();
    }
}
