package com.dalnimsoft.dalread;

public class SearchDicCh implements ISearchDic {

	public void searchDic() {
		// TODO Auto-generated method stub
		System.out.println("SearchDicCh SearchDic");
	}

	public boolean isRightChar(String strOneChar) {
		// TODO Auto-generated method stub
		System.out.println(strOneChar);
		if (strOneChar.matches("[\u3400-\u9FBF]")) {
			return true;
		}
		return false;
	}
}

