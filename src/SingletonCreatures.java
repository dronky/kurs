import java.util.ArrayList;

public class SingletonCreatures {

    private static volatile SingletonCreatures instance;
    private ArrayList<Creature> creatureList = null;
    private int generation;
    private int creaturesInGeneration=104;

    public SingletonCreatures(){
        creatureList = new ArrayList<>();
    }

    public int getCreaturesInGeneration() {
        return creaturesInGeneration;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public ArrayList<Creature> getCreatureList() {
        return creatureList;
    }

    public void setCreatureList(ArrayList<Creature> creatureList) {
        this.creatureList.clear();
        this.creatureList.addAll(creatureList);
    }

    public void addToList(Creature creature){
        creatureList.add(creature);
    }

    // INSTANCE
        public static SingletonCreatures getInstance() {
            SingletonCreatures localInstance = instance;
            if (localInstance == null) {
                synchronized (SingletonCreatures.class) {
                    localInstance = instance;
                    if (localInstance == null) {
                        instance = localInstance = new SingletonCreatures();
                    }
                }
            }
            return localInstance;
        }

}
