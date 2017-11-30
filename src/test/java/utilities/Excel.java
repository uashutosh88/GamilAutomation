package utilities;

public class Excel {

	public static ManagerExcel excel = new ManagerExcel(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");

	public static Object[][] getData(String sheetName) {

		int rowNum = excel.getRowCount(sheetName);
		int colNum = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rowNum - 1][colNum];

		for (int rows = 2; rows <= rowNum; rows++) {

			for (int cols = 0; cols < colNum; cols++) {

				data[rows - 2][cols] = excel.getCellData(sheetName, cols, rows);

			}

		}

		return data;

	}

}
