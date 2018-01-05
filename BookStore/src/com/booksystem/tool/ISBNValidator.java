package com.booksystem.tool;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class ISBNValidator extends FieldValidatorSupport{
	
	private String[] isbnList;
	private int s;
	
	
	@Override
	public void  validate(Object obj) throws ValidationException{
		String fieldName = getFieldName();
		String val = (String) getFieldValue(fieldName, obj);
		isbnList = ((String)val).split("-");
		if(isbnList.length!=5){
			addFieldError(fieldName, obj);
			return;
		}
		int country = Integer.parseInt(isbnList[0]);
		int publish = Integer.parseInt(isbnList[1]);
		int bookId = Integer.parseInt(isbnList[2]);
		int bookCode = Integer.parseInt(isbnList[3]);
		int code = Integer.parseInt(isbnList[4]);
		s=(country/100)*1+((country-(country/100)*100)/10)*3+(country%10)*1+
				(publish/100)*3+((publish-(publish/100)*100)/10)*1+(publish%10)*3+
				(bookId/100)*1+((bookId-(bookId/100)*100)/10)*3+(bookId%10)*1+
				(bookCode/100)*3+((bookCode-(bookCode/100)*100)/10)*1+(bookCode%10)*3;
		s=s%10;
		s=10-s;
		if(s==10){
			if(code!=0)
				addFieldError(fieldName, obj);
		}else{
			if(code!=s)
				addFieldError(fieldName, obj);
		}
	}
}
