package core.basesyntax.service.parser;

import core.basesyntax.model.FruitTransaction;
import java.util.List;

public interface ParserService {
    List<FruitTransaction> parseData(List<String> sourceData);
}