import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello genetic algorithm!");
        for (int i = 0; i < SingletonCreatures.getInstance().getCreaturesInGeneration(); i++) {
            Creature creature = new Creature(i, 1, 1, true);
            creature.start();
            SingletonCreatures.getInstance().addToList(creature);
        }
        ArrayList<Creature> topList = new ArrayList<>();
        double d = 100;
        int i = 2;
        while (d > 0) {
            SingletonCreatures.getInstance().setGeneration(i);
            topList.clear();
            for (int p = 0; p < SingletonCreatures.getInstance().getCreatureList().size(); p++) {
                SingletonCreatures.getInstance().getCreatureList().get(p).start();
            }
            topList = GenerationManager.tenTop(SingletonCreatures.getInstance().getCreatureList(), SingletonCreatures.getInstance().getCreaturesInGeneration() / 4);
            SingletonCreatures.getInstance().setCreatureList(GenerationManager.crossing(topList));

            for (int o = 0; o < topList.size(); o++) {
                if (topList.get(o).distance() < d)
                    d = topList.get(o).distance();
            }
            i++;
        }
        System.out.print("Survived generation: " + SingletonCreatures.getInstance().getGeneration());
    }
}
