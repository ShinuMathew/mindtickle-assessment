package mtapitest.clients;

import java.io.FileInputStream;
import java.nio.file.Paths;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import mtapitest.enums.MTErrorCode;
import mtapitest.exceptions.MTException;
import mtapitest.tests.helper.CommonTestHelper;

public class ExcelDocumentParser {

	private static final Logger log = LoggerFactory.getLogger(CommonTestHelper.class);
	private String path;
	private XSSFWorkbook workbook;

	public ExcelDocumentParser(String path) {
		this.path = path;
	}

	public void readWorkbook() throws MTException {
		try {
			String filePath = Paths.get(this.path).toString();
			log.info("Fetching data from " + filePath);
			FileInputStream file = new FileInputStream(filePath);
			this.workbook = new XSSFWorkbook(file);
		} catch (Exception e) {
			throw new MTException(MTErrorCode.EXCEL_PARSING_FAILED + e.getMessage());
		}
	}

	public XSSFSheet loadSheet(String sheetName) {
		return this.workbook.getSheet(sheetName);
	}

}
