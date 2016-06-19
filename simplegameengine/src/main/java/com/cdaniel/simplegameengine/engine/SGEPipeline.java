package com.cdaniel.simplegameengine.engine;

/**
 * Created by christopher.daniel on 5/8/16.
 */
interface SGEPipeline {

    void contentFreeze(boolean isFrozen);
    void pause(boolean isPaused);
    void deleteEverything();
}
