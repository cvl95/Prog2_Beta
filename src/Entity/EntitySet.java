package Entity;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class EntitySet {

    private static final int LENTGH = 30;
    private Entity[] entitySet = new Entity[this.LENTGH];



    public void addEntity(Entity entity){
        for (int i = 0; i<this.LENTGH;i++) {
            if(entitySet[i]==null){
                entitySet[i] = entity;
                break;
            }else if(i == entitySet.length){
                System.out.println("no place in Array delete elements first");
            }
        }

    }
    public void deleteEntity(Entity entity){
        for (int i = 0; i<this.LENTGH;i++) {
            if(entitySet[i] == entity){
                entitySet[i] = null;
                break;
            }else if(i == entitySet.length){
                System.out.println("there is no such entity in Array.");
            }
        }
    }
    public void deleteEntity(int entityID){
        for (int i = 0; i<this.LENTGH;i++) {
                    if(entitySet[i].getId()==entityID){
                entitySet[i] = null;
                break;
            }else if(i == entitySet.length){
                System.out.println("there is no such entity in Array.");
            }
        }
    }
    public void callNextStep(){
        for (Entity en:entitySet) {
            if(en != null){
                en.nextStep(this);
            }
        }
    }
    public Entity findEntity(int entityID){
        for (int i = 0; i<this.LENTGH;i++) {
            if(entitySet[i] == null){
                i++;
            }
            if(entitySet[i].getId()==entityID){
                return entitySet[i];
            }else if(i == entitySet.length){
                System.out.println("there is no such entity in Array.");
                return null;
            }
        }
        return null;
    }
    public Entity findEntity(Entity entity){
        for (int i = 0; i<this.LENTGH;i++) {
            if(entitySet[i] == null){
                i++;
            }
            if(entitySet[i] == entity){
                return entitySet[i];
            }else if(i == entitySet.length){
                System.out.println("there is no such entity in Array.");
                return null;
            }
        }
        return null;
    }

    public static int getLENTGH() {
        return LENTGH;
    }

    public Entity[] getEntitySet() {
        return entitySet;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (Entity en :entitySet) {
            if(en != null) {
                stringBuilder.append(en + "\n");
            }else{
                count++;
            }
        }
        stringBuilder.append(" the number of free places in array is: "+count);
        String array = stringBuilder.toString();
        return array;

    }
}
