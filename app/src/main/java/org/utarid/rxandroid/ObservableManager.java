package org.utarid.rxandroid;

import io.reactivex.Observable;

public class ObservableManager {

    private Observable<String> numbersObservable;

    public ObservableManager() {
        numbersObservable = Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    }

    public Observable<String> getNumbersObservable() {
        return numbersObservable;
    }

    public void setNumbersObservable(Observable<String> numbersObservable) {
        this.numbersObservable = numbersObservable;
    }
}
