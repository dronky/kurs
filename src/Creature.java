import java.util.ArrayList;
import java.util.Random;

public class Creature {
    private int id, posi, posj;
    private ArrayList<String> cmd;
    private boolean alive;
    private int labirint[][] = {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 1, 1},
            {1, 0, 1, 0, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1}
    };

    public Creature(int id, int posi, int posj, boolean alive) {
        cmd = new ArrayList<>();
        this.id = id;
        this.posi = posi;
        this.posj = posj;
        this.alive = alive;
        if (id < SingletonCreatures.getInstance().getCreaturesInGeneration())
            firstGen();

    }

    public Creature(int id, int posi, int posj, boolean alive, ArrayList<String> commands) {
        cmd = new ArrayList<>(commands);
        this.id = id;
        this.posi = posi;
        this.posj = posj;
        this.alive = alive;
        if (id < SingletonCreatures.getInstance().getCreaturesInGeneration())
            firstGen();

    }


    public ArrayList<String> getCmd() {
        return cmd;
    }

    public void printLabirint() {
        for (int i = 0; i < 8; i++) {
            System.out.print("\n");
            for (int j = 0; j < 7; j++)
                System.out.print(labirint[i][j]);
        }
    }

    public void firstGen() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int command = r.nextInt(2);
            switch (command) {
                case 0:
                    cmd.add("right");
                    break;
                case 1:
                    cmd.add("down");
                    break;
            }
        }
    }

    private void step() {
        for (int i = 0; i < cmd.size(); i++) {
            if (alive)
                switch (cmd.get(i)) {
                    case "right":
                        this.posj++;
                        if (!checkWall()) {
                            this.posj--;
                            alive = false;
                        }
                        break;
                    case "down":
                        this.posi++;
                        if (!checkWall()) {
                            this.posi--;
                            alive = false;
                        }
                        break;
                }
            else break;
            //after ever step checking walls

        }
    }

    public boolean start() {
        step();
        return alive;
    }

    private boolean checkWall() {
        return labirint[posi][posj] == 1 ? false : true;
    }

    public Double distance() {
        return Math.sqrt(Math.pow(6 - posi, 2) + Math.pow(5 - posj, 2));
    }

}
