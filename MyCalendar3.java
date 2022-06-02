
import java.util.*;
import java.io.*;
import java.util.Calendar;
import java.io.PrintStream;

public class MyCalendar3{
   public static final int SIZE = 25;
   public static final int DAY = 6;
   public static String[][] eventArray = new String[12][];
   public static void main(String[] args) throws FileNotFoundException{
      intro();
      Scanner input = new Scanner(System.in);
      Calendar calendar = Calendar.getInstance();
      String command = menuCommand(input);
      int month = 0;
      int day = 0;
      fileReading("calendarEvents.txt");
      // while loop command
      while (!command.equals("q")) { 
         if (command.equals("e")){            
            System.out.print("What date would you like to see? enter by mm/dd "); 
            String date = input.next();
            artFlower();
            month = monthFromDate(date);
            day = dayFromDate(date);
            System.out.println();
            drawMonth(month,day);
            displayDate(month, day);
         } else if (command.equals("ev")){// event planner command
            System.out.println("Enter a date and event to edit: MM/DD event_title "); 
            String date = input.next();
            String event = input.next();
            artFlower();
            month = monthFromDate(date);
            day = dayFromDate(date);
            event = event.replace("_", " ");
            eventArray [month - 1][day - 1] = event;
            drawMonth(month, day);
            displayDate(month, day);
         }else if (command.equals("t")){// today's date
            System.out.println();
            artFlower();
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DATE);
            System.out.println();
            drawMonth(month, day);
            displayDate(month, day);
         }else if(command.equals("fp")) { // file printing command
            System.out.print("Please enter a month: ");
            String date = input.next();
            month = monthFromDate(date);
            day = dayFromDate(date);
            System.out.print("What file name would you like to give to print to? ");
            PrintStream output = new PrintStream(new File(input.next()));
            PrintStream name = System.out;
            System.setOut(output);
            artFlower();
            drawMonth(month, day);
            displayDate(month, day);
            System.setOut(name);
         }else if(command.equals("n")){// next month
            if (month == 0){
               System.out.println("Please select command \"e\" or \"t\" first");
            } else if (month == 12){
               month = 0;
               month++;
               System.out.println();
               artFlower();
               drawMonth(month, day);
               displayDate(month, day);
            } else {
               month++;
               System.out.println();
               artFlower();
               drawMonth(month,day);
               displayDate(month, day);
            }
         }else if (command.equals("p")){//previous month
            if (month == 0){
               System.out.println("Please select command \"e\" or \"t\" first");
            }   else if (month == 1){
               month = 13;
               month--; 
               System.out.println();
               artFlower();
               drawMonth(month,day);
               displayDate(month, day);
            } else {
               month--;
               System.out.println();
               artFlower();
               drawMonth(month,day);
               displayDate(month, day);
            }
         }else {
            System.out.println("Please select a valid command: ");
         }
         command = menuCommand(input);
      } 
       
   }
   
   public static void drawMonth(int month, int day){
      for (int i = 1; i <= (SIZE * 7 +8) / 2; i++){
         System.out.print(" ");
      }
      System.out.print(month);
      System.out.println();
      weekOfDay();
      System.out.println();
      int number = 5; 
      for (int i = 1; i <= number; i++){
         number = drawRow(i, month, day);
      }
      drawBottom();
   }// end of drawMonth
  
   public static int drawRow(int row, int monthI, int day1){
      int p = 0; 
      for (int rowDivder = 1; rowDivder <= SIZE * 7 + 8 ; rowDivder++){
         System.out.print("=");
      }
      System.out.println();
      int x = printMonth(monthI);
      int y = month(monthI);
      int day = 0;
      for (int i = 7 * row - 6 ; i <= 7 * row  ; i++) {
         if (i >= x + 1 && day < y){
            day = i - x;
            System.out.print("|" + day);
            int space = Integer.toString(day).length();
            if (day == day1){
               System.out.print("*"); 
               space++;
            }
            String s = "";
            if (!(eventArray[monthI - 1][day - 1] == null)){
               s = eventArray[monthI - 1][day -1];
               System.out.print(eventArray[monthI - 1][day -1]);
            }
            int sL = s.length(); 
            for (int realSpace =1; realSpace <= SIZE - space -sL; realSpace++){
               System.out.print(" "); 
            }
         } else {
            System.out.print("|");
            for (int space = 1; space <= SIZE; space++){
               System.out.print(" ");
            }
         } 
      }// end of the line loop 
      System.out.println("|");
      for (int k = 1; k <=(SIZE - 2) / 2; k++){
         for (int slash = 1;  slash <= 7; slash++){
            System.out.print("|");
            for (int space = 1; space <= SIZE; space++){
               System.out.print(" ");
            }
         }
         System.out.println("|");
      } 
      if (day < y && row ==5){
         return 6;
      } 
      return 5;
   } // end of the method 
    
   public static void drawBottom(){
      for (int rowDivder = 1; rowDivder <= SIZE * 7 + 8 ; rowDivder++){
         System.out.print("=");
      }
   }// end of drawBottom
        
   public static void displayDate(int month, int day){ //display month and date
      System.out.println();
      System.out.print("Month: " + month);
      System.out.println();
      System.out.print("Date: " + day);
      System.out.println();
   }// end of displayDate
   
   public static int monthFromDate(String date) {
      int M = Integer.parseInt(date.substring(0,2));
      return M; 
   }
 
   public static int dayFromDate(String date){
      int D = Integer.parseInt(date.substring(3,5));
      return D; 
   }
   
   public static void weekOfDay(){
      for (int i = 1; i <= 1; i++){ // days from Sun to Sat
         printDate(" ", SIZE/2);
         printDate("SUN", 1);
         printDate(" ", 5 * SIZE / 6);
         printDate("MON", 1);
         printDate(" ", 5 * SIZE / 6);
         printDate("TUE", 1);
         printDate(" ", 5 * SIZE / 6);
         printDate("WED", 1);
         printDate(" ", 5 * SIZE / 6);
         printDate("THU", 1);
         printDate(" ", 5 *SIZE / 6 );
         printDate("FRI", 1);
         printDate(" ", 5 * SIZE / 6);
         printDate("SAT", 1);
         printDate(" ", SIZE/3);
      }
   }
   
   public static void printDate(String s, int number ){// method print Sun to Mon
      for (int i = 1; i <= number; i++){ 
         System.out.print(s);
      }
   }
      
   public static int printMonth(int monthI ){
      int total = DAY;
      for(int i = 1; i < monthI; i++){
         total = (total + month(i));
      }
      if(total == DAY){
         return total;
      }
      return total % 7;
   }
         
   public static int month(int Month){ // days in the month
      if (Month == 2){
         return 28;
      } else if (Month == 4 || Month == 6 || Month == 9 || Month == 11){
         return 30;
      } else {
         return 31;
      }
   }
   
   public static String menuCommand(Scanner input){// command options
      System.out.print("\nPlease enter a command:\n");
      System.out.print("\t\tPress \"e\" to enter a date and display corrsponding calendar\n");
      System.out.print("\t\tPress \"ev\" to enter an event\n");
      System.out.print("\t\tPress \"fp\" to print calendar to file\n");
      System.out.print("\t\tPress \"t\" to enter today's date and display the current day\n");
      System.out.print("\t\tPress \"n\" to enter next month\n");
      System.out.print("\t\tPress \"p\" to display the previous month\n");
      System.out.print("\t\tPress \"q\" to quit the program\n");
      String command = input.nextLine(); 
      return command; 
   }
   
   public static void fileReading(String files) throws FileNotFoundException{
      eventArray = new String[12][];
      for (int i = 0; i < 12; i++){// event array 
         eventArray[i] = new String [month(i + 1 )];
      }
      Scanner input = new Scanner(new File(files));
      while (input.hasNextLine()){// file reading
         String date = input.next();
         String event = input.next();
         int day = dayFromDate(date);
         int month = monthFromDate(date); 
         event = event.replace("_", " ");
         eventArray [month - 1][day - 1] = event;
      }
   }
    
   public static void intro(){// introduction about the program.
      System.out.println("This program has various command to work with");
      System.out.println("The command is at your hand. ");
   }
      
   public static void artFlower(){// AsciiArt 
      for (int j = 1; j <= 6; j++)
         System.out.print(" ");
      for (int k = 1; k <= 5; k++)
         System.out.print("W");
      for(int h = 1; h <= 15; h++)
         System.out.print(" ");
      for (int k = 1; k <= 5; k++)
         System.out.print("W");
      System.out.println();
      for (int o = 1; o <= 5; o++)
         System.out.print("V");
      System.out.print(" ");
      System.out.print("(___)");
      System.out.print(" ");
      for (int j = 1; j <= 5; j++)
         System.out.print("W");
      for (int h = 1; h <= 9; h++)
         System.out.print(" ");
      System.out.print("(___)");
      System.out.print("  ");
      for (int j = 1; j <= 5; j++)
         System.out.print("V");
      System.out.println();
      System.out.print("(___)");
      System.out.print("  ");
      System.out.print("~Y~");
      for (int g = 1; g <= 2; g++)
         System.out.print(" ");
      System.out.print("(___)");
      for (int t = 1; t <= 2; t++){
         System.out.print(" ");
      }
      for (int h = 1; h <= 5; h++)
         System.out.print("V");
      for (int r = 1; r <= 3; r++)
         System.out.print(" ");
      System.out.print("~Y~");
      for (int q = 1; q <= 3; q++)
         System.out.print(" ");
      System.out.println("(___)");
      System.out.print(" ");
      System.out.print("~Y~");  
      for ( int e = 1; e <= 3; e++){
         System.out.print(" ");
      }
      System.out.print("\\|");
      for (int p = 1; p <= 4; p++){
         System.out.print(" ");
      }
      System.out.print("~Y~");
      for (int v = 1; v <= 3; v++){
         System.out.print(" ");
      }
      System.out.print("(___)");
      for (int a = 1; a <= 4; a++){
         System.out.print(" ");
      }
      System.out.print("|/");
      for (int w = 1; w <= 4; w++){
         System.out.print(" ");
      }
      System.out.println("~Y~");
      System.out.print(" ");
      System.out.print("\\|");
      for (int space = 1; space <= 3; space++){
         System.out.print(" ");
      }
      System.out.print("\\ |/");
      for (int v = 1; v <= 3; v++){
         System.out.print(" ");
      }
      System.out.print("\\| /");
      for (int l = 1; l <= 2; l++){
         System.out.print(" ");
      }
      System.out.print("\\~Y~/");
      for (int d = 1; d <= 3; d++){
         System.out.print(" ");
      }
      System.out.print("\\|");
      for (int c = 1; c <= 4; c++){
         System.out.print(" ");
      }
      System.out.print("\\ |/");
      System.out.println();     
      for (int sign = 1; sign <= 2; sign++){
         System.out.print("\\\\|//");
         System.out.print(" ");
      }
      System.out.print("\\\\|//");
      System.out.print("  ");
      System.out.print("\\\\|//");
      for (int s = 1; s <= 2; s++){
         System.out.print(" ");
      }
      System.out.print("\\\\|//");            
      System.out.print(" ");
      System.out.println("\\\\\\|///");
      for (int line = 1; line <= 40; line++){
         System.out.print("^");
      }
      System.out.println();
   }
}