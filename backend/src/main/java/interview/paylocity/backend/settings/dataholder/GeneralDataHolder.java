package interview.paylocity.backend.settings.dataholder;

import java.util.List;
import java.util.Map;

public class GeneralDataHolder {

    // Data Holders

    public List<Map<String, String>> sharedUserDataTable;

    public String sharedUserId;

    // Auxiliary Methods

    public static GeneralDataHolder getInstance() {
        return INSTANCE;
    }

    public static final GeneralDataHolder INSTANCE = new GeneralDataHolder();
}
