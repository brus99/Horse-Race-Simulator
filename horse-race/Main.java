import java.util.*;
class Main {
  public static void main(String[] args) {
    HashMap<String, Double> dailyBonus = new HashMap<String,Double>();
    HashMap<String, Double> horsePayoutBonus = new HashMap<String,Double>();
    HashMap<String, Integer> convertHorseNameToNumber = new HashMap<String,Integer>();
    Scanner scnr = new Scanner(System.in);
    double betAmount = 0;
    
    boolean validDay = false;
   


    theHouse blakeHorseRacing = new theHouse();
    Horse Betsy = new Horse();
    Horse Arrg = new Horse();
    Horse SeaBiscuit = new Horse();
    Horse Lucky = new Horse();
    player user = new player();

    Lucky.setHorseName("Lucky");
    Lucky.setHorseSpeed(3);
    Lucky.setHorsePosition(0);
    

    SeaBiscuit.setHorseName("SeaBiscuit");
    SeaBiscuit.setHorseSpeed(4);
    SeaBiscuit.setHorsePosition(0);
    

    Betsy.setHorseName("Betsy");
    Betsy.setHorseSpeed(7);
    Betsy.setHorsePosition(0);
    

    Arrg.setHorseName("Arrg");
    Arrg.setHorseSpeed(6);
    Arrg.setHorsePosition(0);
   

    dailyBonus.put("sunday",0.33);
    dailyBonus.put("monday",0.4);
    dailyBonus.put("tuesday",0.1);
    dailyBonus.put("wednesday",0.0);
    dailyBonus.put("thursday",0.1);
    dailyBonus.put("friday",0.2);
    dailyBonus.put("saturday",0.25);
    

    horsePayoutBonus.put("Betsy", 2.5);
    horsePayoutBonus.put("Arrg", 2.0);
    horsePayoutBonus.put("SeaBiscuit", 1.5);
    horsePayoutBonus.put("Lucky", 1.33);

    convertHorseNameToNumber.put("Betsy", 0);
    convertHorseNameToNumber.put("Arrg", 2);
    convertHorseNameToNumber.put("SeaBiscuit", 1);
    convertHorseNameToNumber.put("Lucky", 3);

    blakeHorseRacing.setBalance(50000.00);
    
    System.out.println("\nHi there, are you feeling lucky today. What is your name?\n");
    String playerName = scnr.nextLine();
    
    
    System.out.println("\nNice to meet you " + playerName + " how much are you playing with today? (Dollars and cents accepted)\n");

    boolean matchError = true;
    double playerAmount=0;
    do {
      try{
        playerAmount = scnr.nextDouble();
        matchError= false;
      }
      catch (Exception e) {
        System.out.println("\nThat is not a valid amount, try again (dollars and cents accepted)\n");
        scnr.nextLine();
        continue;
      }
    }while(matchError);

    scnr.nextLine();
    user.setBalance(playerAmount);
    System.out.println("\nYour Balance is: $" + user.getBalance());
    
    System.out.println();
    String today = "";

    while(!validDay) {
       System.out.println("Can you remind me what day it is (use lowercase). You get a promotional bonus added onto your bet!\n");
       today = scnr.nextLine();
       if (dailyBonus.containsKey(today)) {
         validDay = true;
       }
       else{
         System.out.println("\nThat is not a valid day, please try again\n");
       }
    }
    System.out.println("\nToday's promotional bonus is " + dailyBonus.get(today) + "\n");
    

    System.out.println("\nLet's get going then!");

    while (user.getBalance() > .0001) {
      
      boolean validHorse = false;
      String horseToBetOn = "";
      System.out.println("_______________________\n\nWhich horse would you like to place a bet on? (Caps sensitive)\n\n_______________OPTIONS_________________\n\nBetsy (Speed: very slow) | Pays 1:2.5 __|||__ Lucky (Speed: fast) | pays 1:1.3 __|||__ SeaBiscuit (Speed: Average) | pays 1:1.5 __|||_ Arrg (Speed: slow) | pays 1:2\n");

      while(!validHorse){
        
        horseToBetOn = scnr.nextLine();
        
        if(horsePayoutBonus.containsKey(horseToBetOn)){
          validHorse = true;
          System.out.println("\nYou have chosen to bet on " + horseToBetOn + "\n");
        }
        else {
          System.out.println("\nType the horse name below(caps sensitive)\n");
        }

      }
      

      System.out.println("How much would you like to bet? Dollars and cents are accepted.\n");
      boolean validBet = false;

      while(!validBet) { 
        betAmount = scnr.nextDouble();
        if(betAmount > user.getBalance()) { 
          System.out.println("\nYou can't bet more than you have!! Try again.\n");
        }
        else if (blakeHorseRacing.betHigherThanHouseBalance(betAmount) == true) {
          System.out.println("\nYou can't bet more than the house has! Times are tough! Think of the jockeys!\n");
        }
        else {
          validBet = true;
        }
      }
      
      double payoutTotal = dailyBonus.get(today) + horsePayoutBonus.get(horseToBetOn);
      double possibleWinnings = payoutTotal * betAmount;

      System.out.println("\nYour payout ratio is " + payoutTotal + "\n\n(" + today + "'s promotional bonus has been added to your payout)\n\nYou stand to win: $" + possibleWinnings + "\n\nAnd we are off to the races!\n");

      
      int[] currPositions = new int[4];
      
      
      boolean aHorseHasWon = false;
      int winningHorse = 0;
      while(!aHorseHasWon){
  
        Betsy.didTheHorseMove();
        currPositions[0] = Betsy.getHorsePosition();

        Arrg.didTheHorseMove();
        currPositions[1] = Arrg.getHorsePosition();

        SeaBiscuit.didTheHorseMove();
        currPositions[2] = SeaBiscuit.getHorsePosition();

        Lucky.didTheHorseMove();
        currPositions[3] = Lucky.getHorsePosition();

        findCurrentPositions(currPositions);
      
        if(Betsy.didTheHorseWin()==true){
          System.out.println("\nBetsy won!!!\n");
          winningHorse = 0;
          break;
        }
        
        else if(SeaBiscuit.didTheHorseWin()==true){
          System.out.println("\nSeaBiscuit won!!!\n");
          winningHorse = 1;
          break;
        }
       
        else if(Arrg.didTheHorseWin()==true){
          System.out.println("\nArrg won!!!\n");
          winningHorse = 2;
          break;
        }
        else if(Lucky.didTheHorseWin()==true){
          System.out.println("\nLucky won!!!\n");
          winningHorse = 3;
          break;
        }
      }

      double winningsOrLosses = payoutThePlayer(convertHorseNameToNumber.get(horseToBetOn), winningHorse, betAmount, payoutTotal);

      playerAmount = user.getBalance() + winningsOrLosses;
      user.setBalance(playerAmount);
      
      double houseMoney = blakeHorseRacing.getBalance() - winningsOrLosses;
      blakeHorseRacing.setBalance(houseMoney);


      System.out.println("Your new balance is: $" + playerAmount + "\n\nThe house has: $" + blakeHorseRacing.getBalance() + "\n");

      if(user.outOfMoney() == true) {
        System.out.println("\nToday was not your lucky day! You are all out of money, but are welcome to keep watching the horses!");
      }
    }

    scnr.close();
  }


  public static void findCurrentPositions(int[] positionArr) {
    //uses linear search since only 4 positions are taken
    HashMap<Integer, String> horsePlaces = new HashMap<Integer, String>();
    horsePlaces.put(0, "Betsy");
    horsePlaces.put(1, "Arrg");
    horsePlaces.put(2, "SeaBiscuit");
    horsePlaces.put(3, "Lucky");
    int currMax = 0;
    int indexHolder = 0;

    
    for (int i = 0; i < 4; ++i){
      if (positionArr[i] > currMax){
        currMax = positionArr[i];
        indexHolder = i;
      }

      System.out.println(horsePlaces.get(indexHolder) + " is in first place!\n");
      positionArr[indexHolder] = 0;

      try{
          Thread.sleep(750);
      }
        catch(Exception InterupptedException) {

      }
    }
  }


  public static double payoutThePlayer(int horseBetOn, int winningHorse, double amountBet, double horsePayoutRatio) {
    double winnings;
    if (horseBetOn == winningHorse) {
      winnings = amountBet * horsePayoutRatio;
    }
    else {
      winnings = -amountBet;
    }
    return winnings; 
  }

}
