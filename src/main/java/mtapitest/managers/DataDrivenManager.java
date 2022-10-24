package mtapitest.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import mtapitest.clients.ExcelDocumentParser;
import mtapitest.constants.MTConstants;
import mtapitest.exceptions.MTException;
import mtapitest.objects.request.User;

public class DataDrivenManager {

	private ExcelDocumentParser excelDocParser;

	public DataDrivenManager() {
	}

	public List<User> loadUserDataFromExcel() throws MTException {
		List<User> users = new ArrayList<User>();
		String path = MTConstants.TEST_DATA_PATH + "/user-data.xlsx";
		this.excelDocParser = new ExcelDocumentParser(path);
		this.excelDocParser.readWorkbook();
		XSSFSheet sheet = this.excelDocParser.loadSheet("users");
		Iterator<Row> rowIterator = sheet.iterator();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (row.getRowNum() < 2)
				continue;

			users.add(User.builder().id((int) row.getCell(0).getNumericCellValue())
					.username(row.getCell(1).getStringCellValue()).firstName(row.getCell(2).getStringCellValue())
					.lastName(row.getCell(3).getStringCellValue()).email(row.getCell(4).getStringCellValue())
					.password(row.getCell(5).getStringCellValue())
					.phone(String.valueOf((int) row.getCell(6).getNumericCellValue()))
					.userStatus((int) row.getCell(7).getNumericCellValue()).build());
		}
		return users;
	}
}
