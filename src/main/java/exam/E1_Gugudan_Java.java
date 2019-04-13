package exam;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class E1_Gugudan_Java {

    public static void main(String[] args) {


        Function<Integer, Observable<String>> gugudanFunction = dan ->
                Observable.range(1, 9).map(val -> dan + " * " + val + " = " + (val * dan));
        Observable<String> source = Observable.just(3).flatMap(gugudanFunction);
        source.subscribe(System.out::println);




    }
}
