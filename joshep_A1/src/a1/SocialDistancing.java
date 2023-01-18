package a1;

import java.util.ArrayList;
import java.util.List;


public class SocialDistancing {
	/*	public static void main(String[] args) {
		System.out.println("Yo");
	}
	 */	


	public static boolean isGivenSafe(final int dist, final int dur, final int exhal) {
		if(dist == 6) { 							//small distance
			if(dur == 30) {							//med duration
				if(exhal == 10) {					//small exhale
					return true;
				}
			} else if (dur == 15) {					//small dur
				if(exhal == 30) {					//med exhal
					return true;
				}
			}	
		}else if(dist == 13) {							//med distance
			if(dur == 30) {								//med duration
				if(exhal == 30) {						//med exhal
					return true;
				}
			} else if(dur == 15) {						//small dur
				if(exhal == 50) {						//large exhal
					return true;
				}
			}else if(dur == 120) {						//large dur
				if(exhal == 10) {						//small exhal
					return true;
				}
			}
		}else if(dist == 27) {							//large distance
			if(dur == 30) {								//med dur
				if(exhal == 50) {						//large exhal
					return true;
				}
			} else if (dur == 120) {					//large dur
				if(exhal == 30) {						//med exhal
					return true;
				}
			}
		}
		return false;
	}
	public static boolean isInterpolatedSafe(final int dist, final int dur, final int exhal) {
		//higher dist are safe, so interpolate low (can have 0)
		//lower dur and exhal are safe, so interpolate high (can have max integer) Integer.MAX_VALUE
		final int distance;
		final int duration;
		final int exhale;
		if (dist < 6) {					//distance interpolation
			distance = 0;
		} else if(dist < 13) {
			distance = 6;
		} else if(dist < 27) {
			distance = 16;
		} else {
			distance = 27;
		}
		if (dur > 120) {					//duration interpolation
			duration = Integer.MAX_VALUE;
		} else if(dur > 30) {
			duration = 120;
		} else if(dur > 15) {
			duration = 30;
		} else {
			duration = 15;
		}
		if (exhal > 50) {						//exhale interpolation
			exhale = Integer.MAX_VALUE;
		} else if(exhal > 30) {
			exhale = 50;
		} else if(exhal > 10) {
			exhale = 30;
		} else {
			exhale = 10;
		}
		if (dist == 6 || dist == 0) {			//Safe small dist values
			if (dur == 30) {
				if (exhale == 10) {
					return true;
				}
			}else if (dur == 10) {
				if (exhale == 30) {
					return true;
				}
			}
		}
		if (distance == 13) {						//Safe medium dist values
			if (duration == 30) {					//med dur
				if (exhale == 30) {				//med exhal
					return true;
				}
			} else if (duration == 15) {				//small dur
				if (exhale == 50) {				//large exhal
					return true;
				}
			}else if (duration == 120) {				//large dur
				if (exhale == 10) {				//small exhal
					return true;
				}
			}
		}
		if (distance == 27) {						//Safe large dist values
			if (duration == 30) {					//med dur
				if (exhale == 50) {				//large exhal
					return true;
				}
			}
			if (duration == 120) {					//large dur
				if (exhale == 30) {				//med exhal
					return true;
				}
			}
		}
		return false;

	}
	public static boolean isInterpolatedSafe(final int dist, final int dur) {
		//exhalation level set to medium
		final int distance;
		final int duration;
		if (dist < 6) {					//distance interpolation
			distance = 0;
		} else if(dist < 13) {
			distance = 6;
		} else if(dist < 27) {
			distance = 16;
		} else {
			distance = 27;
		}
		if (dur > 120) {					//duration interpolation
			duration = Integer.MAX_VALUE;
		} else if(dur > 30) {
			duration = 120;
		} else if(dur > 15) {
			duration = 30;
		} else {
			duration = 15;
		}
		if (distance == 13) {					//med dist
			if (duration == 30) {				//med dur
				return true;
			}
		} else if (distance == 27) {			//large dist
			if (duration == 120) {				//large dur
				return true;
			}
		} else if (distance == 6 || distance == 0) {	//small dist
			if (duration == 15) {				//small dur
				return true;
			}
		}
		return false;
	}
	public static boolean isInterpolatedSafe(final int dist) {
		//duration and exhalation levels set to medium
		final int distance;
		if (dist < 6) {					//distance interpolation
			distance = 0;
		} else if(dist < 13) {
			distance = 6;
		} else if(dist < 27) {
			distance = 13;
		} else {
			distance = 27;
		}
		if (distance >= 13) {
			return true;
		}
		return false;
	}
	public static boolean isDerivedSafe(final int dist, final int dur, final int exhal) {
		if(dist >= 6 & dist < 13) { 												//small distance
			if(dur <= 30) {											//med duration
				if(exhal <= 10) {									//small exhale
					return true;
				}
			} else if (dur <= 15) {									//small dur
				if(exhal <= 30) {									//med exhal
					return true;
				}
			}	
		}
		if(dist >= 13 & dist < 27) {										//med distance
			if(dur <= 30) {											//med duration
				if(exhal <= 30) {									//med exhal
					return true;
				}
			} else if(dur <= 15) {									//small dur
				if(exhal <= 50) {									//large exhal
					return true;
				}
			}else if(dur <= 120) {									//large dur
				if(exhal <= 10) {									//small exhal
					return true;
				}
			}
		}
		if(dist >= 27) {										//large distance
			if(dur <= 30) {											//med dur
				if(exhal <= 50) {									//large exhal
					return true;
				}
			} else if (dur <= 120) {								//large dur
				if(exhal <= 30) {									//med exhal
					return true;
				}
			}
		}
		return false;
	}
	public static void printGeneratedCombinationDerivedSafety() {
		final int distance = (int)(Math.random()*32);
		final int duration = (int)(Math.random()*144);
		final int exhale = (int)(Math.random()*60);



		final boolean derivedResult = isDerivedSafe(distance, duration, exhale);
		System.out.println(distance + ", " + duration + ", " + exhale + ", " + derivedResult);
	}
	public static void printGivenAndGeneratedCombinationsDerivedSafety() {
		System.out.println("Distance,Duration,Exhalation,IsSafe");
		System.out.println("13,30,30,true");
		System.out.println("6,30,10,true");
		System.out.println("27,30,50,true");
		System.out.println("13,15,50,true");
		System.out.println("13,120,10,true");
		System.out.println("27,120,30,true");
		System.out.println("6,15,30,true");
		System.out.println("----------");
		for(int i = 0; i < 10; i++) {
			printGeneratedCombinationDerivedSafety();
		}
	}
	public static List<Integer[]> generateSafeDistancesAndDurations(final int exhal) {
		final List<Integer[]> list = new ArrayList<Integer[]>();
		if (exhal < 0) {return list;}
		if (exhal <= 10) { 								//small exhal
			final Integer[] a1 = {6,30};
			final Integer[] a2 = {13,120};
			list.add(a1);
			list.add(a2);
			return list;
		} else if (exhal > 10 && exhal <= 30) {			//med exhal
			final Integer[] a1 = {13,30};
			final Integer[] a2 = {27,120};
			final Integer[] a3 = {6,15};
			list.add(0, a1);
			list.add(1, a2);
			list.add(2, a3);
			return list;
		} else if (exhal > 30) {						//large exhal
			final Integer[] a1 = {27,30};
			final Integer[] a2 = {13,15};
			list.add(0, a1);
			list.add(1, a2);
			return list;
		};
		return list;
	}
	public static void printSafeDistancesAndDurations(final int exhal) {
		final List<Integer[]> list = generateSafeDistancesAndDurations(exhal);
		//		list.forEach(i -> System.out.print("not" + i));
		System.out.print(exhal + ", ");
		for (Integer[] pair: list) {
			System.out.print("{" + pair[0] + "," + pair[1] + "}");
		}
		System.out.println();

		/*
		for (int i = 0; i < list.size(); i++) {
			Integer[] array = list.get(i);
			for (int j : array) {
				System.out.println(j);
			}
		}
		 */				
		//		System.out.println();
//		System.out.println(exhal + "," + generateSafeDistancesAndDurations(exhal).toString());
	}
}
