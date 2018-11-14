import java.util.Random;
import java.util.Scanner;
//This is Andy's Chatbot
/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Andy Pun
 * @version September 2018
 */
public class ChatBot2
{
	//variables to keep track of the order
	int drinkNum = 0;
	int burgerNum = 0;
	int saladNum = 0;



	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
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
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hi, welcome to WcDonalds. Would you like to learn about the burger of the day, salad of the day, or drink of the day ? ";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";
		
		if (statement.length() == 0)
		{
			response = "Say something, please.";
		}

		else if (findKeyword(statement, "burger") >= 0)
		{
			response = getBurgerOfTheDay();

		}
		
		else if (findKeyword(statement, "salad") >= 0)
		{
			response = getDressingOfTheDay() + getSaladOfTheDay();

		}
		else if (findKeyword(statement, "drink") >= 0)
		{
			response = getDrinkOfTheDay();
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
			response = getRandomResponse();
		}
		
		return response = getBill(int burgerNum, int saladaNum, int drinkNum);
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
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse ()
	{
		int r = (int)Math.random()*4;
		return randomResponses[r];
	}

	private String [] randomResponses = {"Can I get you anything else?",
			"Is there anything else you want?",
			"How else can I help you?",
	};

	private String getBurgerOfTheDay()
	{
		int r = (int)Math.random()*8;
		return "The burger of the day is " + burgerOfTheDay[r] + " .";
	}

	private String [] burgerOfTheDay = {
			"Hamburger",
			"Cheeseburger",
			"Bacon Burger",
			"Bacon Cheeseburger",
	};

	private String getSaladOfTheDay()
	{
		int r = (int)Math.random()*8;
		return "The salad of the day is " + saladOfTheDay[r] + " .";
	}
	private String [] saladOfTheDay = {
			"Grilled Chicken",
			"Cripsy Chicken",
			"Grilled Steak",
			"Shrimp",
			"Grilled Salmon",
	};

	private String getDressingOfTheDay()
	{
		int r = (int)Math.random()*8;
		return "The dressing of the day is " + dressingOfTheDay[r] + " .";
	}
	private String [] dressingOfTheDay = {
			"Italian",
			"Ceaser",
			"Ranch",
			"Ginger",
			"Vinaigrette",
	};

	private String getDrinkOfTheDay()
	{
		int r = (int)Math.random()*8;
		return "The drink of the day is " + drinkOfTheDay[r] + " .";
	}
	private String [] drinkOfTheDay = {
			"Milkshake",
			"Soda Float",
			"Fresh Juice",
			"Holy Water",
	};

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

	private String getBill(int dessertCount, int soupCount, int dishesCount)
	{
		int total = (dessertCount*8) + (dishesCount*12) + (soupCount*9);
		return "You've ordered: " + soupCount + " soups, " + dishesCount + " dishes, and " + dessertCount + " desserts. Your total is " + total + " dollars.";
	}



}
