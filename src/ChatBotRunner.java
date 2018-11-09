import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Jackson Jacobs, Kelly Fang, Andy Pun
 * @version September 2018
 */
public class ChatBotRunner
{

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args)
	{
		ChatBot1 chatbot1 = new ChatBot1();
		ChatBot2 chatbot2 = new ChatBot2();
		ChatBot3 chatbot3 = new ChatBot3();
		//int whichChatBot = 0;

		Scanner in = new Scanner (System.in);
		System.out.println("Welcome to would you like to order breakfast, lunch or dinner?");
		String userInput = in.nextLine();
		String statement = "";

		while (!statement.equals("Bye"))
		{
			//Use Logic to control which chatbot is handling the conversation\
			//This example has only chatbot1
			if (userInput.equals("breakfast") || (userInput.equals("Breakfast")))
			{
				chatbot1.chatLoop(statement);
				statement = in.nextLine();
			}
			if(userInput.equals("lunch") || (userInput.equals("Lunch")))
			{
				chatbot2.chatLoop(statement);
				statement = in.nextLine();
			}
			else if(userInput.equals("dinner") || (userInput.equals("Dinner")))
			{
				chatbot3.chatLoop(statement);
				statement = in.nextLine();
			}
		}
		System.out.println("Thank you for ordering from WcDonalds. We hope to see you again!");
	}

}
