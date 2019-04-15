package GameEngine;

import Entity.Entity;
import Entity.EntitySet;
import Movement.XY;

import java.util.ArrayList;
import java.util.List;

public class Collider {
    Entity attacker;
    Entity defender;
    EntitySet entitySet;

    public Collider(Entity attacker, Entity defender, EntitySet entityset){
        this.attacker = attacker;
        this.defender = defender;
        this.entitySet = entityset;
    }
    public Collider(Entity attacker, EntitySet entitySet){
        this.attacker = attacker;
        this.entitySet = entitySet;
    }

    public List checkSuroundings(){
        List<Entity> surroundingEntities = new ArrayList<Entity>();
        for(int i = -6; i < 7; i++){
            for(int z = -6; z < 7; z++){
                XY dummySearch = this.attacker.getPosition();
                if(new XY(dummySearch.getX() + i,dummySearch.getY() + z) == null){
                    continue;
                }else{
                    surroundingEntities.add(entitySet.findEntity(new XY(dummySearch.getX() + i,dummySearch.getY() + z)));
                }
            }

        }
        return surroundingEntities;
    }
}
