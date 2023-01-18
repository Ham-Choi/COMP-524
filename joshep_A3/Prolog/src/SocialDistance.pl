givenSizes(6,15,10).
givenSizes(13,30,30).
givenSizes(27,120,50).

givenSafe(13,30,30).
givenSafe(6,30,10).
givenSafe(27,30,50).
givenSafe(13,15,50).
givenSafe(13,120,10).
givenSafe(27,120,30).
givenSafe(6,15,30).

smallDistance(6).
mediumDistance(13).
largeDistance(27).

smallDuration(15).
mediumDuration(30).
largeDuration(120).

smallExhalation(10).
mediumExhalation(30).
largeExhalation(50).


%Consider the three smaller problems of determining if a distance, duration, or exhalation level is at least as safe a given distance, duration and exhalation level.
%We know, for example, that a distance is at least as safe as another if it is greater than or equal to that distance. Each of these problems can be associated with its own two parameter query.
derivedSafe(Distance,Duration,Exhalation) :-
	givenSafe(GivenDistance,GivenDuration,GivenExhale),
	GivenDistance =< Distance, GivenDuration >= Duration , GivenExhale >= Exhalation.



distanceInterpolation(Distance, Interpolated) :-
	smallDistance(SmallDist),
	SmallDist > Distance,
	Interpolated = 0;

	mediumDistance(MediumDist), smallDistance(SmallDist),
	MediumDist > Distance, SmallDist =< Distance,
	Interpolated = SmallDist;

	largeDistance(LargeDist), mediumDistance(MediumDist),
	LargeDist > Distance, MediumDist =< Distance,
	Interpolated = MediumDist;

	largeDistance(LargeDist),
	LargeDist =< Distance,
	Interpolated = LargeDist.


durationInterpolation(Duration, Interpolated) :-
	largeDuration(LargeDur),
	LargeDur < Duration,
	Interpolated = 200;

	mediumDuration(MediumDur), largeDuration(LargeDur),
	MediumDur < Duration, LargeDur >= Duration,
	Interpolated = LargeDur;

	smallDuration(SmallDur), mediumDuration(MediumDur),
	SmallDur < Duration, MediumDur >= Duration,
	Interpolated = MediumDur;

	smallDuration(SmallDur), SmallDur >= Duration,
	Interpolated = SmallDur.


exhalationInterpolation(Exhalation, Result) :-
	largeExhalation(LargeExh),
	LargeExh < Exhalation,
	Result = 200;

	mediumExhalation(MediumExh), largeExhalation(LargeExh),
	MediumExh < Exhalation, LargeExh >= Exhalation,
	Result = LargeExh;

	smallExhalation(SmallExh), mediumExhalation(MediumExh),
	SmallExh < Exhalation, MediumExh >= Exhalation,
	Result = MediumExh;

	smallExhalation(SmallExh), SmallExh >= Exhalation,
	Result = SmallExh.


interpolatedSafe(Distance, Duration, Exhalation) :-
	distanceInterpolation(Distance, DistanceInterpolated),
	durationInterpolation(Duration, DurationInterpolated),
	exhalationInterpolation(Exhalation, ExhalationInterpolated),
	givenSafe(DistanceInterpolated,DurationInterpolated,ExhalationInterpolated).


interpolatedSafe(Distance, Duration) :-
	distanceInterpolation(Distance, DistanceInterpolated),
	durationInterpolation(Duration, DurationInterpolated),
	mediumExhalation(MediumExhale),
	givenSafe(DistanceInterpolated,DurationInterpolated,MediumExhale).


interpolatedSafe(Distance) :-
	distanceInterpolation(Distance, DistanceInterpolated),
	mediumDuration(MediumDuration),
	mediumExhalation(MediumExhale),
	givenSafe(DistanceInterpolated,MediumDuration,MediumExhale).


generateSafeDistancesAndDurations(Distance, Duration, Exhalation) :-
	exhalationInterpolation(Exhalation, ExhalationInterpolated),
	givenSafe(Distance, Duration, ExhalationInterpolated).


safeTuples(
	[
		[13,30,30],
		[6,30,10],
		[27,30,50],
		[13,15,50],
		[13,120,10],
		[27,120,30],
		[6,15,30]
	]
).

%Call another query to create the full safety table, and then recursively process the table.
listGivenSafe([Distance, Duration, Exhalation]) :-
	safeTuples(SafeCombinations),
	listGivenSafe([Distance, Duration, Exhalation], SafeCombinations).

%You will need to recursively reduce both the safety table and N.
%Call another query to create the full safety table, and then recursively process the table.
listGivenSafe(Values, [Head | Tail]) :-
	Values = Head;
	listGivenSafe(Values, Tail).


%You will need to recursively reduce both the safety table and N.
printGivenCombinations(N) :-
	write("Distance, Duration, Exhalation, IsSafe"), nl,
	safeTuples(SafeCombinations),
	printGivenCombinations(N, SafeCombinations).


%You will need to recursively reduce both the safety table and N.
printGivenCombinations(N, [[Distance, Duration, Exhalation] | Tail]) :-
	N > 0,
	write(Distance), write(","), write(Duration), write(","), write(Exhalation), write(","), write("true"), nl,
	N1 is N - 1,
	printGivenCombinations(N1, Tail).


append([],List,List).

append([Head | Tail],List2,[Head | Result]) :-
	append(Tail,List2,Result).


listGenerateSafeDistancesAndDurations(Exhalation, GeneratedTable) :-
	exhalationInterpolation(Exhalation, ExhalationInterpolated),
	safeTuples(SafeCombinations),
	listGenerateSafeDistancesAndDurations(ExhalationInterpolated, GeneratedTable, [], SafeCombinations).


listGenerateSafeDistancesAndDurations(_, GeneratedTable, TableAppend, []) :-
	GeneratedTable = TableAppend.


listGenerateSafeDistancesAndDurations(Exhale, GeneratedTable, TableAppend, [[Distance, Duration, Exhalation] | Tail]) :-
	Exhale \= Exhalation,
	listGenerateSafeDistancesAndDurations(Exhale, GeneratedTable, TableAppend, Tail);

	Exhale = Exhalation,
	append(TableAppend, [[Distance, Duration]], Result),
	listGenerateSafeDistancesAndDurations(Exhale, GeneratedTable, Result, Tail).


