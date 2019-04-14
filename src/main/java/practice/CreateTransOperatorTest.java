package practice;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observables.GroupedObservable;
import testclass.Shape;
import util.CommonUtils;
import util.Log;

import java.util.concurrent.TimeUnit;

public class CreateTransOperatorTest {


    public static void basic() {
        String[] objs = {"1 CIRCLE", "2 DIAMOND", "3 TRIANGLE", "4 DIAMOND", "5 CIRCLE", "6 HEXAGON"};
        Observable<String> source = Observable.fromArray(objs)
                .filter(obj -> obj.endsWith("CIRCLE"));
        source.subscribe(System.out::println);
    }

    public static void first() {
        Integer[] numbers = {100, 200, 300, 400, 500};
        Single<Integer> single;
        single = Observable.fromArray(numbers).first(-1);
        single.subscribe(data -> System.out.println("first() value = " + data));
    }

    public static void reduce() {
        String[] balls = {"1", "3", "5"};
        Maybe<String> source2 = Observable.fromArray(balls)
                .reduce((ball1, ball2) -> ball2 + "(" + ball1 + ")");
        source2.subscribe(System.out::println);
    }

    public static void interval() {
        CommonUtils.exampleStart();
        Observable<Long> source3 = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
                .map(val -> val + 100)
                .take(5);
        source3.subscribe(Log::i);
        CommonUtils.sleep(1000);
    }

    public static void range() {
        Observable<Integer> source4 = Observable.range(1, 10)
                .filter(n -> n % 2 == 0);
        source4.subscribe(Log::i);
    }

    public static void concatMap() {

        //순서 보장
        CommonUtils.exampleStart();
        String[] balls = {"1", "2", "3"};

        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "<>")
                        .take(2)
                );

        source.subscribe(Log::i);
        CommonUtils.sleep(2000);
    }

    public static void switchMap() {
        String[] objs = {"6", "4", "2-T", "2", "6-T", "4-T"};
        Observable<GroupedObservable<String, String>> source =
                Observable.fromArray(objs).groupBy(Shape::getSuffix);

        source.subscribe(obj -> {
            obj.subscribe(
                    val -> System.out.println("GROUP:" + obj.getKey() + "\t Value" + val));

        });
    }

    public static void scan() {
        String[] balls = {"1", "2", "3"};
        Observable<String> source = Observable.fromArray(balls)
                .scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
        source.subscribe(Log::i);
    }


    public static void main(String[] args) {

    }


}
