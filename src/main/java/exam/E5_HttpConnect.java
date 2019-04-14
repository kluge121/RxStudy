package exam;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import util.CommonUtils;
import util.Log;
import util.OkHttpHelper;

public class E5_HttpConnect {

    public static void httpConnect() {

        final String URL_ROOT =
                "https://raw.githubusercontent.com/yudong80/reactivejava/master/";
        final String FIRST_RUL = "https://api.github.com/zen";
        final String SECOND_URL = URL_ROOT + "/samples/callback_hell";


        //concat with - bad
//        Observable<String> source = Observable.just(FIRST_RUL)
//                .subscribeOn(Schedulers.io())
//                .map(OkHttpHelper::get)
//                .concatWith(Observable.just(SECOND_URL)
//                        .map(OkHttpHelper::get));
//        source.subscribe(Log::i);
//        CommonUtils.sleep(5000);

//        zip- good
        CommonUtils.exampleStart();
        Observable<String> source1 = Observable.just(FIRST_RUL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get);
        Observable<String> source2 = Observable.just(SECOND_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get);

        Observable.zip(
                source1,
                source2,
                (a, b) -> ("\n>> " + a + "\n>> " + b)
        ).subscribe(Log::i);

        CommonUtils.sleep(5000);

    }

    public static void main(String[] args) {
        httpConnect();
    }
}
