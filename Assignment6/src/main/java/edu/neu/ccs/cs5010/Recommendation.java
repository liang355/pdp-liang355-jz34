package edu.neu.ccs.cs5010;

import java.util.*;

/**
 * Recommendation generates the recommendation users fot one users by four recommendation criteira
 */
public class Recommendation {
  private Set<Integer> recommendedUsers;
  private int currCount;
  private Map<Integer, User> users;
  private int numberOfRecommendations;

  /**
   * Constructor with users map and number of recommendation required.
   * @param users a users map, mapping user id to its account details.
   * @param numberOfRecommendations number of recommendation required.
   */
  public Recommendation(Map<Integer, User> users, int numberOfRecommendations) {
    this.users = users;
    this.numberOfRecommendations = numberOfRecommendations;
  }

  /**
   * getRecommendation finds the recommendation users by four criteria.
   * @param userId this user id.
   * @return a set of recommended users.
   */
  public Set<Integer> getRecommendation(int userId) {
    this.recommendedUsers = new HashSet<>();
    currCount = 0;
    criterionOne(userId);
    criterionTwo(userId);
    criterionThree(userId);
    criterionFour(userId);
    return recommendedUsers;
  }

  /**
   * Criterion One: Newbies Mimic a Friendliest Friend.
   * If some user has been a member of Twitter for a month or less, criterionOne finds this user’s friend
   * (a node that the user follows) with the largest number of friends (nodes that that nodes follows), and
   * uses those friends as recommendation for this user.
   * @param userId this user's id to get recommendations.
   */
  private void criterionOne(int userId) {
    User currentUser = users.get(userId);

    Calendar thresholdDate = new GregorianCalendar(2017, 9, 1);
    String[] userDateArray = currentUser.getDateCreated().split("/");
    Calendar userDate = new GregorianCalendar(Integer.parseInt(
        20 + userDateArray[2]), Integer.parseInt(userDateArray[0])-1, Integer.parseInt(userDateArray[1]));

    //Newbies check: user's created date is not after Oct 1st.
    if (!userDate.after(thresholdDate)) {
      return;
    }

    //pq sorted in descending order by following list size
    PriorityQueue<User> pq = new PriorityQueue<>(new Comparator<User>() {
      @Override
      public int compare(User user1, User user2) {
        return user2.getFollowingList().size() - user1.getFollowingList().size();
      }
    });

    Set<Integer> thisUserFriends = currentUser.getFollowingList();  //this user's friends set
    for (int currId : thisUserFriends) { //add every followings of this user to the pq
      pq.add(users.get(currId)); //sort the current following by that user's friend list size
    }

    //update on 11/09/17
    User friendliestFriend = pq.poll();

//    while (currCount < numberOfRecommendations && !pq.isEmpty()) {
//      //User currFriendUser = pq.poll();
    for (int currUserFriendId : friendliestFriend.getFollowingList()) {
      if (!thisUserFriends.contains(currUserFriendId)) {
        recommendedUsers.add(currUserFriendId);
        currCount++;
        if (currCount == numberOfRecommendations) {
          return;
        }
      }
    }

  }
  //}

  /**
   * Criterion Two: Friend of a Friend is a Friend.
   * criterionTwo picks friends of friends of this user as the recommendation of this user.
   * If the recommendation number is too large, the recommended users will be sorted by their userId.
   * @param userId this user's id to get recommendations.
   */
  private void criterionTwo(int userId) {
    // Check if we need to call this criterion
    if (this.currCount == numberOfRecommendations) {
      return;
    }

    User currentUser = users.get(userId);
    Set<Integer> thisUserFriends = currentUser.getFollowingList();//this user's friend set

    List<Integer> currentRecommendedList = new ArrayList<>(); //recommended users --> friends of friends w/o duplicates
    for (int currFollowingId : thisUserFriends) { //all the followings of this user
      for (int followingIdOfOneFollowing : users.get(currFollowingId).getFollowingList()) {
        if (!thisUserFriends.contains(followingIdOfOneFollowing) && !recommendedUsers.contains(followingIdOfOneFollowing)) {
          currentRecommendedList.add(followingIdOfOneFollowing);
        }
      }
    }


    if (currentRecommendedList.size() + currCount > numberOfRecommendations) { //if current recommended number is too large，sort by id
      Collections.sort(currentRecommendedList);
      int recommendNumLeft = numberOfRecommendations - currCount;
      for (int i = 0; i < recommendNumLeft; i++) {
        recommendedUsers.add(currentRecommendedList.remove(0));
      }
      currCount = recommendedUsers.size();
    } else { //if current recommendation number is not larger, add all of them to the recommendedList and update the currCount
      recommendedUsers.addAll(currentRecommendedList);
      currCount = recommendedUsers.size();
    }
  }

  /**
   * Criterion Three: Always Follow the Influencer.
   * criterionThree picks people with great number of followers as recommendations.
   * @param curUserId this user's id to get recommendations.
   */
  private void criterionThree(int curUserId) {
    // Check if we need to call this criterion
    if (this.currCount == numberOfRecommendations) {
      return;
    }

    // Get recommendation candidates from this criterion
    List<Integer> userIdsToRecommend = new ArrayList<>();
    for (Integer userId : users.keySet()) {
      User user = users.get(userId);
      //*************************************************************************************
      //***   more than 25 followers for nodes_small.csv, edges_small.csv                 ***
      //***   change to 250 for the regular data set (nodes_10000.csv, edges_10000.csv)   ***
      //*************************************************************************************
      if (user.getFollowerList().size() > 25) {
        userIdsToRecommend.add(userId);
      }
    }

    // Add recommendation candidates to set
    int remain = numberOfRecommendations - this.currCount;
    Set<Integer> curFollowingList = users.get(curUserId).getFollowingList();
    for (int i = 0; i < userIdsToRecommend.size() && i < remain; i++) {
      int userId = userIdsToRecommend.get(i);
      if (!curFollowingList.contains(userId)) {
        this.recommendedUsers.add(userId);
        this.currCount++;
      }
    }
  }

  /**
   * Criterion Four: When in Doubt, Branch Out.
   * criterionFour randomly picks someone in the social network as recommendations.
   * @param curUserId this user's id to get recommendations.
   */
  private void criterionFour(int curUserId) {
    // Check if we need to call this criterion
    if (this.currCount == numberOfRecommendations) {
      return;
    }

    Random random = new Random();
    int remain = numberOfRecommendations - this.currCount;
    Set<Integer> curFollowingList = users.get(curUserId).getFollowingList();
//    System.out.println(users.size());
//    System.out.println("---> userId: " + curUserId + " <---");
//    System.out.println("---> remain: " + remain + " <---");
//    System.out.println("size: " + curFollowingList.size() + ", " + Arrays.toString(curFollowingList.toArray()));
    for (int i = 0; i < remain && curFollowingList.size() + recommendedUsers.size() < users.size() - 1; i++) {
      int randomId;
      while (true) {
        randomId = random.nextInt(users.size()) + 1;
        if (!curFollowingList.contains(randomId) && !recommendedUsers.contains(randomId) && randomId != curUserId) {
          break;
        }
      }
      this.recommendedUsers.add(randomId);
//      System.out.print("[" + randomId + "," + recommendedUsers.size() + "]");
      this.currCount++;
    }
//    System.out.println();
//    System.out.println(Arrays.toString(this.recommendedUsers.toArray()));
  }
}