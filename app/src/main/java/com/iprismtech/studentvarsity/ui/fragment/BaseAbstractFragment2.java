package com.iprismtech.studentvarsity.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.iprismtech.studentvarsity.mvp.base.IBaseView;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.ui.activity.FirstScreenActivity;
import com.iprismtech.studentvarsity.utils.MyExceptionHandler;


public abstract class BaseAbstractFragment2<T> extends YouTubePlayerSupportFragment implements IBaseView<T> {

    /**
     * Keeps the generic presenter object so can be used in every fragment.
     */
    protected T presenter;
    protected View view = null;
    protected UserSession userSession;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Here get the instance of the view.
        initializeInstances();
        view = getFragmentView();
        initialiseViews();
        setListenerToViews();
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(getActivity(), FirstScreenActivity.class));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Override the getFragmentView() and return inflated view.
     *
     * @return
     */
    protected abstract View getFragmentView();

    /**
     * Sets the listeners to the views
     */
    protected void setListenerToViews() {

    }

    @Override
    public void initializeInstances() {
        setPresenter();
    }
    /**
     * Initializes views here, make sure to call findViewById with current view object.
     */
    protected void initialiseViews() {
        userSession = new UserSession(getActivity());
    }
}
