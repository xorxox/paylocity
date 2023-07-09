package interview.paylocity.frontend.settings.dataholder;

public class GeneralDataHolder {

    // Data Holders

    public String sharedFirstName;

    public String sharedLastName;

    public String sharedDependants;

    // Auxiliary Methods

    public static GeneralDataHolder getInstance() {
        return INSTANCE;
    }

    public static final GeneralDataHolder INSTANCE = new GeneralDataHolder();
}
