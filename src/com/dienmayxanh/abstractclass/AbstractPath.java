package com.dienmayxanh.abstractclass;

public abstract class AbstractPath {
	private static String projPath = System.getProperty("user.dir");
	
	public static String getScreenShotsFolderPath() {
		return projPath+"\\screenshots\\";
	}
	
	public static String getTestCasesFolderPath() {
		return projPath+"\\test cases\\";
	}
	
	public static String getReportFilePath() {
		return projPath+"\\DienmayXANH-FunctionalTestExecution.xlsx";
	}
}
