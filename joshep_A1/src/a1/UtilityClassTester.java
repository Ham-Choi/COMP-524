package a1;

public class UtilityClassTester implements gradingTools.comp524f21.assignment1.F21A1SocialDistanceClassRegistry{

	public static void main(String[] args) {
		SocialDistancing.printGivenAndGeneratedCombinationsDerivedSafety();
		SocialDistancing.printSafeDistancesAndDurations(30);
		SocialDistancing.printSafeDistancesAndDurations(29);
		SocialDistancing.printSafeDistancesAndDurations(32);
	}

	@Override
	public Class<?> getBasicSocialDistanceUtility() {
		// TODO Auto-generated method stub
		return SocialDistancing.class;
	}

	@Override
	public Class<?> getSocialDistanceUilityTesterMain() {
		// TODO Auto-generated method stub
		return UtilityClassTester.class;
	}
}
