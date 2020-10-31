package org.utarid.rxandroid;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ObserverManager {

    private Observer<String> numbersObserver;
    private Disposable disposable;

    public ObserverManager() {
        numbersObserver = createNumbersObserver();
    }

    private Observer<String> createNumbersObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                Log.d("rxAndroid", "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d("rxAndroid", "Number: " + s);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("rxAndroid", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("rxAndroid", "onComplete");
                disposable.dispose();
            }
        };
    }

    public Observer<String> getNumbersObserver() {
        return numbersObserver;
    }

    public void setNumbersObserver(Observer<String> numbersObserver) {
        this.numbersObserver = numbersObserver;
    }
}
