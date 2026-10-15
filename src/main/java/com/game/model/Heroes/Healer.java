package main.java.com.game.model.Heroes;

import main.java.com.game.model.Fighter;

// main.java.com.game.Healer гораздо более гибкий чем main.java.com.game.PlayableHeroes
// main.java.com.game.PlayableHeroes может лечить, но! это прежде всего боец, так как наследовал всё от main.java.com.game.Fighter
// Если создавать лечащий артефакт, то он будет наследоваться от интерфейса main.java.com.game.Healer
public interface Healer {
    void heal (Fighter target);
}