package core.basesyntax.service.parser.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.service.parser.ReportService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReportServiceImplTest {
    private static final String REPORT_TITLE = "fruit,quantity";
    private static final String FRUIT_BANANA = "banana";
    private static final String FRUIT_APPLE = "apple";
    private static final int QUANTITY_OF_BANANA = 152;
    private static final int QUANTITY_OF_APPLE = 90;
    private static ReportService reportService;

    @BeforeAll
    static void setUp() {
        reportService = new ReportServiceImpl();
    }

    @AfterEach
    void tearDown() {
        Storage.getFruitStorage().clear();
    }

    @Test
    void reportService_withValidData_ok() {
        Storage.getFruitStorage().put(FRUIT_BANANA, QUANTITY_OF_BANANA);
        Storage.getFruitStorage().put(FRUIT_APPLE, QUANTITY_OF_APPLE);
        String testReport = REPORT_TITLE + System.lineSeparator()
                + "banana,152" + System.lineSeparator() + "apple,90";
        String resultReport = reportService.createReport();
        Assertions.assertEquals(testReport, resultReport);
    }

    @Test
    void reportService_withEmptyStorage_notOk() {
        String resultReport = reportService.createReport();
        Assertions.assertEquals(REPORT_TITLE, resultReport);
    }

    @Test
    void reportService_withNullValue_notOk() {
        Storage.getFruitStorage().put(FRUIT_BANANA, null);
        Storage.getFruitStorage().put(FRUIT_APPLE, null);
        String resultReport = reportService.createReport();
        String testReport = REPORT_TITLE + System.lineSeparator()
                + "banana,null" + System.lineSeparator() + "apple,null";
        Assertions.assertEquals(testReport, resultReport);
    }
}