import java.util.Random;
public class Horse {
  private String horseName; 
  private int horseSpeed;
  private int horsePosition;

  public void setHorseName(String name) {
    this.horseName = name;
  }

  public String getHorseName() {
    return horseName;
  }

  public void setHorsePosition(int position) {
    this.horsePosition = position;
  }

  public int getHorsePosition() {
    return this.horsePosition;
  }

  public void setHorseSpeed(int Speed){
    this.horseSpeed = Speed;
  }

  public void didTheHorseMove () {
    Random movementSimulator = new Random();
    int movementValue = movementSimulator.nextInt(horseSpeed);

    if (movementValue == 2) {
      this.horsePosition += 1;
    }
  }
  
  public boolean didTheHorseWin() {
    if (horsePosition == 4) {
      return true;
    }
    else {
      return false;
    }
  }
  

}