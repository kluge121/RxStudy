import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import util.CommonUtils;
import util.Log;

import java.util.concurrent.TimeUnit;

public class Test_Java {


    public static void main(String[] args) {


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
        Observable<Integer> source4 = Observable.range(1,10)
                .filter(n->n%2==0);
        source4.subscribe(Log::it);







    }
}
