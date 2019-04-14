package practice;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import testclass.Shape;
import util.CommonUtils;
import util.Log;

import java.util.concurrent.TimeUnit;

public class CombineOperatorTest {

    public static void zip_observable() {
        //zip string + string observable 조합!
        String[] shapes = {"BALL", "PENTAGON", "START"};
        String[] coloredTriangles = {"2-T", "6-T", "4-T"};
        Observable<String> source = Observable.zip(
                Observable.fromArray(shapes).map(Shape::getSuffix),
                Observable.fromArray(coloredTriangles).map(Shape::getColor),
                (suffix, color) -> color + suffix);

        source.subscribe(Log::i);
    }

    public static void interval_observable() {
        Observable<String> source = Observable.zip(
                Observable.just("RED", "GREEN", "BLUE"),
                Observable.interval(200L, TimeUnit.MILLISECONDS),
                (str, i) -> str);

        CommonUtils.exampleStart();
        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
    }

    public static void zipWith() {
        Observable<Integer> source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                (a, b) -> a + b)
                .zipWith(Observable.just(1, 2, 3), (ab, c) -> ab + c);
        source.subscribe(Log::i);
    }

    public static void combineLatest() {
        String[] data1 = {"6", "7", "4", "2"};
        String[] data2 = {"DIAMOND", "STAR", "PENTAGON"};

        Observable<String> source = Observable.combineLatest(

                //옵저블 1
                Observable.fromArray(data1)
                        .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS)
                                , (shape, notUsed) -> Shape.getColor(shape)),
                //옵저블 2
                Observable.fromArray(data2)
                        .zipWith(Observable.interval(150L, 200L, TimeUnit.MILLISECONDS),
                                (shape, notUsed) -> Shape.getSuffix(shape)),

                //처리
                (v1, v2) -> v1 + v2);

        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
    }

    public static void merge() {
        String[] data1 = {"1", "3"};
        String[] data2 = {"2", "4", "6"};

        Observable<String> source1 = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> data1[idx])
                .take(data1.length);
        Observable<String> source2 = Observable.interval(50L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> data2[idx])
                .take(data2.length);

        Observable<String> mergeSource = Observable.merge(source1, source2);

        mergeSource.subscribe(Log::i);
        CommonUtils.sleep(1000);
    }

    public static void concat() {

        //Observable을 순서대로 발행하게 보장하는 것 하나가 onComplete되야 다음 Observable 발행시작
        Action onCompleteAction = () -> Log.i("그저로그");
        String[] data1 = {"1", "3", "5"};
        String[] data2 = {"2", "4", "6"};

        Observable<String> source1 = Observable.fromArray(data1)
                .doOnComplete(onCompleteAction);

        Observable<String> source2 = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(inx -> data2[inx])
                .take(data2.length)
                .doOnComplete(onCompleteAction);

        Observable<String> source = Observable.concat(source1, source2)
                .doOnComplete(onCompleteAction);
        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {




    }
}
