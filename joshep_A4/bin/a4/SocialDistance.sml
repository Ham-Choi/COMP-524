fun givenSafe(13,30,30) = true |
		givenSafe(6,30,10) = true |
		givenSafe(27,30,50) = true |
		givenSafe(13,15,50) = true |
		givenSafe(13,120,10) = true |
		givenSafe(27,120,30) = true |
		givenSafe(6,15,30) = true |
		givenSafe(Distance, Duration, Exhalation) = false;

fun interpolateDistance Distance =
	if (Distance < 6) then 0
	else if (Distance >= 6 andalso Distance < 13) then 6
	else if (Distance >= 13 andalso Distance < 27) then 13
	else 27;
	
fun interpolateDuration Duration =
	if (Duration > 120) then valOf Int.maxInt
	else if (Duration > 30 andalso Duration <= 120) then 120
	else if (Duration > 15 andalso Duration <= 30) then 30
	else 15;

fun interpolateExhalation Exhalation = 
	if (Exhalation > 50) then valOf Int.maxInt
	else if (Exhalation <= 50 andalso Exhalation > 30) then 50
	else if (Exhalation <= 30 andalso Exhalation > 10) then 30
	else 10;

fun interpolatedSafe (Distance, Duration, Exhalation) = 
	let
		val interpolatedDist = interpolateDistance(Distance)
		val interpolatedDur = interpolateDuration(Duration)
		val interpolatedExhale = interpolateExhalation(Exhalation)
	in
		givenSafe(interpolatedDist, interpolatedDur, interpolatedExhale)
	end;
	
val SAFETY_TABLE =
  [(13,30,30),(6,30,10),(27,30,50),(13,15,50),(13,120,10),(27,120,30),
   (6,15,30)]:(int * int * int)list;

fun
	derivedSafe (Distance, Duration, Exhalation, nil) = false |
	derivedSafe (Distance, Duration, Exhalation, (tableDistance, tableDuration, tableExhale) :: tail) = 
		if (Distance >= tableDistance andalso Duration <= tableDuration andalso Exhalation <= tableExhale) then true
		else derivedSafe (Distance, Duration, Exhalation, tail);
		
fun listDerivedSafe(Distance, Duration, Exhalation) = derivedSafe(Distance, Duration, Exhalation, SAFETY_TABLE);

	
fun printSafety (safetyComputer, (Distance, Duration, Exhalation)) = 
		if (safetyComputer(Distance, Duration, Exhalation)) then
		print("Distance:"^Int.toString(Distance)^" Duration:"^Int.toString(Duration)^" Exhalation:"^Int.toString(Exhalation)^" Safe:true"^"\n")
		else print("Distance:"^Int.toString(Distance)^" Duration:"^Int.toString(Duration)^" Exhalation:"^Int.toString(Exhalation)^" Safe:false"^"\n");

		
fun concisePrintSafety (safetyComputer, (Distance, Duration, Exhalation)) = 
		if (safetyComputer(Distance, Duration, Exhalation)) then
		print("("^Int.toString(Distance)^", "^Int.toString(Duration)^", "^Int.toString(Exhalation)^", true)"^"\n")
		else print("("^Int.toString(Distance)^", "^Int.toString(Duration)^", "^Int.toString(Exhalation)^", false)"^"\n");
		
fun 
		listPrintSafety (_, _, nil) = () |
		listPrintSafety (printSafetyFun, safetyComputer, featureHead :: featureTail) =
				let 
						val output = printSafetyFun(safetyComputer, featureHead)
				in listPrintSafety(printSafetyFun, safetyComputer, featureTail)
				end;

fun
	matchingSafeRecursion (matcher, nil, (Distance, Duration, Exhalation)) = false | 
	matchingSafeRecursion (matcher, (Dist, Dur, Exh) :: tableTail, (Distance, Duration, Exhalation)) = 
		if matcher((Dist, Dur, Exh), (Distance, Duration, Exhalation)) then true
		else matchingSafeRecursion (matcher, tableTail, (Distance, Duration, Exhalation));


fun matchingSafe(matcher, (Distance, Duration, Exhalation)) = matchingSafeRecursion(matcher, SAFETY_TABLE, (Distance, Duration, Exhalation));

fun derivedSafeMatcher((Dist, Dur, Exh), (Distance, Duration, Exhalation)) =
		if (Distance >= Dist andalso Duration <= Dur andalso Exhalation <= Exh) then true
		else
		false;

fun matchingDerivedSafe(Distance, Duration, Exhalation) = matchingSafe(derivedSafeMatcher, (Distance, Duration, Exhalation));

fun givenSafeMatcher((Dist, Dur, Exh), (Distance, Duration, Exhalation)) =
		if (Distance = Dist andalso Duration = Dur andalso Exhalation = Exh) then true
		else
		false;

fun matchingGivenSafe(Distance, Duration, Exhalation) = matchingSafe(givenSafeMatcher, (Distance, Duration, Exhalation));

fun toThreeArgumentCurryableFunction
	tupleBasedThreeArgumentFunction x y z = tupleBasedThreeArgumentFunction (x, y, z);

val curryableInterpolatedSafe = toThreeArgumentCurryableFunction interpolatedSafe;

val curriedOnceInterpolatedSafe = curryableInterpolatedSafe 13;

val curriedTwiceInterpolatedSafe = curriedOnceInterpolatedSafe 30;

fun curryableMatchingSafe matcher (Distance, Duration, Exhalation) = matchingSafeRecursion(matcher, SAFETY_TABLE, (Distance, Duration, Exhalation));

val curriedMatchingDerivedSafe = curryableMatchingSafe derivedSafeMatcher;

val curriedMatchingGivenSafe = curryableMatchingSafe givenSafeMatcher;