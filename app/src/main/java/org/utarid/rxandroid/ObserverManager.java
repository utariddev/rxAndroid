package org.utarid.rxandroid;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class ObserverManager {

    private Observer<String> numbersObserver;
    private DisposableObserver<String> numbersDisposableObserverEven;
    private DisposableObserver<String> numbersDisposableObserverOdd;
    private Disposable disposable;

    public ObserverManager() {
        //basit örnek
        numbersObserver = createNumbersObserver();

        //disposableObservable örneği
        numbersDisposableObserverEven = createNumbersDisposableObserverEven();
        numbersDisposableObserverOdd = createNumbersDisposableObserverOdd();
    }


    private DisposableObserver<String> createNumbersDisposableObserverOdd() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                Log.d("rxAndroid", "onNext odd: " + s);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("rxAndroid", "onError odd: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("rxAndroid", "onComplete odd");
            }
        };
    }

    private DisposableObserver<String> createNumbersDisposableObserverEven() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                Log.d("rxAndroid", "onNext even: " + s);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("rxAndroid", "onError even: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("rxAndroid", "onComplete even");
            }
        };
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
                Log.d("rxAndroid", "onNext: " + s);
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

    public DisposableObserver<String> getNumbersDisposableObserverEven() {
        return numbersDisposableObserverEven;
    }

    public void setNumbersDisposableObserverEven(DisposableObserver<String> numbersDisposableObserverEven) {
        this.numbersDisposableObserverEven = numbersDisposableObserverEven;
    }

    public DisposableObserver<String> getNumbersDisposableObserverOdd() {
        return numbersDisposableObserverOdd;
    }

    public void setNumbersDisposableObserverOdd(DisposableObserver<String> numbersDisposableObserverOdd) {
        this.numbersDisposableObserverOdd = numbersDisposableObserverOdd;
    }
}
