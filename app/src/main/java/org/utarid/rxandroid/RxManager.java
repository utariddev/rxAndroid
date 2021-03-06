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
                .repeat(2)//observable datasını 2 defa işleyecek
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .filter(s -> Integer.parseInt(s) < 6)//6 dan kucuk sayilar icin calisacak
                .map(s -> s + "0")//observable dan gelen datanın sonuna 0 ekler
                .subscribe(observerManager.getNumbersObserver());

        //disposableObserver ornegi
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
