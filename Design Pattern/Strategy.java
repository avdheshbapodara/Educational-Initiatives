// Strategy interface 
interface AttackStrategy {
    void executeAttack();
}

// Concrete strategy 1: Melee Attack
class MeleeAttack implements AttackStrategy {
    @Override
    public void executeAttack() {
        System.out.println("Using melee attack with close combat skills!");
    }
}

// Concrete strategy 2: Ranged Attack
class RangedAttack implements AttackStrategy {
    @Override
    public void executeAttack() {
        System.out.println("Using ranged attack with long-range weapons!");
    }
}

// Context class: Character
class Character {
    private String name;
    private AttackStrategy attackStrategy;

    public Character(String name, AttackStrategy attackStrategy) {
        this.name = name;
        this.attackStrategy = attackStrategy;
    }

    public void changeAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public void attack() {
        System.out.println(name + " is attacking:");
        attackStrategy.executeAttack();
    }
}

// Main to test
public class Strategy {
    public static void main(String[] args) {
        Character warrior = new Character("Warrior", new MeleeAttack());
        Character archer = new Character("Archer", new RangedAttack());

        warrior.attack();
        archer.attack();

        // Changing strategy during battle
        archer.changeAttackStrategy(new MeleeAttack());
        archer.attack();
    }
}
