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
    System.out.printf("Pharaoh %s\n", name);
    System.out.printf("\tid: %d\n", id);
    System.out.printf("\tContributors: %s\n", contributors);
  }

}
