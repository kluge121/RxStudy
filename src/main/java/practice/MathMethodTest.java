package practice;

import hu.akarnokd.rxjava2.math.MathFlowable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import util.Log;

public class MathMethodTest {

    static Integer[] data = {1, 2, 3, 4};

    public static void count() {
        Single<Long> source = Observable.fromArray(data)
                .count();
        source.subscribe(Log::i);
    }

    public static void max() {
        Flowable.fromArray(data)
                .to(MathFlowable::max)
                .subscribe(max -> Log.i("max is " + max));
    }

    public static void min() {
        Flowable.fromArray(data)
                .to(MathFlowable::min)
                .subscribe(max -> Log.i("min is " + max));
    }

    public static void sum() {
        Flowable<Integer> flowable = Flowable.fromArray(data)
                .to(MathFlowable::sumInt);
        flowable.subscribe(sum -> Log.i("sum is " + sum));


    }

    public static void avg() {
        Flowable<Double> flowable2 = Observable.fromArray(data)
                .toFlowable(BackpressureStrategy.BUFFER)
                .to(MathFlowable::averageDouble);
        flowable2.subscribe(avg -> Log.i("avg is " + avg));
    }


    public static void main(String[] args) {
        //시간제어 유틸리티
        //1. delay()
        //2. timeInterval() 발행되는 데이터간의 발행 간격을 리턴함


    }
}
