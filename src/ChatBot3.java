import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBot3
{

    int soupCount = 0;
    int dishesCount = 0;
    int dessertCount = 0;

	public void chatLoop(String statement)
	{
		Scanner in = new Scanner (System.in);
		System.out.println (getGreeting());


		while (!statement.equals("Bye"))
		{


			statement = in.nextLine();
			//getResponse handles the user reply
			System.out.println(getResponse(statement));


		}

	}

	public String getGreeting()
    {
        return "Would you like to learn about the soup of the day, chef's special, or dessert of the night?";
    }

	public String getResponse(String statement)
	{
		String response = "";
		
		if (statement.length() == 0)
		{
			response = "Say something, please.";
		}

		else if (findKeyword(statement, "soup") >= 0)
		{
			response = getSoupOfTheDay();

		}
		
		else if (findKeyword(statement, "chef's") >= 0)
		{
			response =  getChefsSpecial();
		}
		else if (findKeyword(statement, "dessert") >= 0)
		{
			response =  getDessertOfTheNight();
		}

		// Response transforming I want to statement
		else if (findKeyword(statement, "I want the", 0) >= 0)
		{
			response = transformIWantTheStatement(statement);
		}
		else if (findKeyword(statement, "Do you have ",0) >= 0)
		{
			response = transformHowIsThe(statement);
		}	
		else
		{
			response = getRandomResponses();
		}
		
		return response = getBill();
	}

	private String transformIWantTheStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "Do you have", 0);
		String restOfStatement = statement.substring(psn + 12).trim();
		return "I am adding " + restOfStatement + " to your order now.";
	}


	private String transformHowIsThe(String statement, String[] soupOfTheDay, String[] chefSpecial, String[] dessertOfTheNight)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "How is the", 0);
		String restOfStatement = statement.substring(psn+11).trim();
		return "The" + restOfStatement + " is " + getCompliment();
	}

	private String StatementDoYouHave(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psn = findKeyword (statement, "Do you have", 0);
		
		String restOfStatement = statement.substring(psn + 12).trim();
		String check = findOrder();
		return "We " + check + " have " + restOfStatement + " on our menu tonight.";
	}
	

	//private String

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


	private String getSoupOfTheDay()
    {
        int r = (int)Math.random()*8;
        return "The soup of the day is " + soupOfTheDay[r] + " .";
    }

	private String [] soupOfTheDay = {"Clam Chowder",
			"Tomato Soup",
			"French Onion Soup",
			"Chicken Noodle Soup",
			"Lentil Soup",
            "Cauliflower Soup:,"
	};

	private String getChefsSpecial()
    {
        int r =(int)Math.random()*6;
        return "The chef's special is " + chefSpecial[r] + " .";
    }

	private String [] chefSpecial = {"Filet Mignon",
            "Beef Wellington",
            "Butter Chicken",
            "Stuffed Pork Tenderloins",
            "Grilled Salmon",
	};

	private String getDessertOfTheNight()
    {
        int r = (int)Math.random()*5;
        return "The dessert of the night is " + dessertOfTheNight[r] +" .";
    }

	private String [] dessertOfTheNight = {"Chocolate Soufle",
            "Fruit Tart",
            "Macaroons",
            "Tiramisu",
    };

	private String getRandomResponses()
    {
        int r = (int)Math.random()*4;
        return randomResponses[r];
    }

    private String [] randomResponses = {"Can I get you anything else?",
            "Is there anything else you want?",
            "How else can I help you?",
    };

	private String getBill(int dessertCount, int soupCount, int dishesCount)
    {
        int total = (dessertCount*8) + (dishesCount*12) + (soupCount*9);
        return "You've ordered: " + soupCount + " soups, " + dishesCount + " dishes, and " + dessertCount + " desserts. Your total is " + total + " dollars.";
    }

    private String getCompliment();
    {
        int r = (int)(Math.random()*5);
        return compliment[r];
    }

    private String[] compliment = {"very good.",
    "delicious.",
    "excellent.",
    "well made.",
    };

    private String findOrder()
    {
        for (int o : dessertOfTheNight){
            if (dessertOfTheNight[o].equals(restOfStatement)) {
                return "do";
            }
            else if (for (int o : chefSpecial){
            if (!chefSpecial[o].equals(restOfStatement)) {
                return "do";
            }
            else for (int o : soupOfTheDay){
            if (soupOfTheDay[o].equals(restOfStatement)){
                return "do";

                else return "don't"
            }
        }
    }

    }
        return false;
    }
}