package interview.paylocity.backend.general.auxiliary;

import interview.paylocity.backend.settings.base.TestConfig;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class EmployeeAuxiliaryMethods extends TestConfig {

    protected String prepareUserJsonBody(List<Map<String, String>> userTable, String userTableKeyId) {
        String firstName = null, lastName = null, dependants = null;

        for (Map<String, String> map : userTable) {
            if (userTableKeyId.equals(map.get("id"))) {
                firstName = map.get("firstName");
                lastName = map.get("lastName");
                dependants = map.get("dependants");
                break;
            }
        }

        if (firstName == null) throw new IllegalArgumentException("It was not possible to load key for userTable data table!");
        JSONObject jsonObject = new JSONObject();
        jsonObject = prepareCoreUserJsonBody(jsonObject, firstName, lastName, dependants);

        return jsonObject.toString();
    }

    protected String prepareUpdateUserJsonBody(List<Map<String, String>> userTable, String userTableKeyId, String userId) {
        String firstName = null, lastName = null, dependants = null;

        for (Map<String, String> map : userTable) {
            if (userTableKeyId.equals(map.get("id"))) {
                firstName = map.get("firstName");
                lastName = map.get("lastName");
                dependants = map.get("dependants");
                break;
            }
        }

        if (firstName == null) throw new IllegalArgumentException("It was not possible to load key for userTable data table!");
        JSONObject jsonObject = new JSONObject();
        jsonObject = prepareJsonObjectField(jsonObject, "id", userId);
        jsonObject = prepareCoreUserJsonBody(jsonObject, firstName, lastName, dependants);

        return jsonObject.toString();
    }

    private JSONObject prepareCoreUserJsonBody(JSONObject jsonObject, String firstName, String lastName, String dependants) {
        jsonObject = prepareJsonObjectField(jsonObject, "firstName", firstName);
        jsonObject = prepareJsonObjectField(jsonObject, "lastName", lastName);
        jsonObject = prepareIntegerJsonObjectField(jsonObject, "dependants  ", dependants);

        return jsonObject;
    }

}
