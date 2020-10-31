package org.utarid.rxandroid;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxManager {

    public RxManager() {
        ObservableManager observableManager = new ObservableManager();
        ObserverManager observerManager = new ObserverManager();

        observableManager.getNumbersObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observerManager.getNumbersObserver());
    }
}
