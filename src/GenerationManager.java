import java.util.ArrayList;
import java.util.Random;

public class GenerationManager {
    public static ArrayList<Creature> tenTop(ArrayList<Creature> list, int size) {
        double dist;
        int k = 0;
        ArrayList<Creature> topCreatures = new ArrayList<>();
        while (k < size) {
            dist = list.get(0).distance();
            //find min
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).distance() < dist)
                    dist = list.get(i).distance();
            }
            //find top creatures
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).distance() == dist && k < size) {
                    topCreatures.add(list.get(i));
                    list.remove(i);
                    k++;
                }
            }

        }
        return topCreatures;
    }

    public static ArrayList<Creature> crossing(ArrayList<Creature> topList) {
        Random r = new Random();
        int c = 5; //lokus, point of cross
        // int c=r.nextInt(8)+2; //lokus, point of cross
        ArrayList<Creature> crossedList = new ArrayList<>();
        ArrayList<String> commandsOne = new ArrayList<>();
        ArrayList<String> commandsTwo = new ArrayList<>();
        ArrayList<String> commandsThree = new ArrayList<>();
        ArrayList<String> commandsFour = new ArrayList<>();
        ArrayList<String> commandsFive = new ArrayList<>();
        ArrayList<String> commandsSix = new ArrayList<>();
        ArrayList<String> commandsSeven = new ArrayList<>();
        ArrayList<String> commandsEight = new ArrayList<>();

        for (int i = 1; i <= topList.size() / 2; i++) {
            commandsOne.addAll(topList.get(i - 1).getCmd());
            commandsTwo.addAll(topList.get(topList.size() - i).getCmd());
            for (int n = 0; n < c; n++) {
                commandsFive.add(commandsOne.get(n));
                commandsSix.add(commandsTwo.get(n));
            }
            for (int n = c; n < commandsOne.size(); n++) {
                commandsFive.add(commandsOne.get(commandsOne.size() - 1 - n));
                try {
                    commandsSix.add(commandsTwo.get(commandsTwo.size() - 1 - n));
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.out.print(" N=" + n + " SIZE1:" + commandsTwo.size() + " SIZE2:" + commandsOne.size() + ";");
                }

            }

            for (int n = c; n < commandsOne.size(); n++) {
                commandsSeven.add(commandsOne.get(n));
                commandsEight.add(commandsTwo.get(n));
            }

            for (int n = 0; n < c; n++) {
                commandsSeven.add(commandsOne.get(n));
                commandsEight.add(commandsTwo.get(n));
            }

            for (int n = c; n < commandsOne.size(); n++) {
                String temp = commandsOne.get(n);
                commandsOne.set(n, commandsTwo.get(n));
                commandsTwo.set(n, temp);
            }
            //sample
            //1)100110}
            //         -> 1)100100 2)111110 3)100001 4)111011 5)100011 6)111110 7)110100 8)100111
            //2)111100}
            //
            for (int n = 0; n < c; n++) {
                commandsThree.add(commandsOne.get(n));
                commandsFour.add(commandsTwo.get(n));
            }
            for (int n = c; n < commandsOne.size(); n++) {
                commandsThree.add(commandsOne.get(commandsOne.size() - 1 - n));
                commandsFour.add(commandsTwo.get(commandsTwo.size() - 1 - n));
            }
            crossedList.add(new Creature((SingletonCreatures.getInstance().getGeneration() - 1) * 104 + i * 8 - 7, 1, 1, true, mutate(commandsOne)));
            crossedList.add(new Creature((SingletonCreatures.getInstance().getGeneration() - 1) * 104 + i * 8 - 6, 1, 1, true, mutate(commandsTwo)));
            crossedList.add(new Creature((SingletonCreatures.getInstance().getGeneration() - 1) * 104 + i * 8 - 5, 1, 1, true, mutate(commandsThree)));
            crossedList.add(new Creature((SingletonCreatures.getInstance().getGeneration() - 1) * 104 + i * 8, 4, 1, true, mutate(commandsFour)));
            crossedList.add(new Creature((SingletonCreatures.getInstance().getGeneration() - 1) * 104 + i * 8 - 3, 1, 1, true, mutate(commandsFive)));
            crossedList.add(new Creature((SingletonCreatures.getInstance().getGeneration() - 1) * 104 + i * 8, 2, 1, true, mutate(commandsSix)));
            crossedList.add(new Creature((SingletonCreatures.getInstance().getGeneration() - 1) * 104 + i * 8 - 1, 1, 1, true, mutate(commandsSeven)));
            crossedList.add(new Creature((SingletonCreatures.getInstance().getGeneration() - 1) * 104 + i * 8, 1, 1, true, mutate(commandsEight)));
            commandsOne.clear();
            commandsTwo.clear();
            commandsThree.clear();
            commandsFour.clear();
            commandsFive.clear();
            commandsSix.clear();
            commandsSeven.clear();
            commandsEight.clear();
        }
        return crossedList;
    }

    private static ArrayList<String> mutate(ArrayList<String> commands) {
        Random r = new Random();
        double chance = 10;
        if (r.nextInt(100) + 1 <= chance) {

            int pos = r.nextInt(commands.size());
            if (commands.get(pos).equals("right"))
                commands.set(pos, "down");
            else commands.set(pos, "right");
        }
        return commands;
    }
}
