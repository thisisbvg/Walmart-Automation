Steps to run the code:
1)	Ensure that there is an input.txt file in the source code along with all the jars and installations needed to run Selenium TestNG.
2)	Run the SearchAutomation.java file and run as TestNG.
3)	Then it prompts to enter the file name which is the name of the file that contains the input(input.text is the name of the file placed in this source code)
4)	Then the program runs the source code based on the annotations and generates the result if everything goes well.
Technical Choices and Trade-offs made:
1)	Faced some issues while running the script on Chrome hence instantiated firefoxDriver.
2)	Rather than simply writing the code, I have used TestNG so that I can control the execution in a proper order. By doing this, I ensured high amount of readability as well as reusability to my code.
3)	I have created an Object Repository that contains all the repeatedly needed data like xpaths, user credentials & URLs. Used an interface to store all the xpath locators which ensures high level of reusability instead of hard coding the same value again and again at multiple places.
4)	Using an input file that contains all the values that needs to be searched rather than hardcoding. This comes handy when you have different sets of input files which we need to test. At that time just by entering a different file name, the program tests the present set of input values.
5)	Thinking of future enhancements, I have collected all the values into a HashMap as ItemName and Quantity rather than a LinkedList. If at all in future, if I want to add different items with different quantities then we can use the same code without making any changes to it.
6)	Ensuring that the file reading is done even before the Test execution starts i.e. @BeforeTest
7)	Before the required method is run, I am opening the Walmart.com URL and also validating the user along with populating the values in the text file to the map.
8)	In the @Test method, I am searching for a particular value and then adding it to the cart and then trying to do the necessary validations.
9)	Once after everything is done and if no exceptions are raised then the driver quits showing the result as all pass. Also once again I cleared the cart. This part is done @AfterTest
Bugs Encountered while testing the application:
1)	The search functionality is not working properly for the item “toys”. What so ever input we try to give, it is showing the same error message.
Example:
Based on your search, we're showing you our "Toys" page.
If this isn't what you meant, you can view a list of items that match "Toys".
2)	All the application is not consistent across the browsers. I am facing issues while executing the code on Chrome where as its working perfectly fine while working on Firefox( It may even be an issue with my machine)
Things missing in my application:
1)	At application execution is instable. I have even tried resolving this problem giving explicit waits along with the implicit waits, yet the problem is persistent.
Things that could have done if I had extra time:
1)	First and foremost thing I could have done if I had extra time is the Cross Browser Testing (IE,FF,Safari,Chrome,Opera)
2)	If I had like around 50 hours of time, I could have automated most of the basic functionalities right from login(Positive as well as Negative tests) till check out.
3)	Would have used Code Coverage tool(EclEmma) to ensure that I am not missing any of the conditions.
4)	Finally would have prepared a manual test plan that covers all the scenarios that I need to automate based on the priority.

