import _common.minus10
import _common.plus10
import _common.print
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

fun main(args: Array<String>) {
  val generator = Observable
      .fromIterable(1..5)
      .print()

  val plus10 = generator
      .plus10()
      .observeOn(Schedulers.io())
      .print(spaces = 3)

  val minus10 = plus10
      .minus10()
      .print(spaces = 7)

  minus10
      .doOnComplete { println("Complete") }
      .subscribe { println("${Thread.currentThread()} - ${" ".repeat(11)} $it (Received)") }

  Thread.sleep(1000)
}
