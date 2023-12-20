object first extends App {

  // Define a Monoid trait
  trait Monoid[A] {
    def empty: A

    def combine(x: A, y: A): A
  }

  object MonoidInstances {
    implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
      def empty: Int = 0

      def combine(x: Int, y: Int): Int = x + y
    }

    implicit val stringMonoid: Monoid[String] = new Monoid[String] {
      def empty: String = ""

      def combine(x: String, y: String): String = x + y
    }

    // Add more instances for different types as needed
  }

    implicit class MonoidOps[A](value: A)(implicit m: Monoid[A]) {
      def combine(other: A): A = m.combine(value, other)
    }


    import MonoidInstances._

    val resultInt: Int = 1.combine(2) // Result: 3
    println(s"Result for Int: $resultInt")

    val resultString: String = "Hello, ".combine("Scala") // Result: "Hello, Scala"
    println(s"Result for String: $resultString")
  }
  object FpBasicsExample extends App {
    // Каррінг (Currying)
    def add(x: Int)(y: Int): Int = x + y
    val partiallyApplied = add(5)_
    val result1 = partiallyApplied(3) // Result: 8
    println(s"Result for Currying: $result1")

    // Вищий порядок (Higher-Order Functions)
    def applyNTimes[A](f: A => A, n: Int, a: A): A =
      if (n <= 0) a else applyNTimes(f, n - 1, f(a))

    val result2 = applyNTimes((x: Int) => x + 2, 3, 5) // Result: 11
    println(s"Result for Higher-Order Functions: $result2")

    // Не змінювання стану (Immutable State)
    val numbers = List(1, 2, 3, 4, 5)
    val incremented = numbers.map(_ + 1) // Result: List(2, 3, 4, 5, 6)
    val evenNumbers = numbers.filter(_ % 2 == 0) // Result: List(2, 4)
    println(s"Incremented: $incremented")
    println(s"Even numbers: $evenNumbers")



}
