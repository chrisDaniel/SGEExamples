package com.cdaniel.simplegameengine.core;

/**
 * Created by christopher.daniel on 5/7/16.
 */
public interface SimpleGamePlugin {

    void contentFreeze(boolean isFrozen);
    void pause(boolean isPaused);
    void deleteEverything();
    void beforeDraw();
    void afterDraw();
}
