package com.iprismtech.studentvarsity.base;

public interface IBaseView<T> {
    /**
     *  Initialize the things which are common for all subclasses
     */
    void initializeInstances();

    /**
     * Here we set the generic presenter
     * Override setPresenter in all sub classes and set the presenter
     * Compulsory to Override in each screen
     */
    void setPresenter();
}
