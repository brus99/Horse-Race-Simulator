public class theHouse extends player {

  public boolean betHigherThanHouseBalance(double playerBet) {
    if(playerBet > this.balance) {
      return true;
    }
    else {
      return false;
    }
  }
  
}