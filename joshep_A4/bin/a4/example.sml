structure Sum = struct
	val sum = fold1 (op +) 0
	fun println s = print (s ^ "\n")
	val printSum = println o Int.toString o sum
end