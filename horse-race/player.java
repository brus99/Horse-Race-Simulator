public class player {
  public double balance;

  public void setBalance(double money) {
    this.balance = money;
  }

  public double getBalance() {
    return this.balance;
  }

  public boolean outOfMoney() {
    if (this.balance < .001) {
      return true;
    }
    else {
      return false;
    }
  }

}