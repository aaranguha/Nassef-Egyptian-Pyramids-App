package com.example;

// pyramid class, that corresponds to the information in the json file
public class Pyramid {

  protected Integer id;
  protected String name;
  protected Object[] contributors;

  // constructor
  public Pyramid(
    Integer pyramidId,
    String pyramidName,
    Object[] pyramidContributors
  ) {
    id = pyramidId;
    name = pyramidName;
    contributors = pyramidContributors;
  }
  // print pyramid
  public void print() {
    System.out.printf("Pyramid %s\n", name);
    System.out.printf("\tid: %d\n", id);
    for (int i = 0; i < contributors.length; i++){
      Object cont = contributors[i];
      System.out.printf("\tContributors: %s\n", cont);
    }
  }
  public String getname(){
    return name;
  }
  public int get_id(){
    return id;
  }
  public Object get_cont(){
    return contributors;
  }
    

}
