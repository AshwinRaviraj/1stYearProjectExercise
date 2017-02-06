import java.util.*;
import java.util.regex.*;

public class Address {
    private final String street, house, floor, side, postcode, city;

    private Address(String _street, String _house, String _floor, String _side, String _postcode, String _city) {
        street = _street;
        house = _house;
        floor = _floor;
        side = _side;
        postcode = _postcode;
        city = _city;
    }

    public String toString(){
        return street + " " + house + "\n" +
                postcode + " " + city;
    }

    public static class Builder {
        private String street = "Unknown", house, floor, side, postcode, city;
        public Builder street(String _street) { street = _street; return this; }
        public Builder house(String _house)   { house = _house;   return this; }
        public Builder floor(String _floor)   { floor = _floor;   return this; }
        public Builder side(String _side)     { side = _side;     return this; }
        public Builder postcode(String _postcode) { postcode = _postcode; return this; }
        public Builder city(String _city)     { city = _city;     return this; }
        public Address build() {
            return new Address(street, house, floor, side, postcode, city);
        }
    }

    public String street()   { return street; }
    public String house()    { return house; }
    public String floor()    { return floor; }
    public String side()     { return side; }
    public String postcode() { return postcode; }
    public String city()     { return city; }

    private final static String REGEX1 = "(?<Street>[A-Za-zæøåÆØÅ ]+) (?<house>[0-9]+), (?<postcode>[0-9]{4}) (?<city>[A-Za-zæøåÆØÅ ]+)";
    private final static String REGEX2 = "(?<Street>[A-Za-zæøåÆØÅ ]+) (?<house>[0-9]+)";
    private final static Pattern PATTERN1 = Pattern.compile(REGEX1);
    private final static Pattern PATTERN2 = Pattern.compile(REGEX2);

    /*
    Kig på regulære udtryk og ændre de ovenstående og nedenstående statics og find bedre metoder og navngivninger.
    Lav evt. et while loop til at køre hele tekstfilen igennem og se på de forskellige patterns.
    */


    public static Address parse(String s) {
        Builder b = new Builder();
        Matcher matcher = PATTERN1.matcher(s);
        if(matcher.matches()){
            b.street(matcher.group("Street"))
                    .house(matcher.group("house"))
                    .postcode(matcher.group("postcode"))
                    .city(matcher.group("city"));
        }
        matcher = PATTERN2.matcher(s);
        if(matcher.matches()){
            b.street(matcher.group("Street"))
                    .house(matcher.group("house"));
        }
        return b.build();
    }
}
