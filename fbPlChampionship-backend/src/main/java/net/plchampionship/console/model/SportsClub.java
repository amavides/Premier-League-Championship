package net.plchampionship.console.model;

import org.springframework.stereotype.Component;

@Component
class SportsClub  {
    private String nameOfClub;
    private String locationOfClub;
    private String typeOfClub;


    public  SportsClub(String nameOfClub,String locationOfClub,String typeOfClub){
        this.nameOfClub = nameOfClub;
        this.locationOfClub = locationOfClub;
        this.typeOfClub=typeOfClub;
    }

    public SportsClub() {

    }

    public String getNameOfClub() {
        return nameOfClub;
    }

    public void setNameOfClub(String nameOfClub) {
        this.nameOfClub = nameOfClub;
    }

    public String getLocationOfClub() {
        return locationOfClub;
    }

    public void setLocationOfClub(String locationOfClub) {
        this.locationOfClub = locationOfClub;
    }

    public String getTypeOfClub() {
        return typeOfClub;
    }

    public void setTypeOfClub(String typeOfClub) {
        this.typeOfClub = typeOfClub;
    }
}
