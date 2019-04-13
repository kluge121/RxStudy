import io.reactivex.Observable;
import testclass.Shape;
import util.CommonUtils;
import util.Log;

import java.util.concurrent.TimeUnit;

public class CombineOperatorTest {

    public static void main(String[] args) {


        String[] shapes = {"BALL", "PENTAGON", "START"};
        String[] coloredTriangles = {"2-T", "6-T", "4-T"};


        //zip string + string observable 조합!
//        Observable<String> source = Observable.zip(
//                Observable.fromArray(shapes).map(Shape::getSuffix),
//                Observable.fromArray(coloredTriangles).map(Shape::getColor),
//                (suffix, color) -> color + suffix);
//
//        source.subscribe(Log::it);


        //zip string - interval observable 조합
//        Observable<String> source = Observable.zip(
//                Observable.just("RED", "GREEN", "BLUE"),
//                Observable.interval(200L, TimeUnit.MILLISECONDS),
//                (str, i) -> str);
//
//        CommonUtils.exampleStart();
//        source.subscribe(Log::it);
//        CommonUtils.sleep(1000);

        //zip with
        Observable<Integer> source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                (a, b) -> a + b)
                .zipWith(Observable.just(1, 2, 3), (ab, c) -> ab + c);
        source.subscribe(Log::it);


    }
}
