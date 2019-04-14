package practice;

import io.reactivex.Observable;
import io.reactivex.Single;
import testclass.Shape;
import util.CommonUtils;
import util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ConditionOperator {

    public static void amb() {
        String[] data1 = {"1", "3", "5"};
        String[] data2 = {"2-R", "4-R"};

        List<Observable<String>> sources = Arrays.asList(
                Observable.fromArray(data1)
                        .doOnComplete(() -> Log.d("#Observable1 : complete")),
                Observable.fromArray(data2)
                        .delay(100L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> Log.d("#Observable2 : complete"))
        );

        Observable.amb(sources)
                .doOnComplete(() -> Log.d("전체 끝"))
                .subscribe(Log::i);
    }

    public static void takeUntil() {
        String[] data = {"1", "2", "3", "4", "5", "6"};
        Observable<String> source = Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (val, notUsed) -> val)
                .takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));
        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
    }

    public static void skipUntil() {
        String[] data = {"1", "2", "3", "4", "5", "6"};
        Observable<String> source = Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (val, notUsed) -> val)
                .skipUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));
        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
    }

    public static void all() {
        String[] data = {"1", "1", "1", "1"};
        Single<Boolean> source = Observable.fromArray(data)
                .map(Integer::parseInt)
                .all(value -> value == 1);
        source.subscribe(Log::i);
    }


    public static void main(String[] args) {


    }
}
