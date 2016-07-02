package com.dalnimsoft.dalread;

public class SearchDicEng implements ISearchDic {
	public void searchDic() {
		// TODO Auto-generated method stub
		System.out.println("SearchDicEng SearchDic");
	}
	public boolean isRightChar(String strOneChar) {
		// TODO Auto-generated method stub
		if (strOneChar.matches("[a-z|A-Z]")) {
			return true;
		}
		return false;
	}
}
