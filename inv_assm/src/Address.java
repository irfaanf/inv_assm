import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

public class Address {

    private String id;

    private JSONObject type;

    private int typeCode;

    private String typeName;

    private JSONObject addressLineDetail;

    private String addressLine1;

    private String addressLine2;

    private JSONObject provinceOrState;

    private String provinceCode;

    private String provinceName;

    private String cityOrTown;

    private JSONObject country;

    private String countryCode;

    private String countryName;

    private String postalCode;

    private String lastUpdated;

    private String suburbOrDistrict;

    public Address(JSONObject address) {
        this.id = (String) address.get("id");

        this.type = (JSONObject) address.get("type");
        this.typeCode = Integer.parseInt((String) type.get("code"));
        this.typeName = (String) type.get("name");

        this.addressLineDetail = (JSONObject) address.get("addressLineDetail");
        this.addressLine1 = (addressLineDetail == null) ? "" : StringUtils.defaultString((String) addressLineDetail.get("line1"));
        this.addressLine2 = (addressLineDetail == null) ? "" : StringUtils.defaultString((String) addressLineDetail.get("line2"));

        this.provinceOrState = (JSONObject) address.get("provinceOrState");
        this.provinceCode = (provinceOrState == null) ? "" : StringUtils.defaultString((String) provinceOrState.get("code"));
        this.provinceName = (provinceOrState == null) ? "" : StringUtils.defaultString((String) provinceOrState.get("name"));

        this.cityOrTown = (String) address.get("cityOrTown");

        this.country = (JSONObject) address.get("country");
        this.countryCode = (country == null) ? "" : StringUtils.defaultString((String) country.get("code"));
        this.countryName = (country == null) ? "" : StringUtils.defaultString((String) country.get("name"));

        this.postalCode = (String) address.get("postalCode");

        this.lastUpdated = (String) address.get("lastUpdated");

        this.suburbOrDistrict = (String) address.get("suburbOrDistrict");
    }

    public String prettyPrintAddress(){
        String addLine1TrailingSpace = addressLine1.equals("") == true ? addressLine1 : addressLine1 + " ";
        String addLine2TrailingSpace = addressLine2.equals("") == true ? addressLine2 : addressLine2 + " ";

        String provinceCodeTrailingSpace = provinceCode.equals("") == true ? provinceCode : provinceCode + " ";
        String provinceNameTrailingSpace = provinceName.equals("") == true ? provinceName : provinceName + " ";

        return typeCode + " " + typeName + ": " + addLine1TrailingSpace + addLine2TrailingSpace + "- " + cityOrTown + " - " + provinceCodeTrailingSpace + provinceNameTrailingSpace + "- " + postalCode + " - " + countryCode + " " + countryName;
    }

    public int getTypeCode(){
        return this.typeCode;
    }

    public boolean validate(){
        boolean isValid = true;

        try {
            Integer.parseInt(postalCode);

            if (countryName.equals("")){
                isValid = false;
                throw  new InvalidAddressException("Address ID " + id + ": Invalid Country Name");
            } else if (addressLine1.equals("") && addressLine2.equals("")) {
                isValid = false;
                throw new InvalidAddressException("Address ID " + id + ": Invalid Address Line");
            } else if (countryCode.equals("ZA") && provinceName.equals("")) {
                isValid = false;
                throw new InvalidAddressException("Address ID " + id + ": Invalid Province");
            }
        } catch (NumberFormatException nfe){
            isValid = false;
            throw new InvalidAddressException("Address ID " + id + ": Invalid Postal Code");
        }

        return isValid;
    }
}
