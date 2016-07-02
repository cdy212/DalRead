package com.dalnimsoft.dalread;

public class SearchDicJp implements ISearchDic {

	public void searchDic() {
		// TODO Auto-generated method stub
		System.out.println("SearchDicCh SearchDic");
	}

	public boolean isRightChar(String strOneChar) {
		// TODO Auto-generated method stub
		System.out.println(strOneChar);
		if (strOneChar.matches("[\u4e00-\u9FCF]")) {
			return true;
		}
		return false;
	}
}
