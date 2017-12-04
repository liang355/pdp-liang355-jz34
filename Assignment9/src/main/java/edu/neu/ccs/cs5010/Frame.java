package edu.neu.ccs.cs5010;

/**
 * Frame is an object to represents a tag and a payload for
 * the game protocol of this client-server application.
 */
public class Frame {
  private String tag;
  private String payload;

  /**
   * Constructs a new frame with two empty arguments.
   */
  public Frame() {
    this("", "");
  }

  /**
   * Constructs a new frame with given tag and given payload.
   * @param tag given tag.
   * @param payload given payload.
   */
  public Frame(String tag, String payload) {
    this.tag = tag;
    this.payload = payload;
  }

  /**
   * Getter method for the tag.
   * @return the String tag.
   */
  public String getTag() {
    return tag;
  }

  /**
   * Setter method for the tag.
   * @param tag to be set.
   */
  public void setTag(String tag) {
    this.tag = tag;
  }

  /**
   * Getter method for the payload.
   * @return the String payload.
   */
  public String getPayload() {
    return payload;
  }

  /**
   * Setter method for the payload.
   * @param payload to be set.
   */
  public void setPayload(String payload) {
    this.payload = payload;
  }

  /**
   * Overrides the toString() method.
   * @return a String representation of the Frame.
   */
  @Override
  public String toString() {
    return tag + ":" + payload;
  }
}
