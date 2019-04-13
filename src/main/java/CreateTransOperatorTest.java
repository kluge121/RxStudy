import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import util.CommonUtils;
import util.Log;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CreateTransOperatorTest {


    public static void main(String[] args) {

        String[] balls = {"1", "2", "3"};

////        basic filter
//        String[] objs = {"1 CIRCLE", "2 DIAMOND", "3 TRIANGLE", "4 DIAMOND", "5 CIRCLE", "6 HEXAGON"};
//        Observable<String> source = Observable.fromArray(objs)
//                .filter(obj -> obj.endsWith("CIRCLE"));
//        source.subscribe(System.out::println);
//
//
////        first, last, take, skip ... .
//        Integer[] numbers = {100, 200, 300, 400, 500};
//        Single<Integer> single;
//        single = Observable.fromArray(numbers).first(-1);
//        single.subscribe(data -> System.out.println("first() value = " + data));
//
//
//
//        //basic reduce
//        String[] balls = {"1", "3", "5"};
//        Maybe<String> source2 = Observable.fromArray(balls)
//                .reduce((ball1, ball2) -> ball2 + "(" + ball1 + ")");
//        source2.subscribe(System.out::println);
//
//
//        //Interval Test Long값을 1씩 증가시키면서 값 제공
//        CommonUtils.exampleStart();
//        Observable<Long> source3 = Observable.interval(0L,100L, TimeUnit.MILLISECONDS)
//                .map(val->val+100)
//                .take(5);
//        source3.subscribe(Log::it);
//        CommonUtils.sleep(1000);

        //range
//        Observable<Integer> source4 = Observable.range(1,10)
//                .filter(n->n%2==0);
//        source4.subscribe(Log::it);


        //concat map -> 순서보장

//        CommonUtils.exampleStart();
//        String[] balls = {"1", "2", "3"};
//
//        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
//                .map(Long::intValue)
//                .map(idx -> balls[idx])
//                .take(balls.length)
//                .switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
//                        .map(notUsed -> ball + "<>")
//                        .take(2)
//                );
//
//        source.subscribe(Log::it);
//        CommonUtils.sleep(2000);


        //switch map -> 여러개의 Observable로 그룹화
//        String[] objs = {"6", "4", "2-T", "2", "6-T", "4-T"};
//        Observable<GroupedObservable<String, String>> source =
//                Observable.fromArray(objs).groupBy(CommonUtils::getShape);
//
//        source.subscribe(obj -> {
//            obj.subscribe(
//                    val -> System.out.println("GROUP:" + obj.getKey() + "\t Value" + val));
//
//            });


        Observable<String> source = Observable.fromArray(balls)
                .scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
        source.subscribe(Log::it);

    }


}
