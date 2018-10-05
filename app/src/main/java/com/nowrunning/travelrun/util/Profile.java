package com.nowrunning.travelrun.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;

import java.util.ArrayList;

/**
 * Created by Austin on 1/15/2018.
 */

public class Profile {
    //question about cicles - do you want it to be a relation between two people,
    // or do you want it to be a group of people clustered together?

    private String name;
    private int age;
    private String home;
    private String nationality;
    //String profession;
    private String whatsUp;
    private String bio;
    private String eMail;
    private String uniqueID;
    private int mainImage;
    private int ageRNG;
    private int distanceRNG;


    private Location location;


    private ArrayList<Long> friendID;
    private ArrayList<String> pictNames;


    private ArrayList<Integer> pictList;

    private ArrayList<Circle> circList;
    private ArrayList<byte[]> bytePicList;
    private ArrayList<String> picPath;

    private ArrayList<Bitmap> pictBMP;


    //pictures that a person has
    //group of access stuff.

    public Profile(){
        this.name = "";
        this.age = 0;
        this.home = "";
        this.nationality = "";
        //this.profession = "";
        this.whatsUp = "";
        this.bio = "";
        this.uniqueID = "";
        this.eMail = "";
        this.pictList = new ArrayList<Integer>();
        this.friendID = new ArrayList<>();
        this.circList = new ArrayList<>();
        this.pictNames = new ArrayList<>();
        this.bytePicList = new ArrayList<>();
        this.picPath = new ArrayList<>();
        this.pictBMP = new ArrayList<>();
        this.ageRNG = 0;
        this.distanceRNG = 0;
    }

    public Profile(String inName, int inAge){
        this.name = inName;
        this.age = inAge;
        this.home = "";
        this.nationality = "";
        //this.profession = "";
        this.whatsUp = "";
        this.bio = "";
        this.uniqueID ="";
        this.eMail = "";
        this.pictList = new ArrayList<Integer>();
        this.friendID = new ArrayList<>();
        this.circList = new ArrayList<>();
        this.pictNames = new ArrayList<>();
        this.bytePicList = new ArrayList<>();
        this.picPath = new ArrayList<>();
        this.pictBMP = new ArrayList<>();
        this.ageRNG = 0;
        this.distanceRNG = 0;
    }

    public Profile(String inName, int inAge, String inHome, String inNat, String inProf, String inEdu){
        this.name = inName;
        this.age = inAge;
        this.home = inHome;
        this.nationality = inNat;
        //this.profession = inProf;
        this.whatsUp = "";
        this.bio = "";
        this.eMail = "";
        this.uniqueID = "";
        this.pictList = new ArrayList<Integer>();
        this.friendID = new ArrayList<>();
        this.circList = new ArrayList<>();
        this.pictNames = new ArrayList<>();
        this.bytePicList = new ArrayList<>();
        this.picPath = new ArrayList<>();
        this.pictBMP = new ArrayList<>();
        this.ageRNG = 0;
        this.distanceRNG = 0;
    }

    public Profile(String inName, int inAge, String inHome, String inNat, String inProf, String inWhat, String inBio){
        this.name = inName;
        this.age = inAge;
        this.home = inHome;
        this.nationality = inNat;
        //this.profession = inProf;
        this.whatsUp = inWhat;
        this.bio = inBio;
        this.eMail = "";
        this.uniqueID = "";
        this.pictList = new ArrayList<Integer>();
        this.friendID = new ArrayList<>();
        this.circList = new ArrayList<>();
        this.pictNames = new ArrayList<>();
        this.bytePicList = new ArrayList<>();
        this.picPath = new ArrayList<>();
        this.pictBMP = new ArrayList<>();
        this.ageRNG = 0;
        this.distanceRNG = 0;
    }

    public Profile(String inName, int inAge, String inHome, String inNat, String inProf, String inWhat, String inBio, ArrayList<Integer> inPict){
        this.name = inName;
        this.age = inAge;
        this.home = inHome;
        this.nationality = inNat;
        //this.profession = inProf;
        this.whatsUp = inWhat;
        this.bio = inBio;
        this.eMail = "";
        this.uniqueID = "";
        this.pictList = inPict;
        this.friendID = new ArrayList<>();
        this.circList = new ArrayList<>();
        this.pictNames = new ArrayList<>();
        this.bytePicList = new ArrayList<>();
        this.picPath = new ArrayList<>();
        this.pictBMP = new ArrayList<>();
        this.ageRNG = 0;
        this.distanceRNG = 0;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String inName){
        this.name = inName;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int inAge){
        this.age = inAge;
    }

    public String getHome(){
        return this.home;
    }

    public void setHome(String newHome){
        this.home = newHome;
    }

    public String getNationality(){
        return  this.nationality;
    }

    public void setNationality(String newNationality){
        this.nationality = newNationality;
    }

    /*public String getProfession(){
        return this.profession;
    }

    public void setProfession(String newProfession){
        this.profession = newProfession;
    }*/



    public String getWhatsUp(){
        return this.whatsUp;
    }

    public void setWhatsUp(String newWhats){
        this.whatsUp = newWhats;
    }

    public String getBio(){
        return this.bio;
    }

    public void setBio(String newBio){
        this.bio = newBio;
    }

    public ArrayList<Integer> getPictList(){
        return this.pictList;
    }

    public void setPictList(ArrayList<Integer> inPictList){
        this.pictList = inPictList;
    }

    public void setMainImage(int inImage){
        this.mainImage = inImage;
    }

    public int getMainImage(){
        return this.mainImage;
    }

    public void addPic(int newPic) {
        pictList.add(new Integer(newPic));
    }

    public void addFriend(long inID){
        this.friendID.add(new Long(inID));
    }
    public ArrayList<Long> getFriendID(){
        return this.friendID;
    }

    public void addCircle(Circle inCirc){
        this.circList.add(inCirc);
    }

    public ArrayList<Circle> getCircList() {
        return circList;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<String> getPictNames(){
        return this.pictNames;
    }

    public void addNewPictName(String inPict){
        pictNames.add(inPict);
    }

    public ArrayList<byte[]> getBytePicTist(){
        return bytePicList;
    }

    public void addPic(byte[] bytes) {
        //bytePicList.add(bytes);
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        addBMP(bmp);
    }

    public void addPicPath(String absolutePath) {
        picPath.add(absolutePath);
    }

    public ArrayList<String> getPicPath(){
        return this.picPath;
    }

    public void addBMP(Bitmap bmp) {
        this.pictBMP.add(bmp);
    }

    public ArrayList<Bitmap> getPictBMP(){
        return this.pictBMP;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getAgeRNG() {
        return ageRNG;
    }

    public void setAgeRNG(int ageRNG) {
        this.ageRNG = ageRNG;
    }

    public int getDistanceRNG() {
        return distanceRNG;
    }

    public void setDistanceRNG(int distanceRNG) {
        this.distanceRNG = distanceRNG;
    }
}