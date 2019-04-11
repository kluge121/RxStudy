import io.reactivex.Observable
import io.reactivex.functions.Function
import java.util.*

class Gugudan {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val sc = Scanner(System.`in`)
            print("리액티브 구구단")
            val dan = sc.nextInt();

//
//            val gugudanFunction = Function<Int, Observable<String>> {
//                Observable.range(1, 9).map { value ->
//                    "$dan * $value = ${dan * value}"
//                }
//
//            }
//
//            val source: Observable<String> = Observable.just(dan).flatMap(gugudanFunction)

            val source2: Observable<String> = Observable.just(dan).flatMap {
                Observable.range(1, 9).map {
                    "$dan * $it = ${dan * it}"
                }
            }
            source2.subscribe { println(it) }

        }
    }

}