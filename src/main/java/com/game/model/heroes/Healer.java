package com.game.model.heroes;

import com.game.model.Fighter;

// com.game.Healer гораздо более гибкий чем com.game.PlayableHeroes
// com.game.PlayableHeroes может лечить, но! это прежде всего боец, так как наследовал всё от com.game.Fighter
// Если создавать лечащий артефакт, то он будет наследоваться от интерфейса com.game.Healer
public interface Healer {
    void heal (Fighter target);
}