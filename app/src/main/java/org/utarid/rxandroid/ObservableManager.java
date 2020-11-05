package org.utarid.rxandroid;

import io.reactivex.Observable;

public class ObservableManager {

    //private final List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private Observable<String> numbersObservable;

    public ObservableManager() {
//        numbersObservable = Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
//        numbersObservable = Observable.fromArray(numbers);

        numbersObservable = Observable.create(emitter -> {
            for (String number : numbers) {
                if (!emitter.isDisposed()) {
                    emitter.onNext(number);
                }
            }

            if (!emitter.isDisposed()) {
                emitter.onComplete();
            }
        });
    }

    public Observable<String> getNumbersObservable() {
        return numbersObservable;
    }

    public void setNumbersObservable(Observable<String> numbersObservable) {
        this.numbersObservable = numbersObservable;
    }
}
