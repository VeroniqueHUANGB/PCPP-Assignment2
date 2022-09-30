package exercises03;

public class Person {
    private long id = 0;
    private String name = "Default Name";
    private int zip = 0;
    private String address = "Default Address";
    private static int idCounter = 0;

    public Person(){
        synchronized (Person.class){
            this.id = idCounter;
            idCounter++;
        }
    }
    public Person(int initialId){
        synchronized (Person.class){
            if (idCounter == 0){
                this.id = initialId;
                idCounter = initialId + 1;
            }
            else{
                this.id = idCounter;
                idCounter++;
            }
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public void setZipAddress(int zip, String address){
        this.zip = zip;
        this.address = address;
    }

    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getZip(){
        return zip;
    }
    public String getAddress(){
        return address;
    }


    public static void main(String[]args){
        class PersonTest extends Thread{
            public void run(){
                Person person1 = new Person();
                System.out.println("Person1 id: " + person1.getId());
                person1.setName("Bihu");
                person1.setZipAddress(100, "addr1");
                Person person2 = new Person(5);
                System.out.println("Person2 id: " + person2.getId());
                person2.setName("Liz");
                person2.setZipAddress(200, "addr2");
            }
        }

        for (int i = 0; i < 100; i++){
            new PersonTest().start();
        }
    }
}
