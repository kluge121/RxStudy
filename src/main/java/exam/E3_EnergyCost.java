package exam;

import io.reactivex.Observable;
import kotlin.Pair;
import util.Log;

import java.text.DecimalFormat;

public class E3_EnergyCost {

    public static void main(String[] args) {


        String[] data = {"100", "300"};

        Observable<Integer> basePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val -> {
                    if (val <= 200) return 910;
                    if (val <= 400) return 1600;
                    return 7300;
                });

        Observable<Integer> usagePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val -> {
                    double g1 = Math.min(200, val) * 93.3;
                    double g2 = Math.min(200, Math.max(val - 200, 0)) * 187.9;
                    double g3 = Math.min(0, Math.max(val - 400, 0)) * 280.65;
                    return (int) (g1 + g2 + g3);
                });

        Observable<Pair<String, Integer>> source = Observable.zip(
                basePrice,
                usagePrice,
                Observable.fromArray(data),
                (p1, p2, i) -> new Pair<>(i, p1 + p2));
        source.subscribe(pair -> {
            StringBuilder sb = new StringBuilder();
            sb.append("사용한 전기량 : ").append(pair.getFirst()).append("\n")
                    .append("전기요금 :  ").append(new DecimalFormat("#,###").format(pair.getSecond())).append("\n")
                    .append("---------------------").append("\n");
            Log.i(sb.toString());
        });


    }
}
