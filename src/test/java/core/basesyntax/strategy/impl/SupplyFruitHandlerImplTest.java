package core.basesyntax.strategy.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.enumeration.Operation;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.FruitHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SupplyFruitHandlerImplTest {
    private static final int QUANTITY_OF_FRUIT_APPLE = 50;
    private static final String NAME_OF_FRUIT_APPLE = "apple";
    private static FruitTransaction fruitTransaction;
    private static FruitHandler fruitHandler;

    @BeforeAll
   static void setUp() {
        fruitHandler = new SupplyFruitHandlerImpl();
        fruitTransaction = new FruitTransaction(Operation.SUPPLY, NAME_OF_FRUIT_APPLE,
                QUANTITY_OF_FRUIT_APPLE);
    }

    @AfterEach
    void tearDown() {
        Storage.getFruitStorage().clear();
    }

    @Test
    void returnFruitHandler_validData_ok() {
        Storage.getFruitStorage().put(NAME_OF_FRUIT_APPLE, 200);
        Assertions.assertDoesNotThrow(() -> fruitHandler.doAction(fruitTransaction));
        Assertions.assertEquals(250,Storage.getFruitStorage().get(fruitTransaction.getFruit()));
    }
}
