import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class AddressHandler {
    private  JSONArray jsonArray;

    public AddressHandler(String addressesFilePath) {
        try {
            jsonArray = (JSONArray) new JSONParser().parse(new FileReader(addressesFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void prettyPrintAllAddresses(){
        for (Object a:jsonArray){
            Address address = new Address((JSONObject) a);
            System.out.println(address.prettyPrintAddress());
        }
    }

    public void printAddressByTypeCode(int typeCode){
      for (Object a:jsonArray){
            Address address = new Address((JSONObject) a);

            if (address.getTypeCode() == typeCode){
                System.out.println(address.prettyPrintAddress());
            }
        }
    }

    public void validateAddresses(){
        for (Object a:jsonArray){
            Address address = new Address((JSONObject) a);

            address.validate();
        }
    }
}
