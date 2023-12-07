package hellojpa;

import javax.persistence.Embeddable;

@Embeddable //값타입이라는 것을 알려주기 위한 어노테이션
public class Address {
    //주소로 묶고 싶을 경우
    private String city;
    private String street;
    private String zipcode;

    public Address(){

    }

    public Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }



}
