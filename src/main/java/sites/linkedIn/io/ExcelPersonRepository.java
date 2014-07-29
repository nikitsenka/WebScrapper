package sites.linkedIn.io;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sites.linkedIn.model.Person;
import sites.linkedIn.parser.PersonParser;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcelPersonRepository implements PersonRepository{
    private final static Logger LOGGER = Logger.getLogger(ExcelPersonRepository.class.getName());
    private final File file;

    public ExcelPersonRepository(File file) {
        this.file = file;
    }

    /**
     * Returns all persons from the xls file.
     * @return
     * @throws InvalidInputData
     */
    @Override
    public List<Person> getAll() throws InvalidInputData {
        List<Person> persons = new ArrayList<>();
        Workbook workbook = getWorkBook();
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() > 0) {
                String name = getCellStringValue(row, PersonExcelColumn.PERSON_NAME, true);
                Person person = new Person(name);
                person.setDescription(getCellStringValue(row, PersonExcelColumn.PERSON_DESCRIPTION, false));
                persons.add(person);

            }
        }
        return persons;
    }

    /**
     * Save the list of persons in xls sheet. Always rewrites the file. The previous data of the file will be lost.
     * @param persons
     */
    @Override
    public void addAll(List<Person> persons) {
        try(OutputStream stream = new FileOutputStream(file)) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("new sheet");
            for(Person person:persons){
                Row row = sheet.createRow(sheet.getLastRowNum()+1);
                fillRow(row,person);
            }
            workbook.write(stream);
        } catch (IOException e) {
            LOGGER.log(Level.FINE,"Adding persons error",e);
        }
    }

    private Workbook getWorkBook() {
        try(FileInputStream inputStream = new FileInputStream(file)){
            return new XSSFWorkbook(inputStream);
        } catch (Exception e) {
            LOGGER.log(Level.FINE,"getWorkBook error",e);
        }
        return null;
    }

    private void fillRow(Row row, Person person) {
        Cell name = row.createCell(PersonExcelColumn.PERSON_NAME.getIndex());
        name.setCellValue(person.getName());
        Cell desc = row.createCell(PersonExcelColumn.PERSON_DESCRIPTION.getIndex());
        desc.setCellValue(person.getDescription());
    }

    private String getCellStringValue(Row row, Column column, boolean required) throws InvalidInputData {
        Cell cell = row.getCell(column.getIndex());
        if(cell != null){
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String stringCellValue = cell.getStringCellValue();
            if(required && (stringCellValue == null || stringCellValue.isEmpty())){
                throw new InvalidInputData("Required cell string value is null or empty. Row #"+row.getRowNum());
            }
            return stringCellValue;
        }
        return "";
    }
    private int getCellNumValue(Row row, Column column) throws InvalidInputData {
        Cell cell = row.getCell(column.getIndex());
        if(cell != null){
            if(cell.getCellType()!=Cell.CELL_TYPE_NUMERIC){
                throw new InvalidInputData("Cell type is not a number. Row #"+row.getRowNum());
            }
            return (int) cell.getNumericCellValue();
        }
        return 0;
    }
}
