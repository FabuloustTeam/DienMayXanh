package com.dienmayxanh.test;

import com.dienmayxanh.abstractclass.*;
import com.dienmayxanh.page.*;
import com.dienmayxanh.service.Element;
import com.dienmayxanh.service.ExcelUtils;
import com.dienmayxanh.service.TakeSnapShot;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.dienmayxanh.Enum.*;

public class Comment extends AbstractAnnotation {

	private final int COL_CASE = 2;
	private final int COL_EXPECT = 3;
	private final int COL_RESULT = 4;
	private final int COL_INPUT_COMMENT = 5;
	private final int COL_INPUT_NAME = 6;
	private final int COL_INPUT_IMAGE = 7;
	private final int COL_INPUT_EMAIL = 8;
	private final int COL_TYPE = 9;
	private final int START_ROW = 2;
	private final int COL_TESTNAME = 1;
	private int iTestCaseRow;
	private String caseValue = "";
	private String typeValue = "";
	private String testName = "";
	private String comment = "";
	private String name = "";
	private String image = "";
	private String email = "";
	private String actual = "";
	private String expected = "";
	private CommentPage objComment;
	private Element elementService;
	
	@Parameters({ "url" })
	@Test(priority = 1)
	public void CommentUnsuccessfullyWithError(String url) throws Exception {
		objComment = new CommentPage();
		elementService = new Element();
		
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = START_ROW; i <= iTestCaseRow; i++) {
			caseValue = ExcelUtils.getCellData(i, COL_CASE);
			typeValue = ExcelUtils.getCellData(i, COL_TYPE);
			if(caseValue.equals(Case.UNSUCCESSFULLY.toString()) && typeValue.equals(Type.ERROR.toString())) {
				objComment.goToSendCommentPage();
				
				comment = ExcelUtils.getCellData(i, COL_INPUT_COMMENT);
				objComment.inputComment(comment);
				
				name = ExcelUtils.getCellData(i, COL_INPUT_NAME);
				objComment.inputName(name);
				
				image = ExcelUtils.getCellData(i, COL_INPUT_IMAGE);

				objComment.inputImage(image);
				
				email = ExcelUtils.getCellData(i, COL_INPUT_EMAIL);
				objComment.inputEmail(email);
				
				objComment.pressSend();
				
				while(true) {
					try {
						actual = objComment.getErrorTextCommentContent();
						if(!actual.equals("")) 
							break;
					} catch (Exception e) {
						objComment.pressSend();
					}
				}
				
				expected = ExcelUtils.getCellData(i, COL_EXPECT);
				
				if(actual.equals(expected)) {
					ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
				} else {
					ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
					testName = ExcelUtils.getCellData(i, COL_TESTNAME);
					String fileName = testName + ".png";
					String file = pathFolderImage + fileName;
					try {
						TakeSnapShot.takeSnapShot(file);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				Robot robo = new Robot();
				robo.keyPress(KeyEvent.VK_F5);
				robo.keyRelease(KeyEvent.VK_F5);
				elementService.waitForPageLoad();
			}
		}
	}
	
	@Parameters({ "url" })
	@Test(priority = 2)
	public void CommentUnsuccessfullyWithAlert(String url) throws Exception {
		objComment = new CommentPage();
		elementService = new Element();
		
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = START_ROW; i <= iTestCaseRow; i++) {
			caseValue = ExcelUtils.getCellData(i, COL_CASE);
			typeValue = ExcelUtils.getCellData(i, COL_TYPE);
			if(caseValue.equals(Case.UNSUCCESSFULLY.toString()) && typeValue.equals(Type.ALERT.toString())) {
				objComment.goToSendCommentPage();
				
				comment = ExcelUtils.getCellData(i, COL_INPUT_COMMENT);
				objComment.inputComment(comment);
				
				name = ExcelUtils.getCellData(i, COL_INPUT_NAME);
				objComment.inputName(name);
				
				image = ExcelUtils.getCellData(i, COL_INPUT_IMAGE);
				objComment.inputImage(image);
				
				email = ExcelUtils.getCellData(i, COL_INPUT_EMAIL);
				objComment.inputEmail(email);
				
				objComment.pressSend();
				
				actual = elementService.getAlertMessage();
				expected = ExcelUtils.getCellData(i, COL_EXPECT);
				
				ITestResult result = Reporter.getCurrentTestResult();
				result.setAttribute("testname", ExcelUtils.getCellData(i, COL_TESTNAME));
				if(actual.equals(expected)) {
					ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
				} else {
					ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
				}
				
				Robot robo = new Robot();
				robo.keyPress(KeyEvent.VK_F5);
				robo.keyRelease(KeyEvent.VK_F5);
				elementService.waitForPageLoad();
			}
		}
	}


}
