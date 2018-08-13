package com.example.naj_t.flickrtoritask.presenters;


/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 * A Class that control the lifecycle of the view
 */
public interface Presenter {
    /**
     * Method Called in (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method Called (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method Called in (Activity or Fragment) onDestroy() method.
     */
    void destroy();


}
