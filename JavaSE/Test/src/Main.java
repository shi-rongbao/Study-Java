import java.util.Random;

public class Main {
    static class Role {
        private String name;
        private int level;
        private int hp;
        private int str;
        private int exp;

        private int maxHp;

        private int maxExp;

        private char gender;

        private boolean weapon;

        private int money;

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public int getHp() {
            return hp;
        }

        public int getMaxHp() {
            return maxHp;
        }

        public int getStr() {
            return str;
        }

        public int getExp() {
            return exp;
        }

        public int getMaxExp() {
            return maxExp;
        }

        public Role() {

        }

        public Role(String name, int level, int hp, int str, int exp, int maxHp, int maxExp, char gender, boolean weapon, int money) {
            this.name = name;
            this.level = level;
            this.hp = hp;
            this.str = str;
            this.exp = exp;
            this.maxHp = maxHp;
            this.maxExp = maxExp;
            this.gender = gender;
            this.weapon = weapon;
            this.money = money;
        }

        @Override
        public String toString() {
            return this.getName() + "- Level: " + this.getLevel() + "\t HP:" + this.getHp() + "/" + this.getMaxHp() + "\t Str:" + this.getStr() + "\t Exp:" + this.getExp() + "/" + this.getMaxExp();
        }

        public void buy() {
            this.weapon = true;
            this.money -= 1000;
        }
    }

    static class Player extends Role {
        Player(String name, int level, int hp, int str, int exp, int maxHp, int maxExp, char gender, boolean weapon, int money) {
            super(name, level, hp, str, exp, maxHp, maxExp, gender, weapon, money);
        }
    }

    static class Goblin extends Role {
        Goblin(String name, int level, int hp, int str, int exp, int maxHp, int maxExp, char gender, boolean weapon, int money) {
            super(name, level, hp, str, exp, maxHp, maxExp, gender, weapon, money);
        }
    }

    static class Ogre extends Role {
        Ogre(String name, int level, int hp, int str, int exp, int maxHp, int maxExp, char gender, boolean weapon, int money) {
            super(name, level, hp, str, exp, maxHp, maxExp, gender, weapon, money);
        }
    }

    static class Archer extends Role {
        Archer(String name, int level, int hp, int str, int exp, int maxHp, int maxExp, char gender, boolean weapon, int money) {
            super(name, level, hp, str, exp, maxHp, maxExp, gender, weapon, money);
        }
    }


    public static void main(String[] args) {
        Role player = new Player("Player", 1, 300, 1, 0, 300, 10, 'b', false, 1000);  // b  -> boy
        Role goblin = new Goblin("Goblin", 1, 20, 1, 0, 20, 10, 'g', false, 1000);  // g  -> girl
        Role ogre = new Ogre("Ogre", 1, 30, 3, 0, 30, 10, 'b', false, 1000);
        Role archer = new Archer("Archer", 1, 30, 3, 0, 30, 10, 'g', false, 1000);

        System.out.println("welcome to weapon store!");
        System.out.println("==========================");

        buyWeapon(player);
        buyWeapon(goblin);
        buyWeapon(ogre);
        buyWeapon(archer);

        System.out.println();
        System.out.println("Game Begins");
        System.out.println("=============");
        showInfo(player);
        showInfo(goblin);
        showInfo(ogre);
        showInfo(archer);

        System.out.println();
        System.out.println("Battle");
        System.out.println("=============");

        while (true) {
            getDamage(goblin, player);
            if (goblin.hp == 0) {
                printWinner(player, goblin);
                break;
            }
            getDamage(player, goblin);
            if (player.hp == 0) {
                printWinner(goblin, player);
                break;
            }
        }

        System.out.println();
        System.out.println("Battle");
        System.out.println("=============");

        while (true) {
            getDamage(ogre, player);
            if (ogre.hp == 0) {
                printWinner(player, ogre);
                break;
            }
            getDamage(player, ogre);
            if (player.hp == 0) {
                printWinner(ogre, player);
                break;
            }
        }

        System.out.println();
        System.out.println("Battle");
        System.out.println("=============");

        while (true) {
            getDamage(archer, player);
            if (archer.hp == 0) {
                printWinner(player, archer);
                break;
            }
            getDamage(player, archer);
            if (player.hp == 0) {
                printWinner(archer, player);
                break;
            }
        }

        System.out.println();
        System.out.println("Game ends");
        System.out.println("=============");
        showInfo(player);
        showInfo(goblin);
        showInfo(ogre);
        showInfo(archer);
    }

    private static void buyWeapon(Role role) {
        String[] weapons = {
                "Gatling in the blue light",
                "Poison dagger",
                "Golden Chain Mail",
                "Sprinter Bolt",
                "Berserker Helm"
        };
        Random random = new Random();
        int index = random.nextInt(weapons.length);
        String weapon = weapons[index];
        role.buy();
        System.out.println(role.getName() + " get " + weapon);
    }

    private static void printWinner(Role attacker, Role defender) {
        int gainExp = 5 * defender.str;
        attacker.exp += gainExp;
        System.out.println(attacker.getName() + " gains " + gainExp + "xp");
        while (attacker.exp >= attacker.maxExp) {
            upLevel(attacker);
        }
    }

    private static void getDamage(Role attacker, Role defender) {
        Random r1 = new Random();
        Random r2 = new Random();
        Random r3 = new Random();
        String[] attack_desc = {
                "%s Turn behind the other person and give %s a punch。",
                "%s Flying into mid air and giving %s a palm。",
                "%s Give a loud shout to %s",
                "%s Hammer towards %s legs。",
                "%s crash into %s。",
                "%s Attack with bows and arrows %s。"
        };
        int index = r2.nextInt(attack_desc.length);
        String desc = attack_desc[index];
        int damage = r1.nextInt(20) + 1;  // get r1 damage from 1 to 20
        String result = String.format(desc, attacker.getName(), defender.getName());
        System.out.println(result + " takes " + damage + " damage");

        String[] injured_desc = {
                "%s not a scratch",
                "%s Causing a bruise",
                "Hit with one hit, causing %s pain and bending down",
                "%s Let out a muffled groan in pain",
                "%s ，Falling to the ground in a fall",
                "%s，took several steps back in a row",
                "%s Blood spurted out from the mouth",
                "%s With a scream, it collapsed like a puddle of soft mud"
        };
        index = r3.nextInt(injured_desc.length);
        desc = injured_desc[index];
        result = String.format(desc, defender.getName());
        System.out.println(result);
        System.out.println();

        defender.hp -= damage;
        if (defender.hp < 0) {
            defender.hp = 0;
            System.out.println(defender.getName() + " is dead");
        }

        System.out.println(defender);
    }

    private static void showInfo(Role role) {
        String[] boyFace = {"round face", "square face ", "suntanned", "scar", "high cheek bones", "spiky hair", "hazel eyes"};
        String[] girlFace = {"beautiful", "common", "spiky hair", "freckles", "fair-skinned", "baby-faced", "oval face"};
        Random r = new Random();
        int index = r.nextInt(boyFace.length);
        if (role.gender == 'g') {
            System.out.println(role.getName() + " looks like " + girlFace[index]);
        }
        if (role.gender == 'b') {
            System.out.println(role.getName() + " looks like " + boyFace[index]);
        }
        System.out.println(role);
    }

    private static void upLevel(Role role) {
        role.maxExp += 10;
        role.hp = role.maxHp;
        role.level += 1;
        System.out.println(role.getName() + " levels up to " + role.getLevel());
    }
}