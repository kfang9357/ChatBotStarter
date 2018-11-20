import java.util.Scanner;
import java.util.Random;
//separate array with prices aligned with names of dishes
//cheap flavor etc
/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Kelly Fang
 * @version September 2018
 */
public class ChatBot3 {

    String [] order = {};
    String testItem = "";

    boolean foundItem = false;

    String[] dessertOfTheNight = {"Chocolate Soufle",
            "Fruit Tart",
            "Macaroons",
            "Tiramisu",
            "Cake",
            "Ice cream",
    };

    String[] soupOfTheDay = {"Clam Chowder",
            "Tomato Soup",
            "French Onion Soup",
            "Chicken Noodle Soup",
            "Lentil Soup",
            "Cauliflower Soup",
    };

    String[] chefSpecial = {"Filet Mignon",
            "Beef Wellington",
            "Butter Chicken",
            "Stuffed Pork Tenderloins",
            "Grilled Salmon",
    };

    String[] completeMenu = {"Clam Chowder",
            "Tomato Soup",
            "French Onion Soup",
            "Chicken Noodle Soup",
            "Lentil Soup",
            "Cauliflower Soup",
            "Beef Wellington",
            "Butter Chicken",
            "Stuffed Pork Tenderloins",
            "Grilled Salmon",
            "Fruit Tart",
            "Macaroons",
            "Tiramisu",
            "Cake",
            "Ice cream",
            "Filet Mignon",
    };

    //String [] order = {};

    public void chatLoop(String statement) {
        Scanner in = new Scanner(System.in);
        System.out.println(getGreeting());


        while (!statement.equals("Bye")||!statement.equals("Goodbye")) {


            statement = in.nextLine();
            //getResponse handles the user reply
            System.out.println(getResponse(statement));


        }

        System.out.println("Come back soon!");

    }

    public String getGreeting() {
        return "Would you like to learn about the soup of the day, chef's special, or dessert of the night?";
    }

    public String getResponse(String statement) {
        String response = "";

        if (statement.length() == 0) {
            response = "Say something, please.";
        } else if (findKeyword(statement, "soup of the day") >= 0) {
            System.out.print(getSoupOfTheDay(soupOfTheDay));

        } else if (findKeyword(statement, "chef's special") >= 0) {
            System.out.println(getChefsSpecial(chefSpecial));

        } else if (findKeyword(statement, "dessert of the night") >= 0) {
            System.out.print(getDessertOfTheNight(dessertOfTheNight));

        } else if (findKeyword(statement, "bill") >= 0) {
            response = getBill(order, completeMenu);

        }else if (findKeyword(statement, "How is the", 0) >= 0){
            response = transformHowIsThe(statement, testItem, completeMenu);
        }
        else if (findKeyword(statement, "Do you have", 0) >= 0) {
            response = transformDoYouHave(statement);
        }
        else if (findKeyword(statement, "Can I get the",0)>=0) {
            response = transformCanIGet(statement);
        }

        else
        {
            return getRandomResponses();
        }

        return response;
    }


    private String transformHowIsThe(String statement, String testItem, String[]completeMenu) {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("?")||lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword(statement, "How is the", 0);
        String restOfStatement = statement.substring(psn + 11).trim();
        testItem = restOfStatement;
        String answer = "Sorry we do not have " + testItem +".";

        for (int i = 0; i < completeMenu.length; i++) {
            if (testItem.equalsIgnoreCase(completeMenu[i])) {
                foundItem = true;
                answer = "The " + testItem + " is " + getCompliment();
            }
        }
        return answer;
    }

    private String transformDoYouHave(String statement) {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("?")||lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword(statement, "Do you have", 0);
        String restOfStatement = statement.substring(psn + 11).trim();
        testItem = restOfStatement;
        String answer = "Sorry we do not have " + testItem +". But I can help recommend you something you may like.";

        for (int i = 0; i < completeMenu.length; i++) {
            if (testItem.equalsIgnoreCase(completeMenu[i])) {
                foundItem = true;
                answer = "Yes we have " + testItem + ".";
            }
        }
        return answer;

    }

    private String transformCanIGet(String statement) {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("?")||lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword(statement, "Can I get the", 0);
        String restOfStatement = statement.substring(psn + 11).trim();
        testItem = restOfStatement;
        String answer = "Sorry we do not have " + testItem +". But I can help recommend you something you may like.";



    }


    private int findKeyword(String statement, String goal,
                            int startPos)
    {
        String phrase = statement.trim().toLowerCase();
        goal = goal.toLowerCase();

        // The only change to incorporate the startPos is in
        // the line below
        int psn = phrase.indexOf(goal, startPos);

        // Refinement--make sure the goal isn't part of a
        // word
        while (psn >= 0)
        {
            // Find the string of length 1 before and after
            // the word
            String before = " ", after = " ";
            if (psn > 0)
            {
                before = phrase.substring(psn - 1, psn);
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(
                        psn + goal.length(),
                        psn + goal.length() + 1);
            }

            // If before and after aren't letters, we've
            // found the word
            if (((before.compareTo("a") < 0) || (before
                    .compareTo("z") > 0)) // before is not a
                    // letter
                    && ((after.compareTo("a") < 0) || (after
                    .compareTo("z") > 0)))
            {
                return psn;
            }

            // The last position didn't work, so let's find
            // the next, if there is one.
            psn = phrase.indexOf(goal, psn + 1);

        }

        return -1;
    }

    private int findKeyword(String statement, String goal)
    {
        return findKeyword (statement, goal, 0);
    }


    private String getSoupOfTheDay(String[]soupOfTheDay)
    {
        Random r = new Random ();
        return "The soup of the day is " + soupOfTheDay [r.nextInt(soupOfTheDay.length)];
    }

    private String getChefsSpecial(String[]chefSpecial)
    {
        Random r = new Random ();
        return "The chef's special is " + chefSpecial[r.nextInt(chefSpecial.length)];
    }

    private String getDessertOfTheNight(String[] dessertOfTheNight)
    {
        Random r = new Random ();
        return "The dessert of the night is " + dessertOfTheNight [r.nextInt(dessertOfTheNight.length)];
    }


    private String getRandomResponses()
    {
        Random r = new Random ();
        return randomResponses [r.nextInt(randomResponses.length)];
    }

    private String [] randomResponses = {"Can I get you anything else?",
            "Is there anything else you want?",
            "How else can I help you?",
    };

    /** private String getBill(int dessertCount, int soupCount, int dishesCount)
     {
     int total = (dessertCount*8) + (dishesCount*12) + (soupCount*9);
     return "You've ordered: " + soupCount + " soups, " + dishesCount + " dishes, and " + dessertCount + " desserts. Your total is " + total + " dollars.";
     }**/

    private String getCompliment()
    {
        Random r = new Random ();
        return compliment [r.nextInt(compliment.length)];
    }

    private String[]compliment = {"very good.",
            "delicious.",
            "excellent.",
            "well made.",
            "amazing.",
            "the best.",
    };

    private void findOrder(String testItem, String[]completeMenu)
    {

        for (int i = 0; i >= completeMenu.length; i++) {
            if (testItem.equalsIgnoreCase(completeMenu[i])) {
                foundItem = true;
            } else {
                foundItem = false;
            }
        }
    }
}
//push