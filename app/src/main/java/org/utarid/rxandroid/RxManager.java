package org.utarid.rxandroid;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RxManager {

    private final CompositeDisposable compositeDisposable;

    public RxManager() {
        ObservableManager observableManager = new ObservableManager();
        ObserverManager observerManager = new ObserverManager();
        compositeDisposable = new CompositeDisposable();

        observableManager.getNumbersObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observerManager.getNumbersObserver());

        compositeDisposable.add(observableManager.getNumbersObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(s -> Integer.parseInt(s) % 2 == 1)
                .subscribeWith(observerManager.getNumbersDisposableObserverOdd()));

        compositeDisposable.add(observableManager.getNumbersObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(s -> Integer.parseInt(s) % 2 == 0)
                .subscribeWith(observerManager.getNumbersDisposableObserverEven()));
    }

    public void destroy() {
        compositeDisposable.clear();
    }
}
