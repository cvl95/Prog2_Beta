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

    public Collider(Entity attacker, Entity defender, EntitySet entityset) {
        this.attacker = attacker;
        this.defender = defender;
        this.entitySet = entityset;
    }

    public Collider(Entity attacker, EntitySet entitySet) {
        this.attacker = attacker;
        this.entitySet = entitySet;
    }
    public Collider(EntitySet enititySet){
        this.entitySet = enititySet;
    }

    public List checkSuroundings(Entity entity) {
        List<Entity> surroundingEntities = new ArrayList<Entity>();
        for (int i = -6; i < 7; i++) {
            for (int z = -6; z < 7; z++) {
                if (new XY(entity.getPosition().getX() + i, entity.getPosition().getY() + z) == null) {
                    continue;
                } else {
                    surroundingEntities.add(entitySet.findEntity(new XY(entity.getPosition().getX() + i, entity.getPosition().getY() + z)));
                }
            }

        }
        return surroundingEntities;
    }

    public void resolveColision(Entity current){


    }
}
