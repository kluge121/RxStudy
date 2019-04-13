package exam;

import io.reactivex.Maybe;
import kotlin.Pair;

import java.util.ArrayList;
import java.util.List;

public class E2_QueryTvSales {

    public static void main(String[] args) {

        List<Pair<String, Integer>> sales = new ArrayList<>();

        sales.add(new Pair<>("TV", 2500));
        sales.add(new Pair<>("Camera", 300));
        sales.add(new Pair<>("TV", 1600));
        sales.add(new Pair<>("Phone", 800));

        Maybe<Integer> tvSales = io.reactivex.Observable.fromIterable(sales)
                .filter(product -> product.component1().equals("TV"))
                .map(Pair::component2)
                .reduce((p1, p2) -> p1 + p2);

        tvSales.subscribe(tot -> System.out.println("TV Salse: $" + tot));
    }
}
