package main.java.edu.neu.ccs.cs5010;

import java.util.*;

public class Recommendation {
  private Set<Integer> recommendedUsers;
  private int currCount;
  private Map<Integer, User> users;
  private int numberOfRecommendations;

  public Recommendation(Map<Integer, User> users, int numberOfRecommendations) {
    this.users = users;
    this.numberOfRecommendations = numberOfRecommendations;
  }

  //Find the recommended users by four criteria
  public Set<Integer> getRecommendation(int userId) {
    recommendedUsers = new HashSet<>();
    currCount = 0;
    criterionOne(userId);
    criterionTwo(userId);
    criterionThree(userId);
    criterionFour(userId);
    return recommendedUsers;
  }

  //Newbies mimic a friendliest friend,
  private void criterionOne(int userId){
    User currentUser = users.get(userId);

    Calendar thresholdDate = new GregorianCalendar(2017,10,1);
    String[] userDateArray = currentUser.getDateCreated().split("/");
    Calendar userDate = new GregorianCalendar(Integer.parseInt(
        userDateArray[2]),Integer.parseInt(userDateArray[1]),Integer.parseInt(userDateArray[0]));

    //user's created date is not after Oct 1st.
    if (!userDate.after(thresholdDate)) {
      return;
    }

    //pq sorted in descending order by following size
    PriorityQueue<User> pq = new PriorityQueue<>(new Comparator<User>() {
      @Override
      public int compare(User user1, User user2) {
        return user2.getFollowingList().size() - user1.getFollowingList().size();
      }
    });


    Set<Integer> thisUserFriends = currentUser.getFollowingList();  //this user's friends set
    for (int currId : currentUser.getFollowingList()) { //add every followings to the pq
      pq.add(users.get(currId)); //sort the current following by that user's friend list size
    }

    while (currCount < numberOfRecommendations && !pq.isEmpty()) {
      User currFriendUser = pq.poll();
      for (int currUserFriendId : currFriendUser.getFollowingList()) {
        if (!thisUserFriends.contains(currUserFriendId)) {
          recommendedUsers.add(currUserFriendId);
          currCount++;
          if (currCount == numberOfRecommendations) {
            return;
          }
        }
      }
    }
  }

  //Friend of a frend is a frend
  private void criterionTwo(int userId){
    // Check if we need to call this criterion
    if(this.currCount == numberOfRecommendations) {
      return;
    }

    User currentUser = users.get(userId);
    Set<Integer> thisUserFriends = currentUser.getFollowingList();//new HashSet<>(); //this user's friend set

    List<Integer> currentRecom = new ArrayList<>(); //recommended users in this criteria --> friends of friends w/o overlapping
    for (int currFollowingId : thisUserFriends) { //all the followings of this user
      for (int followingOfcurrFollowingId : users.get(currFollowingId).getFollowingList()) {
        if (!thisUserFriends.contains(followingOfcurrFollowingId) && !recommendedUsers.contains(followingOfcurrFollowingId)) {
          currentRecom.add(followingOfcurrFollowingId);
        }
      }
    }

    //if current recommended number is too large，sort by id
    if (currentRecom.size() + currCount > numberOfRecommendations){
      Collections.sort(currentRecom);
      int recomLeft = numberOfRecommendations - currCount;
      for (int i = 0; i < recomLeft; i++){
        recommendedUsers.add(currentRecom.remove(0));
        currCount++;
      }
    }

  }

  private void criterionThree(int curUserId) {
    // Check if we need to call this criterion
    if(this.currCount == numberOfRecommendations) {
      return;
    }

    // Get recommendation candidates from this criterion
    List<Integer> userIdsToRecommend = new ArrayList<>();
    for(Integer userId : users.keySet()) {
      User user = users.get(userId);
      if(user.getFollowerList().size() >= 25) {
        userIdsToRecommend.add(userId);
      }
    }
    // Add recommendation candidates to set
    int remain = numberOfRecommendations - this.currCount;
    Set<Integer> curFollowingList = users.get(curUserId).getFollowingList();
    for(int i = 0; i < userIdsToRecommend.size() && i < remain; i++) {
      int userId = userIdsToRecommend.get(i);
      if(!curFollowingList.contains(userId)) {
        this.recommendedUsers.add(userId);
        this.currCount++;
      }
    }
  }

  private void criterionFour(int curUserId) {
    // Check if we need to call this criterion
    if(this.currCount == numberOfRecommendations) {
      return;
    }
    Random random = new Random();
    int remain = numberOfRecommendations - this.currCount;
    Set<Integer> curFollowingList = users.get(curUserId).getFollowingList();
    for(int i = 0; i < remain; i++) {
      int randomId;
      while(true) {
        randomId = random.nextInt(users.size());
        if(!curFollowingList.contains(randomId) && recommendedUsers.contains(randomId) && randomId != curUserId) {
          break;
        }
      }
      this.recommendedUsers.add(randomId);
      this.currCount++;
    }
  }
}