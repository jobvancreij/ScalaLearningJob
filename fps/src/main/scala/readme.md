### Scala book 


### Usefull readings

Higher order functions: Functions that take other functions as input <br> 
polymorphic functions: Functions that can take any variable type as input. 
We can do this by giving the variable type as input. Example: 
```scala
def exampleFunc[A](input: A): A = { 
  ???
}
```

```scala
  def partial1[A,B,C](a: A, f: (A,A,B) => C): (A,B) => C = {
  (b: A,c:B) => f(a, b,c)
}

println(partial1("hoi", (a,b,c) => a + b + c)("c", "d"))
```
Result = "hoicd" <br> 

<b> Variadic functions in Scala</b>: Functions that take zero of more arguments <br> 
example: (zero of more arguments of type A)<br> 

```scala
def apply[A](as: A*): List[A] =
      if (as.isEmpty) Nil
      else Cons(as.head, apply(as.tail: _*))

```

<b> Apply </b>: Apply function is syntactic sugar that converts (arg) in to an argument call. Example: 

```scala
object testJob { 
  def apply(x: Int): Int = { 
    x * x
  }
}
val test = testJob
test(5) 
Output: 25
```

#### Handy codes 


```scala
list.fill(5)("something") // makes a list with 5 times "something"
```
Splits a list of components in to list based on the position of the components 
```scala
List(("a","b"), ("c", "d")).unzip --> (List("a","c"), List("b","d"))
```