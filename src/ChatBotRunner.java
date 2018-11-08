import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Brooklyn Tech CS Department
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
		int whichChatBot = 0;

		Scanner in = new Scanner (System.in);
		System.out.println("Welcome to WackDonalds, are you here for breakfast, lunch, or dinner?");
		String statement = in.nextLine();
		if (statement.indexOf("breakfast") >= 0)
		{
			whichChatBot = 1;
		}
		else if(statement.indexOf("lunch") >= 0)
		{
			whichChatBot = 2;
		}
		else if(statement.indexOf("dinner") >= 0)
		{
			whichChatBot = 3;
		}


		while (!statement.equals("Bye"))
		{
			//Use Logic to control which chatbot is handling the conversation\
			//This example has only chatbot1
			if(whichChatBot == 1)
			{
				chatbot1.chatLoop(statement);
				statement = in.nextLine();
			}
			else if(whichChatBot == 2)
			{
				chatbot2.chatLoop(statement);
				statement = in.nextLine();
			}
			else if(whichChatBot == 3)
			{
				chatbot3.chatLoop(statement);
				statement = in.nextLine();
			}
		}
	}

}
